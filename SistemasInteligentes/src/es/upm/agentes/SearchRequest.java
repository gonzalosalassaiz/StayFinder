package es.upm.agentes;
import java.io.Serializable;
import java.util.Date;


public class SearchRequest implements Serializable{
    /**
 * 
 */
private static final long serialVersionUID = 1L;
private String ciudad;
    private int nPersonas;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Integer precioMin;
    private Integer precioMax;
    private String tipoViaje;

    // Constructor vacío
    public SearchRequest() {
    }

    // Constructor con todos los parámetros
    public SearchRequest(String ciudad, int nPersonas, Date fechaEntrada, Date fechaSalida, Integer precioMin,
                         Integer precioMax, String tipoViaje) {
        this.ciudad = ciudad;
        this.nPersonas = nPersonas;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.setTipoViaje(tipoViaje);
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

    public Integer getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Integer precioMin) {
        this.precioMin = precioMin;
    }

    public Integer getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Integer precioMax) {
        this.precioMax = precioMax;
    }

public String getTipoViaje() {
return tipoViaje;
}

public void setTipoViaje(String tipoViaje) {
this.tipoViaje = tipoViaje;
}
}