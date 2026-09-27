package es.upm.agentes;

import javax.swing.JFrame;
import es.upm.interfaces.InterfazUsuario;


public class MainGUI extends Thread {
	String titulo;
	UIAgent agenteUsuario;

	public MainGUI(String tit, UIAgent a) { // Se reciben dos parámetros 
		this.titulo = "Conexión " + tit;
		this.agenteUsuario = a;
	}

	public void run() { // El interfaz se basará en una ventana de tipo JFrame 
		//Lo personalizamos
		// creando un constructor personalizado para que pueda acceder al agente desde
		// el hilo del interfaz 
		JFrame jFrame; 
		jFrame=new InterfazUsuario(agenteUsuario);
		jFrame.setTitle(titulo);
		jFrame.setVisible(true); 
		jFrame.setResizable(false);
	} 
	public static void main(String[] args) {
        // Crear una instancia de uiAgent
        UIAgent agente = new UIAgent();

        // Crear una instancia de MainGUI
        MainGUI mainGUI = new MainGUI("Título", agente);

        // Llamar a start() en MainGUI para iniciar el hilo
        mainGUI.start();
    }
}
