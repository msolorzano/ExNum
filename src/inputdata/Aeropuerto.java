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
    public String nombre;
    public double x;
    public double y;
    public int capacMax;
    public int capacActual;
    public boolean principal;
    public String ciudad;
    
    public Aeropuerto(){
        
    }
    
    public Aeropuerto(int x, int y, int capacMax, int capacActual, boolean principal, String ciudad){
        this.x = x;
        this.y = y;
        this.capacMax = capacMax;
        this.capacActual = capacActual;
        this.principal = principal;
        this.ciudad = ciudad;
    }
    
}
