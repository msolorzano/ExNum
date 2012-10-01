/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

import java.sql.Time;
import java.util.*;


/**
 *
 * @author msolorzano
 */
public class InputData {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int nMinVuelos = 50;
        int nMinGrafos = 30; //numero de iteraciones como data de entrada osea los grafos
        int maxH = 800; //coordenada maxima en el eje horizontal
        int maxV = 600; //coordenada maxima en el eje vertical
        
        //1. Se obtiene el numero de aeropuertos
        // select count(*) from Aeropuerto
        int nAeropuertos = leerNumAeropuertos();
        
        //2. Se cargan todos los aeropuertos
        ArrayList<Aeropuerto> aeropuertos = cargarAeropuertos(nAeropuertos, maxH, maxV);
        
        Collections.sort(aeropuertos, new Comparator<Aeropuerto>(){

            @Override
            public int compare(Aeropuerto o1, Aeropuerto o2) {
                return o1.getCapacMax()-o2.getCapacMax();
            }
            
        });
        
        for(int i=0 ; i< aeropuertos.size(); i++){
            Aeropuerto aux = aeropuertos.get(i);
            System.out.printf("%s\t%f\t%f\t%d\t%d\t%b%n", aux.getNombre(), aux.getX(), aux.getY(),
                    aux.getCapacActual(), aux.getCapacMax(), aux.isPrincipal());
        }
        
        //3. Por cada aeropuerto, se calculara la distancia que se obtiene respecto a los demas
        ArrayList<ArrayList<Conexion>> conexiones = cargarMatrizDeConexiones();
        
        //matriz["Aeropuerto1"]["Aeropuerto2"] = aleatorio(0,1) <= (100 - i(100/(nVuelos-1)))/100 ? 1 : 0;
        
        
    }

    private static ArrayList<Aeropuerto> cargarAeropuertos(int nAeropuertos, int maxH, int maxV) {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
        
        Random rnd = new Random();
        
        for(int i=0; i<nAeropuertos; i++){
            int capac = rnd.nextInt(400) + 600;
            
            Aeropuerto aux = new Aeropuerto(
            "Aeropuerto " + i, //nombre del aeropuerto
            rnd.nextDouble() * maxH, // coordenada respecto al eje X
            rnd.nextDouble() * maxV, // coordenada respecto al eje Y
            capac, //cuando el aeropuerto se crea su capacidad maxima
            capac, //es igual a su capacidad actual
            (rnd.nextDouble() * 500 ) < 25 ? true : false); //si es un aeropuerto principal, esto es para que tenga poca probabilidad de salir principal
            
            aeropuertos.add(aux);
        }        
        
        return aeropuertos;
    }

    private static int leerNumAeropuertos() {
        return 60; //por dato del profe
    }

    private static ArrayList<ArrayList<Conexion>> cargarMatrizDeConexiones() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
}

