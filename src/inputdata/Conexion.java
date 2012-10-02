/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;
import java.util.*;
/**
 *
 * @author msolorzano
 */
public class Conexion {
    Aeropuerto  aeropuertoInicial;
    Aeropuerto  aeropuertoFinal;
    double      distancia;
    boolean     conectado;
    ArrayList<Vuelo> listaVuelos;

    public Conexion() {
        listaVuelos = new ArrayList<Vuelo>();
    }
    
}