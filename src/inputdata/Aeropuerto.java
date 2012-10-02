/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

/**
 *
 * @author msolorzano
 */
public class Aeropuerto {
    private int idAeropuerto;
    private String nombre;
    private double x;
    private double y;
    private int capacMax;
    private int capacActual;
    private boolean principal;
    private double costoAlmacen;
    private String ciudad;
    private String continente;
    private boolean flag = false; //para marcar si ya pasaste por ahi o no GRASP

    public Aeropuerto(String nombre, double x, double y, int capacMax, int capacActual, boolean principal) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.capacMax = capacMax;
        this.capacActual = capacActual;
        this.principal = principal;
    }

    public Aeropuerto(String nombre, double x, double y, int capacMax, int capacActual, 
            boolean principal, double costoAlmacen, String ciudad, String continente) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.capacMax = capacMax;
        this.capacActual = capacActual;
        this.principal = principal;
        this.costoAlmacen = costoAlmacen;
        this.ciudad = ciudad;
        this.continente = continente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCapacMax() {
        return capacMax;
    }

    public int getCapacActual() {
        return capacActual;
    }

    public void setCapacActual(int capacActual) {
        this.capacActual = capacActual;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public double getCostoAlmacen() {
        return costoAlmacen;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getContinente() {
        return continente;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    
       
}
