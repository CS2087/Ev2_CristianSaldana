package Clases;

import java.io.Serializable;

public class ListClientes implements Serializable {

    private String nombre;
    private int dinero;

    //constructor
    public ListClientes(String nombre, int dinero)
    {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
