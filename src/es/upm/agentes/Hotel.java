package es.upm.agentes;


import jade.util.leap.Serializable;

//import java.io.Serializable;

public class Hotel implements Serializable {
	private String nombreHotel;
	private String ciudad;
	private int nPersonas;
	private int nBaños;
	private int puntuacionGente;
	private int precioNoche;
	private int m2;
	private int distanciaCentro;
	private Boolean jardin;
	private Boolean piscina;
	private Boolean wifi;
	private Boolean aireAcondicionado;
	private Boolean parking;
	private Boolean calefaccion;
	private int puntuacionPro;//no se declara en el constructor, la añade el processing.

	// Constructor
	public Hotel(String nombreHotel, String ciudad, int nPersonas, int nBaños, int puntuacionGente, int precioNoche, int m2,int distanciaCentro, Boolean jardin, Boolean piscina, Boolean wifi, Boolean aireAcondicionado, Boolean parking, Boolean calefaccion) {
		this.nombreHotel=nombreHotel;
		this.ciudad = ciudad;
		this.nPersonas = nPersonas;
		this.nBaños = nBaños;
		this.puntuacionGente = puntuacionGente;
		this.precioNoche = precioNoche;
		this.m2 = m2;
		this.distanciaCentro=distanciaCentro;
		this.jardin = jardin;
		this.piscina = piscina;
		this.wifi = wifi;
		this.aireAcondicionado = aireAcondicionado;
		this.parking = parking;
		this.calefaccion=calefaccion;

		//this.puntuacionPro = puntuacionPro;
	}

	// Getters and Setters
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

	public int getNBaños() {
		return nBaños;
	}

	public void setNBaños(int nBaños) {
		this.nBaños = nBaños;
	}

	public int getPuntuacionGente() {
		return puntuacionGente;
	}

	public void setPuntuacionGente(int puntuacionGente) {
		this.puntuacionGente = puntuacionGente;
	}

	public int getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(int precioNoche) {
		this.precioNoche = precioNoche;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public Boolean getJardin() {
		return jardin;
	}

	public void setJardin(Boolean jardin) {
		this.jardin = jardin;
	}

	public Boolean getPiscina() {
		return piscina;
	}

	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(Boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public Boolean getParking() {
		return parking;
	}

	public void setParking(Boolean parking) {
		this.parking = parking;
	}

	public int getPuntuacionPro() {
		return puntuacionPro;
	}

	public void setPuntuacionPro(int puntuacionPro) {
		this.puntuacionPro = puntuacionPro;
	}

	public int getDistanciaCentro() {
		return distanciaCentro;
	}

	public void setDistanciaCentro(int distanciaCentro) {
		this.distanciaCentro = distanciaCentro;
	}

	public Boolean getCalefaccion() {
		return calefaccion;
	}

	public void setCalefaccion(Boolean calefaccion) {
		this.calefaccion = calefaccion;
	}
	public String toString() {
		return "Hotel "+ nombreHotel +" en " + ciudad + ": " +
				"Capacidad para " + nPersonas + " personas, " +
				nBaños + " baños, " +
				"Puntuación de los huéspedes: " + puntuacionGente + ", " +
				"Precio por noche: " + precioNoche + "€, " +
				"Tamaño: " + m2 + " m², " +
				"Distancia al centro: " + distanciaCentro + " metros, " +
				"Jardín: " + jardin + ", " +
				"Piscina: " + piscina + ", " +
				"WiFi: " + wifi + ", " +
				"Aire acondicionado: " + aireAcondicionado + ", " +
				"Parking: " + parking + ", " +
				"Calefacción: " + calefaccion;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
}