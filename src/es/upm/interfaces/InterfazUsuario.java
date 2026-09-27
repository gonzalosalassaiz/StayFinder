package es.upm.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import es.upm.agentes.UIAgent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.text.Format;
import javax.swing.SwingConstants;

public class InterfazUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAgent agente = new UIAgent(); // Create an instance of uiAgent
					InterfazUsuario frame = new InterfazUsuario(agente);
					frame.setVisible(true);
					frame.setResizable(false); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public InterfazUsuario(UIAgent agente) {
		setType(Type.POPUP);
		setTitle("Nombre app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1134, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 173, 172));
		panel.setBounds(0, 0, 1137, 707);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(158, 173, 172));
		panel_1.setBounds(0, 0, 1127, 142);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel header = new JLabel("New label");
		header.setBackground(new Color(158, 173, 172));
		header.setBounds(27, 35, 1090, 107);
		panel_1.add(header);
		header.setIcon(new ImageIcon(InterfazUsuario.class.getResource("/resources/Logo.png")));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 26));
		btnBuscar.setBounds(416, 633, 258, 42);
		panel.add(btnBuscar);
	
        setVisible(true);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(83, 186, 948, 435);
		panel.add(panel_2);
		panel_2.setLayout(null);
		// Create a JLabel to display the slider value
		

		JComboBox<String> Ciudades = new JComboBox();
		Ciudades.setBounds(51, 140, 194, 29);
		panel_2.add(Ciudades);
		Ciudades.setName("");
		Ciudades.setToolTipText("");
		Ciudades.setBackground(new Color(255, 255, 255));
		Ciudades.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		Ciudades.setModel(new DefaultComboBoxModel(new String[] { "", "Madrid", "Barcelona", "Bilbao", "Alicante",
				"Granada", "Valencia", "Málaga", "Sevilla" }));
		
		JLabel lblNewLabel = new JLabel("Ciudad");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(51, 90, 194, 22);
		panel_2.add(lblNewLabel);
		
		JLabel lblNPersonas = new JLabel("Nº Personas");
		lblNPersonas.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNPersonas.setBounds(348, 210, 194, 22);
		panel_2.add(lblNPersonas);
		
		JSpinner Npersonas = new JSpinner();
		Npersonas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		Npersonas.setBounds(348, 258, 231, 29);
		panel_2.add(Npersonas);
		
		JDateChooser fechaInicio = new JDateChooser();
		fechaInicio.getCalendarButton().setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 10));
		fechaInicio.setBackground(UIManager.getColor("Button.background"));
		fechaInicio.setBounds(51, 258, 194, 29);
		panel_2.add(fechaInicio);
		
		JDateChooser fechaFinal = new JDateChooser();
		fechaFinal.getCalendarButton().setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 10));
		fechaFinal.setBounds(51, 371, 194, 29);
		panel_2.add(fechaFinal);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblFechaInicio.setBounds(51, 210, 194, 22);
		panel_2.add(lblFechaInicio);
		
		JLabel lblFechaFinal = new JLabel("Fecha final");
		lblFechaFinal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblFechaFinal.setBounds(51, 321, 194, 22);
		panel_2.add(lblFechaFinal);
		
		
		
		JLabel lblPrecioMximonoche = new JLabel("Precio mínimo por noche");
		lblPrecioMximonoche.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPrecioMximonoche.setBounds(666, 90, 194, 22);
		panel_2.add(lblPrecioMximonoche);
		
		JLabel lblPrecioMximonoche_1 = new JLabel("Precio máximo por noche");
		lblPrecioMximonoche_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPrecioMximonoche_1.setBounds(666, 210, 194, 22);
		panel_2.add(lblPrecioMximonoche_1);
		
		JFormattedTextField PrecioMinimo = new JFormattedTextField((Format) null);
		PrecioMinimo.setBounds(666, 142, 221, 29);
		panel_2.add(PrecioMinimo);
		
		JFormattedTextField PrecioMaximo = new JFormattedTextField((Format) null);
		PrecioMaximo.setBounds(666, 261, 216, 29);
		panel_2.add(PrecioMaximo);
		String exampleText = "50";
		String exampleText1 = "250";
		 PrecioMinimo.setText(exampleText);
		PrecioMinimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PrecioMinimo.getText().equals(exampleText)) {
                	PrecioMinimo.setText("");
                	PrecioMinimo.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PrecioMinimo.getText().isEmpty()) {
                	PrecioMinimo.setText(exampleText1);
                	PrecioMinimo.setForeground(Color.GRAY);
                }
            }
        });
		 PrecioMaximo.setText(exampleText1);
		PrecioMaximo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PrecioMaximo.getText().equals(exampleText1)) {
                	PrecioMaximo.setText("");
                	PrecioMaximo.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PrecioMaximo.getText().isEmpty()) {
                	PrecioMaximo.setText(exampleText);
                	PrecioMaximo.setForeground(Color.GRAY);
                }
            }
        });

		
		
		JLabel lblNewLabel_1 = new JLabel("Detalles");
		lblNewLabel_1.setBackground(new Color(51, 102, 102));
		lblNewLabel_1.setForeground(new Color(51, 102, 102));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		lblNewLabel_1.setBounds(329, 33, 284, 32);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lugar y periodo");
		lblNewLabel_1_1.setBackground(new Color(51, 102, 102));
		lblNewLabel_1_1.setForeground(new Color(51, 102, 102));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(10, 33, 271, 32);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Presupuesto");
		lblNewLabel_1_2.setBackground(new Color(51, 102, 102));
		lblNewLabel_1_2.setForeground(new Color(51, 102, 102));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(653, 33, 234, 32);
		panel_2.add(lblNewLabel_1_2);
		
		JComboBox TipoViaje = new JComboBox();
		TipoViaje.setBackground(new Color(255, 255, 255));
		TipoViaje.setModel(new DefaultComboBoxModel(new String[] {"", "Turístico", "Descanso"}));
		TipoViaje.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		TipoViaje.setBounds(348, 140, 231, 29);
		panel_2.add(TipoViaje);
		TipoViaje.setName("");
		TipoViaje.setToolTipText("");
		TipoViaje.setBackground(new Color(255, 255, 255));
		
		JLabel lblTipoDeViaje = new JLabel("Tipo de viaje");
		lblTipoDeViaje.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTipoDeViaje.setBounds(348, 90, 194, 22);
		panel_2.add(lblTipoDeViaje);
		
		
		
		JSeparator separator_1_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1_1.setBounds(666, 122, 221, 8);
		panel_2.add(separator_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("€ ");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(893, 147, 45, 13);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("€ ");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(892, 266, 45, 13);
		panel_2.add(lblNewLabel_2_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 102, 102));
		separator.setAlignmentY(0.8f);
		separator.setAlignmentX(0.8f);
		separator.setBounds(646, 75, 271, 2);
		panel_2.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(51, 102, 102));
		separator_1.setAlignmentY(0.8f);
		separator_1.setAlignmentX(0.8f);
		separator_1.setBackground(new Color(51, 102, 102));
		separator_1.setBounds(339, 75, 264, 2);
		panel_2.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(51, 102, 102));
		separator_1_1.setAlignmentY(0.8f);
		separator_1_1.setAlignmentX(0.8f);
		separator_1_1.setBackground(new Color(51, 102, 102));
		separator_1_1.setBounds(40, 75, 234, 2);
		panel_2.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(51, 122, 194, 8);
		panel_2.add(separator_1_1_1);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setBounds(666, 243, 216, 8);
		panel_2.add(separator_1_1_1_1);
		
		JSeparator separator_1_1_1_2 = new JSeparator();
		separator_1_1_1_2.setBounds(51, 240, 194, 8);
		panel_2.add(separator_1_1_1_2);
		
		JSeparator separator_1_1_1_3 = new JSeparator();
		separator_1_1_1_3.setBounds(51, 353, 194, 8);
		panel_2.add(separator_1_1_1_3);
		
		JSeparator separator_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1.setBounds(348, 124, 231, 8);
		panel_2.add(separator_1_1_1_1_1);
		
		JSeparator separator_1_1_1_1_2 = new JSeparator();
		separator_1_1_1_1_2.setBounds(348, 240, 221, 8);
		panel_2.add(separator_1_1_1_1_2);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ciudad = (String) Ciudades.getSelectedItem();
		        int nPersonas = (int) Npersonas.getValue();
		        Date fechaEntrada = fechaInicio.getDate();
		        Date fechaSalida = fechaFinal.getDate();
		        String precioMinimoStr = PrecioMinimo.getText().trim();
		        String precioMaximoStr = PrecioMaximo.getText().trim();
		     //  Integer precioMinimo; // Si no pone precio el usuario se pone como minimo el minimo segun BD?
		   //    Integer precioMaximo ; //  Si no pone precio el usuario se pone como minimo el max segun BD ? 
		       String Tipo_Viaje= (String) TipoViaje.getSelectedItem();
		  
			    
			      
		        if (fechaEntrada == null) {
		        	// Crear un JOptionPane personalizado con un tamaño específico
		        	JOptionPane optionPane = new JOptionPane("Debe seleccionar una fecha de inicio.", JOptionPane.ERROR_MESSAGE);
		        	JDialog dialog = optionPane.createDialog("Error");
		        	dialog.setSize(400, 200); // Establecer el tamaño deseado
		        	dialog.setVisible(true);

		         //   JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio", "Error", JOptionPane.ERROR_MESSAGE);
		            
		            return;
		        }
		        
		      
		       
		        Date fechaActual = new Date();
		        if (fechaEntrada.before(fechaActual)) {
		            JOptionPane optionPane = new JOptionPane("La fecha de inicio no puede ser anterior a la fecha actual.", JOptionPane.ERROR_MESSAGE);
		        	JDialog dialog = optionPane.createDialog("Error");
		        	dialog.setSize(400, 200); // Establecer el tamaño deseado
		        	dialog.setVisible(true);
		            return;
		        }
		        if (fechaSalida == null) {
		        	JOptionPane optionPane = new JOptionPane("Debe seleccionar una fecha de finalización.", JOptionPane.ERROR_MESSAGE);
		        	JDialog dialog = optionPane.createDialog("Error");
		        	dialog.setSize(400, 200); // Establecer el tamaño deseado
		        	dialog.setVisible(true);
		            return;
		        }
		        // Verificar que la fecha de salida no sea anterior a la fecha de entrada
		        if (fechaSalida.before(fechaEntrada)) {
		        	JOptionPane optionPane = new JOptionPane("La fecha de salida no puede ser anterior a la fecha de entrada.", JOptionPane.ERROR_MESSAGE);
		        	JDialog dialog = optionPane.createDialog("Error");
		        	dialog.setSize(400, 200); // Establecer el tamaño deseado
		        	dialog.setVisible(true);
		            return;
		        }
		        if (nPersonas==0) {
		        	JOptionPane optionPane = new JOptionPane("El número de personas debe ser al menos 1.", JOptionPane.ERROR_MESSAGE);
		        	JDialog dialog = optionPane.createDialog("Error");
		        	dialog.setSize(400, 200); // Establecer el tamaño deseado
		        	dialog.setVisible(true);
		            return;
		        }
		        Integer precioMinimo;	
		       
		        if(!precioMinimoStr.isEmpty()) {
		        		      
			          precioMinimo = Integer.parseInt(precioMinimoStr);
			         if (precioMinimo < 70) {
					   precioMinimo=70;
					 }
		        }
		        else precioMinimo=70;
		        Integer precioMaximo ;
		        if(!precioMaximoStr.isEmpty()) {
		        	
			        precioMaximo = Integer.parseInt(precioMaximoStr);
			    }
			     else precioMaximo= 250;

			       
			        // Obtener el precio mínimo y verificar que sea mayor o igual a 50
			
		    
		        agente.enviarInformacion(ciudad, nPersonas, fechaEntrada, fechaSalida, precioMinimo, precioMaximo, Tipo_Viaje);
		        // Cerrar la ventana
		        dispose();
			}
		});
	}
}
