package es.upm.interfaces;

import javax.swing.*;

import es.upm.agentes.Hotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazSalida {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Recomendador de Hoteles");
        ventana.setSize(1050, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos un panel principal para colocar los paneles de cada hotel y el encabezado
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Añadimos el encabezado
        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setBackground(new Color(158, 173, 172));
        header.setIcon(new ImageIcon(InterfazSalida.class.getResource("/resources/Logo.png")));
        panelPrincipal.add(header, BorderLayout.NORTH);

        /*
         * Esto lo eliminas, eso sí PuntuacionPro no se como lo añadirás porque en el constructor no aparece
         */
        // Creamos ejemplos de datos de hoteles
        Hotel hotel1 = new Hotel("Hotel Paradise", "Miami", 2, 1, 8, 150, 300, 1000, true, true, true, true, true, true);
        Hotel hotel2 = new Hotel("Grand Plaza", "New York", 4, 2, 9, 300, 500, 500, false, true, true, true, false, true);
        Hotel hotel3 = new Hotel("Sunset Resort", "Los Angeles", 3, 2, 7, 200, 400, 800, true, false, true, false, true, true);
        Hotel hotel4 = new Hotel("Mountain View Lodge", "Denver", 6, 3, 8, 180, 600, 2000, true, true, true, true, true, true);
        Hotel hotel5 = new Hotel("Seaside Retreat", "Miami", 4, 2, 9, 250, 450, 1200, true, true, true, true, true, true);

        /*
         * Aquí tienes que cambiar el 6 por el tamaño del ArrayList que metas
         * Y haces un for añadiendo cada panel como abajo
         * No habría necesidad de tocar más cosas, sino me dices
         */
        // Creamos paneles de hotel usando los datos de cada hotel
        JPanel panelHoteles = new JPanel();
        panelHoteles.setLayout(new GridLayout(6, 1));
        panelHoteles.add(crearPanelHotel(hotel1));
        panelHoteles.add(crearPanelHotel(hotel2));
        panelHoteles.add(crearPanelHotel(hotel3));
        panelHoteles.add(crearPanelHotel(hotel4));
        panelHoteles.add(crearPanelHotel(hotel5));
        panelHoteles.add(crearPanelHotel(hotel5));

        // Agregamos el panel de hoteles al centro del panel principal
        panelPrincipal.add(panelHoteles, BorderLayout.CENTER);

        // Creamos un JScrollPane solo para el panel de hoteles
        JScrollPane scrollPane = new JScrollPane(panelHoteles);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Agregamos el panel principal a la ventana
        ventana.getContentPane().add(panelPrincipal);

        ventana.setVisible(true);
    }

    // Método para crear un panel de hotel personalizado
    public static JPanel crearPanelHotel(Hotel aloj) {
        // Panel principal del hotel
        JPanel panelHotel = new JPanel();
        panelHotel.setLayout(new BorderLayout());
        panelHotel.setBackground(Color.WHITE);
        panelHotel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panelHotel.setPreferredSize(new Dimension(760, 200));

        // Panel para la información del hotel (columna izquierda)
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interior
        infoPanel.setBackground(Color.WHITE);

        // Paneles para las tres columnas de información
        JPanel column1 = new JPanel(new GridLayout(0, 1));
        column1.setBackground(Color.WHITE);
        JPanel column2 = new JPanel(new GridLayout(0, 1));
        column2.setBackground(Color.WHITE);
        JPanel column3 = new JPanel(new GridLayout(0, 1));
        column3.setBackground(Color.WHITE);

        // Nombre del hotel
        JLabel lblNombre = new JLabel(aloj.getNombreHotel());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        column1.add(lblNombre);

        // Detalles del hotel
        JLabel lblCiudad = new JLabel(aloj.getCiudad());
        JLabel lblCapacidad = new JLabel("Capacidad: " + aloj.getNPersonas() + " personas");
        JLabel lblDistanciaCentro = new JLabel("Distancia al centro: " + aloj.getDistanciaCentro() + " km");
        JLabel lblNumBanios = new JLabel("Número de baños: " + aloj.getNBaños());
        JLabel lblTamaño = new JLabel("Tamaño: " + aloj.getM2() + " m2");
        column1.add(lblCiudad);
        column1.add(lblCapacidad);
        column1.add(lblNumBanios);
        column1.add(lblTamaño);
        column1.add(lblDistanciaCentro);

        // Puntuación del hotel
        JLabel lblPuntuacion = new JLabel("Puntuación: " + aloj.getPuntuacionGente());
        lblPuntuacion.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel lblPuntuacionPro = new JLabel("Puntuación Pro: " + aloj.getPuntuacionPro());
        lblPuntuacionPro.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel lblPrecio = new JLabel("Precio por noche: " + aloj.getPrecioNoche() + "€");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 17));
        column2.add(lblPuntuacion);
        column2.add(lblPuntuacionPro);
        column2.add(lblPrecio);

        // Servicios del hotel
        JLabel lblServicios = new JLabel("Servicios Disponibles:");
        lblServicios.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel lblJardin = new JLabel("Jardín: " + (aloj.getJardin() ? "Sí" : "No"));
        JLabel lblPiscina = new JLabel("Piscina: " + (aloj.getPiscina() ? "Sí" : "No"));
        JLabel lblWIFI = new JLabel("WIFI: " + (aloj.getWifi() ? "Sí" : "No"));
        JLabel lblParking = new JLabel("Parking: " + (aloj.getParking() ? "Sí" : "No"));
        JLabel lblCalefaccion = new JLabel("Calefacción: " + (aloj.getCalefaccion() ? "Sí" : "No"));
        JLabel lblAireAcondicionado = new JLabel("Aire Acondicionado: " + (aloj.getAireAcondicionado() ? "Sí" : "No"));
        column3.add(lblServicios);
        column3.add(lblJardin);
        column3.add(lblPiscina);
        column3.add(lblWIFI);
        column3.add(lblParking);
        column3.add(lblCalefaccion);
        column3.add(lblAireAcondicionado);

        // Configurar colores para servicios
        setLabelColor(lblJardin, aloj.getJardin());
        setLabelColor(lblPiscina, aloj.getPiscina());
        setLabelColor(lblWIFI, aloj.getWifi());
        setLabelColor(lblParking, aloj.getParking());
        setLabelColor(lblCalefaccion, aloj.getCalefaccion());
        setLabelColor(lblAireAcondicionado, aloj.getAireAcondicionado());

        // Agregar columnas a infoPanel
        infoPanel.add(column1);
        infoPanel.add(column3);
        infoPanel.add(column2);

        // Botón de reservar hotel
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setPreferredSize(new Dimension(760, 40));
        
        // Añadir ActionListener al botón de reservar
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panelHotel, aloj.getNombreHotel() + " reservado", "Reserva Confirmada", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Agregar paneles al panel del hotel
        panelHotel.add(infoPanel, BorderLayout.CENTER);
        panelHotel.add(btnReservar, BorderLayout.PAGE_END);

        return panelHotel;
    }

    // Método para configurar el color del label basado en el servicio
    private static void setLabelColor(JLabel label, boolean isAvailable) {
        if (isAvailable) {
            label.setForeground(new Color(0, 128, 0)); // Verde
        } else {
            label.setForeground(Color.RED);
        }
    }
}
