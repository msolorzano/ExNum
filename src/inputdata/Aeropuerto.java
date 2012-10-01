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

    public Aeropuerto(String nombre, double x, double y, int capacMax, int capacActual, boolean principal) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.capacMax = capacMax;
        this.capacActual = capacActual;
        this.principal = principal;
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
    
    
       
}
