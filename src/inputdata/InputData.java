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
//        imprimirAeropuertos(aeropuertos);
        
        //3. Por cada aeropuerto, se calculara la distancia que se obtiene respecto a los demas
        ArrayList<ArrayList<Conexion>> conexiones = cargarMatrizDeConexiones(aeropuertos);
        concentrarVuelos(conexiones);
        imprimirMatrizConexiones(conexiones);
        
        
    }
    
    private static void imprimirAeropuertos(ArrayList<Aeropuerto> aeropuertos){

//        Collections.sort(aeropuertos, new Comparator<Aeropuerto>(){
//
//            @Override
//            public int compare(Aeropuerto o1, Aeropuerto o2) {
//                return o1.getCapacMax()-o2.getCapacMax();
//            }
//            
//        });
        
        for(int i=0 ; i< aeropuertos.size(); i++){
            Aeropuerto aux = aeropuertos.get(i);
            System.out.printf("%s\t%f\t%f\t%d\t%d\t%b%n", aux.getNombre(), aux.getX(), aux.getY(),
                    aux.getCapacActual(), aux.getCapacMax(), aux.isPrincipal());
        }        
        
    }
    
    private static void imprimirMatrizConexiones(ArrayList<ArrayList<Conexion>> conexiones){
        
        for (int i=0; i < conexiones.size(); i++){
            //Imprime la ciudad respecto a la cual se analizaran las demas
            ArrayList<Conexion> conexionesI = conexiones.get(i);
            System.out.printf("%s :%n", conexionesI.get(0).aeropuertoInicial.getNombre());
            for(int j=0; j < conexionesI.size(); j++){
                Conexion aux = conexionesI.get(j);
                System.out.printf("---> %s %f %b%n", aux.aeropuertoFinal.getNombre(), aux.distancia, aux.conectado);
            }
        }
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
        return 30; //por dato del profe
    }

    private static ArrayList<ArrayList<Conexion>> cargarMatrizDeConexiones(ArrayList<Aeropuerto> aeropuertos) {

        ArrayList<ArrayList<Conexion>> matrizConexiones = new ArrayList<ArrayList<Conexion>>();
        
        for (int i=0; i<aeropuertos.size(); i++){
            
            ArrayList<Conexion> listaConexionesAeropuertoi = new ArrayList<Conexion>();
            
            // Creo la lista de todos los aeropuertos salvo el aeropuerto que esto analizando
            // y dentro de esta iteracion calcula las distancias de todos respecto a el
            for (int j=0; j<aeropuertos.size(); j++){
                if( j != i ){// Para eso esta este if
                    Conexion conexion = new Conexion();
                    conexion.aeropuertoInicial = aeropuertos.get(i);
                    conexion.aeropuertoFinal = aeropuertos.get(j);
                    conexion.distancia = calcularDistancia(conexion.aeropuertoFinal.getX(), conexion.aeropuertoFinal.getY(),
                                                            conexion.aeropuertoInicial.getX(), conexion.aeropuertoInicial.getY());
                    listaConexionesAeropuertoi.add(conexion);
                }
            }
            
            //antes de meterla a la matriz, deberia ordenar esta lista por distancia
            Collections.sort(listaConexionesAeropuertoi, new Comparator<Conexion>(){

                @Override
                public int compare(Conexion o1, Conexion o2) {
                    return (int)(o1.distancia - o2.distancia);
                }
                
            });
            
            matrizConexiones.add(listaConexionesAeropuertoi);
        }
        
        return matrizConexiones;
        
    }

    private static double calcularDistancia(double aFx, double aFy, double aIx, double aIy) {
        double desplazamientoY = Math.pow(aFy - aIy, 2);
        double desplazamientox = Math.pow(aFx - aIx, 2);
        return Math.sqrt(desplazamientoY + desplazamientox);
    }

    private static void concentrarVuelos(ArrayList<ArrayList<Conexion>> conexiones) {
        Random rnd = new Random(); //inicializo mi random
        double probabilidadInicial = 1 - (1.0 / ((conexiones.size() - 1) * 2)); //la probilidad con la que inicia cada analisis de cada aeropuerto
        for(int i=0 ; i < conexiones.size(); i++){
            ArrayList<Conexion> conexionesI = conexiones.get(i);
            double probabilidad = probabilidadInicial; //esta es la probabilidad inicial, alta para concentrar
//            System.out.println(conexionesI.get(1).aeropuertoInicial.getNombre());
//            System.out.println("Probabilidad Inicial : " + probabilidad);
//            double probabilidad = 1 - (1.0 / conexionesI.size()) + (1.0 / (conexionesI.size() * 2));
//            double probInicial = 1 - (1.0 / (conexionesI.size() *2 ));
            for(int j=0 ; j < conexionesI.size(); j++){
                Conexion conexion = conexionesI.get(j);
                double randomico = rnd.nextDouble();
//                System.out.println(probabilidad + " " + randomico + " " + (randomico < probabilidad));
                if(conexion.aeropuertoInicial.isPrincipal() || conexion.aeropuertoFinal.isPrincipal()){
                    conexion.conectado = randomico < 0.8; //<<-- probabilidad asignada a los aeropuertos principales
                }
                else
                {
                    conexion.conectado = randomico < probabilidad;
                }
                
                probabilidad -= (1.0 / (conexionesI.size())); //actualizo la probabilidad para el siguiente aeropuerto mas lejano
            }
//            System.out.printf("----------------------------------------------------------------------------------------------------%n", null);
        }
    }
    
    
}

