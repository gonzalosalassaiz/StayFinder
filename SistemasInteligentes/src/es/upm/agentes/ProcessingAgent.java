package es.upm.agentes;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProcessingAgent extends Agent {
	ArrayList<Hotel> listaHoteles;
	ArrayList<Hotel> reslistaHoteles;
	protected CyclicBehaviourOrdenHoteles comportamiento1 = new CyclicBehaviourOrdenHoteles();
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

	protected void setup() {
		// Registrar el servicio que ofrece el ProcessingAgent al Directory Facilitator
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		// Crear una descripción del servicio
		ServiceDescription sd = new ServiceDescription();
		sd.setType("processing");
		sd.setName(getLocalName() + "-processing-service");
		// sd.addLanguages("java:java.util.ArrayList"); // Asegurando que el servicio
		// reconoce el tipo de datos
		dfd.addServices(sd);
		// Registrar el servicio al Directory Facilitator
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			System.err.println("Agente " + getLocalName() + ": " + e.getMessage());
		}
		listaHoteles = new ArrayList<Hotel>();
		reslistaHoteles = new ArrayList<Hotel>();
		addBehaviour(comportamiento1);
	}

	public class CyclicBehaviourOrdenHoteles extends CyclicBehaviour {
		public void action() {
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				System.out.println();
				System.out.println(myAgent.getLocalName() + ": Recibo el mensaje: \n" + msg);
				try {
					// Recibimos los datos a analizar, que vienen en el mensaje
					// Data analizar = new Data();
					Data analizar = (Data) msg.getContentObject();
					listaHoteles = analizar.getList();
					SearchRequest sr = analizar.getSR();
					System.out.println(sr.getTipoViaje());
					if (estaEnVerano((Date) sr.getFechaEntrada()) && sr.getTipoViaje().equals("Descanso")) {
						System.out.println(sr.getTipoViaje());

						OneShotBehaviour b1 = new ComportamientoVeranoDescanso();
						myAgent.addBehaviour(b1);
					} else if (estaEnVerano((Date) sr.getFechaEntrada()) && sr.getTipoViaje().equals("Turismo")) {
						OneShotBehaviour b2 = new ComportamientoVeranoTurismo();
						myAgent.addBehaviour(b2);
					} else if (!estaEnVerano((Date) sr.getFechaEntrada()) && sr.getTipoViaje().equals("Descanso")) {
						OneShotBehaviour b3 = new ComportamientoNoVeranoDescanso();
						myAgent.addBehaviour(b3);
					} else if (!estaEnVerano((Date) sr.getFechaEntrada()) && sr.getTipoViaje().equals("Turismo")) {
						OneShotBehaviour b4 = new ComportamientoNoVeranoDescanso();
						myAgent.addBehaviour(b4);
					}
					// Ordenar la lista de hoteles por puntuacionPro

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	public class ComportamientoVeranoDescanso extends OneShotBehaviour {
		public void action() {
			try {
				System.out.println("Hola");

				for (Hotel hotel : listaHoteles) {
					int pH = 0;
					if (hotel.getNPersonas() / hotel.getNBaños() <= 2)
						pH += 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() > 4)
						pH -= 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() <= 4)
						pH += 1;

					// Puntuación por puntuación de otros clientes
					pH += hotel.getPuntuacionGente();

					// Penalización por precio por noche
					pH -= (hotel.getPrecioNoche() / 10);

					// Puntuación por metros cuadrados por persona
					if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 30)
						pH += 3;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 20)
						pH += 1;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) < 15)
						pH -= 2;

					// Puntuación por distancia al centro
					if (hotel.getDistanciaCentro() < 3000)
						pH -= 2;
					else if (hotel.getDistanciaCentro() < 5000)
						pH -= 1;
					else if (hotel.getDistanciaCentro() > 8500)
						pH += 2;
					// else if (hotel.getDistanciaCentro() > 10000) pH += 2;

					// Puntuación por otras características
					if (hotel.getJardin())
						pH += 2;
					if (hotel.getPiscina())
						pH += 3;
					if (hotel.getWifi())
						pH += 1;
					if (hotel.getAireAcondicionado())
						pH += 3;
					if (hotel.getParking())
						pH += 2;
					hotel.setPuntuacionPro(pH); // Asignar la puntuacionPro al hotel
				}
				Collections.sort(listaHoteles, new Comparator<Hotel>() {

					public int compare(Hotel h1, Hotel h2) {
						return Integer.compare(h2.getPuntuacionPro(), h1.getPuntuacionPro());
					}
				});
				for (int i = 0; i < listaHoteles.size(); i++) {
					System.out.println(listaHoteles.get(i));
				}
				enviarListaOrdenada(listaHoteles);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class ComportamientoVeranoTurismo extends OneShotBehaviour {
		public void action() {
			try {
				for (Hotel hotel : listaHoteles) {
					int pH = 0;

					if (hotel.getNPersonas() / hotel.getNBaños() <= 2)
						pH += 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() > 4)
						pH -= 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() <= 3)
						pH += 1;

					// Puntuación por puntuación de otros clientes
					pH += hotel.getPuntuacionGente();

					// Penalización por precio por noche
					pH -= (hotel.getPrecioNoche() / 10);

					// Puntuación por metros cuadrados por persona
					// if (hotel.getM2() / (hotel.getNPersonas()/2) >= 30) pH += 2;
					if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 20)
						pH += 1;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) < 15)
						pH -= 1;

					// Puntuación por distancia al centro
					if (hotel.getDistanciaCentro() < 3000)
						pH += 3;
					// else if (hotel.getDistanciaCentro() < 5000) pH += 1;
					else if (hotel.getDistanciaCentro() > 6000)
						pH -= 2;

					// Puntuación por otras características
					if (hotel.getJardin())
						pH += 1;
					if (hotel.getPiscina())
						pH += 1;
					if (hotel.getWifi())
						pH += 1;
					if (hotel.getAireAcondicionado())
						pH += 3;
					if (hotel.getParking())
						pH += 2;
					// if (hotel.getCalefaccion()) pH += 2;
					hotel.setPuntuacionPro(pH); // Asignar la puntuacionPro al hotel
					Collections.sort(listaHoteles, new Comparator<Hotel>() {

						public int compare(Hotel h1, Hotel h2) {
							return Integer.compare(h2.getPuntuacionPro(), h1.getPuntuacionPro());
						}
					});
					for (int i = 0; i < listaHoteles.size(); i++) {
						System.out.println(listaHoteles.get(i));
					}
					enviarListaOrdenada(listaHoteles);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class ComportamientoNoVeranoDescanso extends OneShotBehaviour {
		public void action() {
			try {
				for (Hotel hotel : listaHoteles) {
					int pH = 0;

					if (hotel.getNPersonas() / hotel.getNBaños() <= 2)
						pH += 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() > 4)
						pH -= 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() <= 4)
						pH += 1;

					// Puntuación por puntuación de otros clientes
					pH += hotel.getPuntuacionGente();

					// Penalización por precio por noche
					pH -= (hotel.getPrecioNoche() / 10);

					// Puntuación por metros cuadrados por persona
					if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 30)
						pH += 3;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 20)
						pH += 1;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) < 15)
						pH -= 2;

					// Puntuación por distancia al centro
					if (hotel.getDistanciaCentro() < 3000)
						pH -= 2;
					else if (hotel.getDistanciaCentro() < 5000)
						pH -= 1;
					else if (hotel.getDistanciaCentro() > 8500)
						pH += 2;

					// Puntuación por otras características
					if (hotel.getJardin())
						pH += 2;
					// if (hotel.getPiscina()) pH += 1;
					if (hotel.getWifi())
						pH += 2;
					// if (hotel.getAireAcondicionado()) pH += 2;
					if (hotel.getParking())
						pH += 2;
					if (hotel.getCalefaccion())
						pH += 3;
					hotel.setPiscina(false);
					hotel.setPuntuacionPro(pH); // Asignar la puntuacionPro al hotel
					Collections.sort(listaHoteles, new Comparator<Hotel>() {

						public int compare(Hotel h1, Hotel h2) {
							return Integer.compare(h2.getPuntuacionPro(), h1.getPuntuacionPro());
						}
					});
					for (int i = 0; i < listaHoteles.size(); i++) {
						System.out.println(listaHoteles.get(i));
					}
					enviarListaOrdenada(listaHoteles);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class ComportamientoNoVeranoTurismo extends OneShotBehaviour {
		public void action() {
			try {
				for (Hotel hotel : listaHoteles) {
					int pH = 0;

					if (hotel.getNPersonas() / hotel.getNBaños() <= 2)
						pH += 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() > 4)
						pH -= 2;
					else if (hotel.getNPersonas() / hotel.getNBaños() <= 4)
						pH += 1;

					// Puntuación por puntuación de otros clientes
					pH += hotel.getPuntuacionGente();

					// Penalización por precio por noche
					pH -= (hotel.getPrecioNoche() / 10);

					// Puntuación por metros cuadrados por persona
					if (hotel.getM2() / (hotel.getNPersonas() / 2) >= 20)
						pH += 1;
					else if (hotel.getM2() / (hotel.getNPersonas() / 2) < 15)
						pH -= 1;

					// Puntuación por distancia al centro
					if (hotel.getDistanciaCentro() < 2000)
						pH += 3;
					else if (hotel.getDistanciaCentro() < 4000)
						pH += 1;
					else if (hotel.getDistanciaCentro() > 6000)
						pH -= 2;

					// Puntuación por otras características
					if (hotel.getJardin())
						pH += 1;
					// if (hotel.getPiscina()) pH += 1;
					if (hotel.getWifi())
						pH += 1;
					// if (hotel.getAireAcondicionado()) pH += 3;
					if (hotel.getParking())
						pH += 2;
					if (hotel.getCalefaccion())
						pH += 3;
					hotel.setPiscina(false);

					hotel.setPuntuacionPro(pH); // Asignar la puntuacionPro al hotel
					Collections.sort(listaHoteles, new Comparator<Hotel>() {

						public int compare(Hotel h1, Hotel h2) {
							return Integer.compare(h2.getPuntuacionPro(), h1.getPuntuacionPro());
						}
					});
					for (int i = 0; i < listaHoteles.size(); i++) {
						System.out.println(listaHoteles.get(i));
					}
					enviarListaOrdenada(listaHoteles);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Método para enviar la lista de hoteles ordenada al UIAgent
	private void enviarListaOrdenada(ArrayList<Hotel> listaHoteles) {
		// Crear un objeto de mensaje ACL con el tipo de mensaje INFORM
		ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);

		// Establecer el destinatario del mensaje (puedes cambiar "UIAgent" por el
		// nombre del agente receptor)
		aclMessage.addReceiver(getAID("UIAgent"));

		try {
			// Establecer el contenido del mensaje como la lista de hoteles ordenada
			aclMessage.setContentObject((Serializable) listaHoteles);

			// Enviar el mensaje
			send(aclMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean estaEnVerano(Date fecha) {
		// Convertir java.sql.Date a java.time.LocalDate
		LocalDate localDate = fecha.toLocalDate();

		// Obtener el mes de la fecha
		Month mes = localDate.getMonth();

		// Comprobar si el mes está entre junio (JUNE) y septiembre (SEPTEMBER), ambos
		// incluidos
		return mes.compareTo(Month.JUNE) >= 0 && mes.compareTo(Month.SEPTEMBER) <= 0;
	}
}
