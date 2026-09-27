package es.upm.agentes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.upm.interfaces.InterfazSalida;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class UIAgent extends Agent {
	private String ciudad;
	private int nPersonas;
	private Date fechaEntrada;
	private Date fechaSalida;
	protected void setup() {
		addBehaviour(new CyclicBehaviourSolicitarAnalisis()); 
		addBehaviour(new CyclicBehaviourMostrarResultados()); // gon y victor creo
		MainGUI gui=new MainGUI(this.getLocalName(), this); 
		gui.start(); 
	}
	public class CyclicBehaviourSolicitarAnalisis extends CyclicBehaviour { 
		private static final long serialVersionUID = 1L; 
		public void action() { 
			try { 													 		//El agente espera a que el usuario haya seleccionado los distintos campos. 
				myAgent.doWait(); 										//Desde el interfaz de usuario el usuario ha pulsado el botón Buscar y podemos continuar 
				SearchRequest searchR = new SearchRequest();  						//Definimos un objeto SearchRequest que sea serializable 
				searchR.setCiudad(ciudad); 
				searchR.setNPersonas(nPersonas); 					 	//Enviamos el mensaje de solicitud de clasificación al  Perception Agent 
				searchR.setFechaEntrada(fechaEntrada);
				searchR.setFechaSalida(fechaSalida);
				/*Buscar el agente en el directorio de Servicios */
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);		// Creamos un mensaje ACLMessage de tipo INFORM
				DFAgentDescription template = new DFAgentDescription(); // Buscamos el agente PerceptionAgent en el directorio de servicios
				ServiceDescription sd = new ServiceDescription();
				sd.setType("perception"); 								// El tipo de servicio que ofrece el agente PerceptionAgent
				template.addServices(sd);
				DFAgentDescription[] result = DFService.search(myAgent, template);  // Obtenemos los agentes que coinciden con la plantilla
				if (result.length > 0) {
					// Si se encontró al menos un agente PerceptionAgent, enviamos el mensaje a uno de ellos
					AID perceptionAgentAID = result[0].getName();
					msg.addReceiver(perceptionAgentAID);

					// Convertimos el objeto SearchRequest a una representación serializable y lo enviamos como contenido del mensaje
					try {
						msg.setContentObject(searchR);
						// Enviamos el mensaje
						myAgent.send(msg);
						System.out.println("Mensaje enviado correctamente a PerceptionAgent.");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					System.out.println("No se encontró ningún agente PerceptionAgent en el sistema.");
				}
			} 

			catch (Exception e) { 
				e.printStackTrace(); 
			}
		} 
	}


	public void enviarInformacion(String ciudad2, int nPersonas2, Date fechaEntrada2, Date fechaSalida2, Integer precioMinimo, Integer precioMaximo, String TipoViaje) {
		// Crear un mensaje ACLMessage
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

		// Agregar los destinatarios del mensaje (en este caso, el agente PerceptionAgent)
		AID perceptionAgentAID = new AID("PerceptionAgent", AID.ISLOCALNAME);
		msg.addReceiver(perceptionAgentAID);

		// Crear un objeto SearchRequest con la información recolectada
		SearchRequest searchRequest = new SearchRequest(ciudad2, nPersonas2, fechaEntrada2, fechaSalida2, precioMinimo, precioMaximo, TipoViaje);

		try {
			// Establecer el contenido del mensaje como el objeto SearchRequest serializado
			msg.setContentObject(searchRequest);

			// Enviar el mensaje al agente PerceptionAgent
			send(msg);

			// Puedes imprimir un mensaje para verificar que se envió correctamente
			System.out.println("Mensaje enviado a PerceptionAgent:\n" + searchRequest.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters y Setters
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNPersonas() {
		return nPersonas;
	}

	public void setNPersonas(int nPersonas) {
		this.nPersonas = nPersonas;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}




	public class CyclicBehaviourMostrarResultados extends CyclicBehaviour {


		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
		// TODO Auto-generated method stub
		ACLMessage msg1=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		try {
		ArrayList<Hotel> listaHoteles = (ArrayList<Hotel>) msg1.getContentObject();
		JFrame ventana = new JFrame("Recomendador de Hoteles");
		        ventana.setSize(1050, 700);
		        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        // Creamos un panel principal para colocar los paneles de cada hotel y el encabezado
		        JPanel panelPrincipal = new JPanel();
		        panelPrincipal.setLayout(new BorderLayout());

		        // AÃ±adimos el encabezado
		        JLabel header = new JLabel();
		        header.setOpaque(true);
		        header.setBackground(new Color(158, 173, 172));
		        header.setIcon(new ImageIcon(InterfazSalida.class.getResource("/resources/Logo.png")));
		        panelPrincipal.add(header, BorderLayout.NORTH);

		       
		        // Creamos paneles de hotel usando los datos de cada hotel
		        JPanel panelHoteles = new JPanel();
		       
		        panelHoteles.setLayout(new GridLayout(listaHoteles.size(), 1));
		        for(Hotel h : listaHoteles) {
		        panelHoteles.add(InterfazSalida.crearPanelHotel(h));
		        }

		        // Agregamos el panel de hoteles al centro del panel principal
		        panelPrincipal.add(panelHoteles, BorderLayout.CENTER);

		        // Creamos un JScrollPane solo para el panel de hoteles
		        JScrollPane scrollPane = new JScrollPane(panelHoteles);
		        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

		        // Agregamos el panel principal a la ventana
		        ventana.getContentPane().add(panelPrincipal);

		        ventana.setVisible(true);
		} catch(Exception e) {
		e.printStackTrace();
		}
		}
	}

}




