package Clases;

import java.io.Serializable;

public class ListPrestamos implements Serializable {

    //declaro los datos
    private String nombrePrestamo;
    private int montoPrestamo;
    private int cuotas;

    //Constructor
    public ListPrestamos(String nombreCredito, int montoCredito, int cuotas)
    {
        this.nombrePrestamo = nombreCredito;
        this.montoPrestamo = montoCredito;
        this.cuotas = cuotas;
        /* creditoHipotecario = "1000000";
        creditoAutomotriz = "500000"; */
    }

    //Genero los Accesadores
    public String getNombrePrestamo() {
        return nombrePrestamo;
    }

    public void setNombrePrestamo(String nombrePrestamo) {
        this.nombrePrestamo = nombrePrestamo;
    }

    public int getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(int montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }


}
