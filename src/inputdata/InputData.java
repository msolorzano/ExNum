/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

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
        
        int nVuelos = 60; //numero de vuelos que puede variar de acuerdo a la especificacion
        int nMinVuelos = 50;
        int nMinGrafos = 30; //numero de iteraciones como data de entrada osea los grafos
        int maxH = 800; //coordenada maxima en el eje horizontal
        int maxV = 600; //coordenada maxima en el eje vertical
        
        //1. Se obtiene el numero de aeropuertos
        // select count(*) from Aeropuerto
        int nAeropuertos = leerNumAeropuertos();
        
        //2. Se cargan todos los aeropuertos
        Aeropuerto[] aeropuertos = cargarAeropuertos(nAeropuertos);
        
        
        //3. Por cada aeropuerto, se calculara la distancia que se obtiene respecto a los demas
        float[][] mDistancia = calcularMatrizDeDistancias(aeropuertos);
        
        
        //matriz["Aeropuerto1"]["Aeropuerto2"] = aleatorio(0,1) <= (100 - i(100/(nVuelos-1)))/100 ? 1 : 0;
        
        
    }

    private static Aeropuerto[] cargarAeropuertos(int nAeropuertos) {
        Aeropuerto[] aeropuertos = new Aeropuerto[nAeropuertos];
        
        Random rnd = new Random();
        
        for(int i=0; i<nAeropuertos; i++){
            aeropuertos[i].x = rnd.nextDouble()*800;
            aeropuertos[i].y = rnd.nextDouble()*800;
            aeropuertos[i].capacActual = aeropuertos[i].capacMax = rnd.nextInt(400) + 600;
            aeropuertos[i].principal = rnd.nextBoolean();
            
        }        
        
        return aeropuertos;
    }

    private static float[][] calcularMatrizDeDistancias(Aeropuerto[] aeropuertos) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static int leerNumAeropuertos() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
}
