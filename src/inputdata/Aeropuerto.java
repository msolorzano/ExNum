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
    public int x;
    public int y;
    public int capacMax;
    public int capacActual;
    public boolean principal;
    public String ciudad;
    
    public Aeropuerto(int x, int y, int capacMax, int capacActual, boolean principal, String ciudad){
        this.x = x;
        this.y = y;
        this.capacMax = capacMax;
        this.capacActual = capacActual;
        this.principal = principal;
        this.ciudad = ciudad;
    }
    
}
