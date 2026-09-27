package es.upm.agentes;

import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class PerceptionAgent extends Agent {
	
	private static Connection conn;
	
    protected void setup() {
       // System.out.println("Agent " + getLocalName() + " is ready.");
    	// Crear la descripciÃ³n del agente
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        
        // Crear la descripciÃ³n del servicio que ofrece el agente
        ServiceDescription sd = new ServiceDescription();
        sd.setType("perception"); // Define el tipo de servicio que ofrece el agente
        sd.setName(getLocalName() + "-perception-service"); // Define un nombre para el servicio
        dfd.addServices(sd);
        
        // Registrar el agente en el DF
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        // Comportamiento para recibir mensajes de la interfaz de usuario
      addBehaviour(new ReceiveUserInputBehaviour());
      
      String url = "jdbc:mysql://localhost:3306/hoteles";
      String usuario = "usuario";
      String contraseña = "contraseña";

      // Crear la conexión
      try {
          conn = DriverManager.getConnection(url, usuario, contraseña);
      } catch (SQLException e) {
          e.printStackTrace();
      }
    	
    }

    private class ReceiveUserInputBehaviour extends CyclicBehaviour {
        public void action() {
            // Esperar a recibir un mensaje de la interfaz de usuario
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

            // Recibe el prÃ³ximo mensaje que coincida con la plantilla
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                try {
                    // Extrae el contenido del mensaje
                    SearchRequest searchRequest = (SearchRequest) msg.getContentObject();

                    // Procesa el contenido segÃºn sea necesario
                    // Por ejemplo, aquÃ­ puedes imprimirlo
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Mensaje recibido por PerceptionAgent:");
                    System.out.println("Ciudad: " + searchRequest.getCiudad());
                    System.out.println("NÃºmero de personas: " + searchRequest.getNPersonas());
                    System.out.println("Fecha de entrada: " + searchRequest.getFechaEntrada());
                    System.out.println("Fecha de salida: " + searchRequest.getFechaSalida());
                    System.out.println("Precio minimo: " + searchRequest.getPrecioMin());
                    System.out.println("Precio maximo: " + searchRequest.getPrecioMax());
                    System.out.println("Jardin: " + searchRequest.getTipoViaje());
                 
                    
                    
                    //SearchRequest rq = new SearchRequest();
                	
                	String sql = "SELECT a.* FROM alojamiento a LEFT JOIN Reserva r ON a.id = r.id_alojamiento "
                            + "WHERE (r.fecha_inicio = ? AND r.fecha_final = ?)"
                            + " AND (a.Capacidad >= ?)"
                            + " AND (? IS NULL OR a.Ciudad = ?)"
                            + " AND (? IS NULL OR a.PrecioNoche <= ?)"
                            + " AND (? IS NULL OR a.PrecioNoche >= ?)";

                	PreparedStatement statement = null;
                    ResultSet resultSet = null;
                    ArrayList<Hotel> listaHoteles = new ArrayList<>();
                	
                	try {
                statement = conn.prepareStatement(sql);
                statement.setDate(1, new java.sql.Date(searchRequest.getFechaSalida().getTime()));
                statement.setDate(2, new java.sql.Date(searchRequest.getFechaEntrada().getTime())); 
                statement.setInt(3, searchRequest.getNPersonas());
                statement.setObject(4, searchRequest.getCiudad() != null ? 1 : null);
                statement.setString(5, searchRequest.getCiudad());
                statement.setObject(6, searchRequest.getPrecioMax() != null ? 1 : null);
                statement.setInt(7, searchRequest.getPrecioMax());
                statement.setObject(8, searchRequest.getPrecioMin() != null ? 1 : null);
                statement.setInt(9, searchRequest.getPrecioMin());

                resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    Hotel hotel = new Hotel(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Ciudad"),
                        resultSet.getInt("Capacidad"),
                        resultSet.getInt("nBanios"),
                        resultSet.getInt("Puntuacion"),
                        resultSet.getInt("PrecioNoche"),
                        resultSet.getInt("m2"), 
                        resultSet.getInt("DistanciaCentro"),
                        resultSet.getBoolean("Jardin"),
                        resultSet.getBoolean("Piscina"),
                        resultSet.getBoolean("WIFI"),
                        resultSet.getBoolean("AireAcondicionado"),
                        resultSet.getBoolean("Parking"),
                        resultSet.getBoolean("Calefaccion")  
                    );
                    listaHoteles.add(hotel);
                }
                
                	} finally {
                        if (resultSet != null) resultSet.close();
                        if (statement != null) statement.close();
                    }

                    
                Data d=new Data(listaHoteles,searchRequest);

                ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                DFAgentDescription template = new DFAgentDescription(); // Buscamos el agente PerceptionAgent en el directorio de servicios
                ServiceDescription sd = new ServiceDescription();
                sd.setType("processing"); 
                template.addServices(sd);
                DFAgentDescription[] result;
              try {
                  result = DFService.search(myAgent, template);
                  if (result.length > 0) {
                      // Si se encontrÃ³ al menos un agente PerceptionAgent, enviamos el mensaje a uno de ellos
                      AID processingAgentAID = result[0].getName();
                      msg2.addReceiver(processingAgentAID);

                      // Convertimos el objeto SearchRequest a una representaciÃ³n serializable y lo enviamos como contenido del mensaje
                      try {
                          msg2.setContentObject((Serializable) d);
                          // Enviamos el mensaje
                          myAgent.send(msg2);
                          System.out.println("Mensaje enviado correctamente a ProcessingAgent.");
                      } catch (Exception ex) {
                          ex.printStackTrace();
                      }
                  } else {
                      System.out.println("No se encontro ningun agente ProcessingAgent en el sistema.");
                  }
              } catch (FIPAException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }  // Obtenemos los agentes que coinciden con la plantilla
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Si no se recibe ningÃºn mensaje, se bloquea el comportamiento hasta que llegue uno
                block();
            }
        }
    }

    private void sendInformationToProcessingAgent(SearchRequest request) { // esta incompleto pq ademas de esto debes anadirlo en action mira de ejemplo el mio en UIAgent
        // Crear un objeto ACLMessage
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

        // Establecer el destinatario del mensaje (el agente de procesamiento)
        AID processingAgentAID = new AID("processing-agent", AID.ISLOCALNAME);
        msg.addReceiver(processingAgentAID);

        // Establecer el contenido del mensaje como el objeto SearchRequest serializado
        try {
            msg.setContentObject((Serializable) request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Enviar el mensaje al agente de procesamiento
        send(msg);
    }
}
