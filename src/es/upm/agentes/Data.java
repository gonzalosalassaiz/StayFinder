package es.upm.agentes;

import java.io.Serializable;
import java.util.ArrayList;


public class Data implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    //Fichero de datos analizar que ha cargado el usuario en el interfaz
    ArrayList<Hotel> list;
    //Utilizamos un único método de clasificación que ha seleccionado el usuario en el interfaz
    SearchRequest sr;
    public Data() {
        
    }
    public Data(ArrayList<Hotel> list,    SearchRequest sr){
        this.list=list;
        this.sr=sr;
    }
    protected ArrayList<Hotel> getList()
    {
    return list;
    }
    protected void setFile(ArrayList<Hotel> list)
    {
    this.list = list;
    }
    protected SearchRequest getSR()
    {
    return sr;
    }
    protected void setSR (    SearchRequest sr)
    {
    this.sr = sr;
    }

}