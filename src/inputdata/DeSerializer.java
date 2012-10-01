/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

import java.util.ArrayList;

/**
 *
 * @author miguelavg
 */
public class DeSerializer {

    public static void main(String[] args) {
        
        // Aeropuerto(int x, int y, int capacMax, int capacActual, boolean principal, String ciudad)
        
        int nAeropuerto = 4;
        ArrayList<Aeropuerto> a = new ArrayList<Aeropuerto>();
        
        a.add(new Aeropuerto("Vega", 0, 0, 500, 0, true, "Lima"));
        a.add(new Aeropuerto("Solórzano", 100, 100, 1000, 0, true, "Miami"));
        a.add(new Aeropuerto("Juárez", 0, 100, 400, 0, false, "Bruselas"));
        a.add(new Aeropuerto("Chávez", 100, 0, 600, 0, false, "Dakar"));
        
        Serializer serial = new Serializer();
        
        serial.serializacion(a, "miguel.xml");
    }
}
