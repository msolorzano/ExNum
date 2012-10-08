/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author msolorzano
 */
public class InputData {
    
    //indica cual es la probabilidad que un aeropuerto salga principal
    private static int numMinAeropuertos = 60;
    private static int numMaxAeropuertos = 100;
    
    private static int probAeropuertoPrincipal = 5; 
    
    private static int capacMaxAvion = 300;
    private static int capacMinAvion = 200;
    
    private static int capacMaxAlmacenAeropuerto = 1000;
    private static int capacMinAlmacenAeropuerto = 600;
    
    private static int costoMaxAvion = 600;
    private static int costoMinAvion = 400;
    
    private static int maxH = 800;
    private static int maxV = 600;
    
    private static int distanciaMaxDeVuelo;
    private static int tiempoMaxDeVuelo = 24;
    
    private static int numeroCasosAProbar = 40;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        //Calcular la maxima distancia en el mapa
        distanciaMaxDeVuelo = (int) Math.sqrt( Math.pow(maxH, 2) + Math.pow(maxV, 2));
        
        Random rnd = new Random();
        
        for(int i=0; i<numeroCasosAProbar; i++){
//            int nAeropuertos = rnd.nextInt(numMaxAeropuertos-numMinAeropuertos+1)+ numMinAeropuertos;
            int nAeropuertos = numMinAeropuertos;
            //Se cargan todos los aeropuertos
            ArrayList<Aeropuerto> aeropuertos = cargarAeropuertos(nAeropuertos);
            //imprimirAeropuertos(aeropuertos);


            //Por cada aeropuerto, se calculara la distancia que se obtiene respecto a los demas
            Calendar calAuxiliar = Calendar.getInstance();

            ArrayList<ArrayList<Conexion>> conexiones = cargarMatrizDeConexiones(aeropuertos);
            concentrarVuelos(conexiones, calAuxiliar);
            ArrayList<Vuelo> vuelos = cargarVuelos(conexiones);
            
            System.out.println(vuelos.size());

            calAuxiliar.add(Calendar.DAY_OF_YEAR, 1);

            conexiones = cargarMatrizDeConexiones(aeropuertos);
            concentrarVuelos(conexiones, calAuxiliar);
            vuelos.addAll(cargarVuelos(conexiones));
            //imprimirMatrizConexiones(conexiones);

            System.out.println(vuelos.size());
            
            ArrayList<Envio> envios = cargarEnvios(nAeropuertos);

//            Serializer xml2file = new Serializer();
//            xml2file.serializa(aeropuertos, "xml/aeropuertos"+i+".xml");
//            xml2file.serializa(vuelos, "xml/vuelos"+i+".xml");
//            xml2file.serializa(envios, "xml/envios"+i+".xml");
        }
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
            System.out.printf("%s\t%f\t%f\t%d\t%d\t%b\t%f%n", aux.getNombre(), aux.getX(), aux.getY(),
                    aux.getCapacActual(), aux.getCapacMax(), aux.isPrincipal(), aux.getCostoAlmacen());
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
                for(int k=0; k < aux.listaVuelos.size(); k++){
                    Vuelo vuelo = aux.listaVuelos.get(k);
                    System.out.println("********* Fecha Salida: " + vuelo.getfSalida());
                    System.out.println("********* Fecha Llegada: " + vuelo.getfLlegada());
                    System.out.println("********* Capacidad Envio Maxima: " + vuelo.getCapacEnvioMax());
                    System.out.println("********* Costo Alquiler: " + vuelo.getCostoAlquiler());
                    System.out.println("********* Tiempo Almacenaje: " + vuelo.getTiempoAlmacenaje());
                    System.out.println("********* Tiempo De Vuelo: " + vuelo.gettVuelo());
                }
            }
        }
    }

    private static ArrayList<Aeropuerto> cargarAeropuertos(int numAeropuertos) {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
        
        Random rnd = new Random();
        
        for(int i=0; i < numAeropuertos; i++){ 
            int capac = rnd.nextInt(capacMaxAlmacenAeropuerto - capacMinAlmacenAeropuerto) + capacMinAlmacenAeropuerto;
            
            Aeropuerto aux = new Aeropuerto(
                i + 1,
                "Aeropuerto " + i+1, //nombre del aeropuerto
                rnd.nextDouble() * maxH, // coordenada respecto al eje X
                rnd.nextDouble() * maxV, // coordenada respecto al eje Y
                capac, //capacidad maxima de paquetes del almacen del aeropuerto
                0, //capacidad ocupada de paquetes del almacen del aeropuerto
                (rnd.nextDouble() * 100 ) < probAeropuertoPrincipal ? true : false, //si es un aeropuerto principal, esto es para que tenga poca probabilidad de salir principal
                (rnd.nextInt(3) + 3.0) / 100, //costo por almacenar paquetes entre 0.03 y 0.05 unidades monetarias
                null, //ciudad
                null); //continente
            
            aeropuertos.add(aux);
        }        
        
        return aeropuertos;
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

    private static void concentrarVuelos(ArrayList<ArrayList<Conexion>> conexiones, Calendar calendarioEntrada) {
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
                /*
                 * Si alguno de los aeropuertos en donde se quiere generar la conexion es un aeropuerto principal
                 * puede ser el aeropuerto de salida como el aeropuerto de llegada, entonces la probabilidad deberia aumentar
                 * y ser distinta pues por ser aeropuerto principal debemos darle mas opcion a que se de la conexion
                 */
                if(conexion.aeropuertoInicial.isPrincipal() || conexion.aeropuertoFinal.isPrincipal()){
//                    System.out.println("0.8" + " " + randomico + " " + (randomico < 0.8));
                    conexion.conectado = randomico < 0.8; //<<-- probabilidad asignada a los aeropuertos principales
                }
                else
                {
//                    System.out.println(probabilidad + " " + randomico + " " + (randomico < probabilidad));
                    conexion.conectado = randomico < probabilidad;
                }
                
                /*
                 * Una vez generado mediante la aleatoriedad si va a haber conexion entre los
                 * 2 aeropuertos que se estan analizando, pasamos a generar el numero de vuelos que
                 * hay en esos 2 aeropuertos durante el dia de hoy, teniendo en consideracion el continente
                 * en el que se encuentran
                 */
                if (conexion.conectado){ //ya hay vuelos entre los aeropuertos ahora falta indicar cuantos
                    int numVuelos = 1; //lo inicializo con 1 porque falta indicar a los aeropuertos sus continentes
                    
//                    if(conexion.aeropuertoInicial.getContinente().equals(conexion.aeropuertoFinal.getContinente())){
//                        //Si los aeropuertos pertenecen al mismo continente, se genera un vuelo                        
//                        numVuelos = 1;
//                    }
//                    else{
//                        //Si los aeropuertos pertenecen a distintos continentes, 1 o 2
//                        numVuelos = rnd.nextInt(1) + 1;
//                    }
                    
                    /*
                     * Aqui se generan las horas de salida y de llegada de cada vuelo, la hora de salida se genera
                     * a traves de una hora y minutos aleatorios y la hora de llegada a partir de la distancia
                     * entre los aeropuertos en base al tiempo de demora maximo que se podria dar (reglas del problema)
                     * con la ruta o distancia maxima que podria haber (dentro del mapa)
                     */
                    for(int k = 0; k < numVuelos; k++){

                        //Se crea aleatoriamente la fecha de salida y de llegada
                        Calendar fechaAux = (Calendar)calendarioEntrada.clone();
                        fechaAux.set(Calendar.HOUR_OF_DAY, rnd.nextInt(24)); //genera una hora aleatorio de 0 - 23
                        fechaAux.set(Calendar.MINUTE, rnd.nextInt(3) * 15); //genera minutos aleatorios entre 0 - 15 - 30 - 45
                        fechaAux.set(Calendar.SECOND, 0);
                        Date fSalida = fechaAux.getTime();
                        int tiempoVuelo = (int) (3600 * 1000 * conexion.distancia * tiempoMaxDeVuelo / distanciaMaxDeVuelo); 
                        //si este tiempo supera las 12 o 24 horas, segun corresponda, esta mal, podria ser un try catch 
                        //con un log que indique cuando sucede esto
                        fechaAux.add(Calendar.MILLISECOND, tiempoVuelo);
                        Date fLlegada = fechaAux.getTime();

                        
                        //Se crea el vuelo
                        Vuelo vuelo = new Vuelo(
                                conexion.aeropuertoInicial, //aeropuerto de inicio del vuelo
                                conexion.aeropuertoFinal, //aeropuerto de llegada del vuelo
                                fSalida, //fecha de salida del vuelo
                                fLlegada, //fecha de llegada del vuelo
                                rnd.nextInt(capacMaxAvion-capacMinAvion) + capacMinAvion, //capacidad maxima de paquetes del vuelo
                                0, //capacidad utilizada de paquetes del vuelo
                                rnd.nextInt(costoMaxAvion-costoMinAvion) + costoMinAvion, //costo de alquiler del avion
                                rnd.nextInt(12) + 1, //tiempo de almacenaje de los paquetes
                                tiempoVuelo / 3600000.0); // tiempo de vuelo en horas

//                        System.out.println(vuelo.getOrigen().getNombre() + " " + vuelo.getDestino().getNombre()
//                                + " "  + vuelo.getfSalida() + " " + vuelo.getfLlegada() + " " + vuelo.getCapacEnvioMax() 
//                                + " " + vuelo.getCostoAlquiler() + " " + vuelo.getTiempoAlmacenaje() + " " + vuelo.gettVuelo());
                        
                        //Se agrega el vuelo a la lista de vuelos que tendra dicha conexion ya valida
                        conexion.listaVuelos.add(vuelo);
                    }
                }
                
                probabilidad -= (1.0 / (conexionesI.size())); //actualizo la probabilidad para el siguiente aeropuerto mas lejano
            }
//            System.out.printf("----------------------------------------------------------------------------------------------------%n", null);
        }
    }

    private static ArrayList<Vuelo> cargarVuelos(ArrayList<ArrayList<Conexion>> conexiones) {
        ArrayList<Vuelo> resultado = new ArrayList<Vuelo>();
        
        for(int i=0; i<conexiones.size(); i++){
            ArrayList<Conexion> conexionesI = conexiones.get(i);
            for(int j=0; j<conexionesI.size(); j++){
                Conexion conexionAux = conexionesI.get(j);
                for(int k=0; k<conexionAux.listaVuelos.size(); k++){
                    Vuelo vuelo = conexionAux.listaVuelos.get(k);
                    vuelo.setIdOrigen(conexionAux.aeropuertoInicial.getIdAeropuerto());
                    vuelo.setIdDestino(conexionAux.aeropuertoFinal.getIdAeropuerto());
                    resultado.add(vuelo);
                }
            }
        }
        
        return resultado;
    }

    private static ArrayList<Envio> cargarEnvios(int numAeropuertos) {
        ArrayList<Envio> envios = new ArrayList<Envio>();
        Random rnd = new Random();
        
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DAY_OF_YEAR, -1);
        Date fecha = cal.getTime();
        
        Envio envio = new Envio();
        envio.idAeropuertoInicio = rnd.nextInt(numAeropuertos)+1;
        envio.idAeropuertoFin = rnd.nextInt(numAeropuertos)+1;
        envio.fecha = fecha;
        envios.add(envio);
        
        return envios;
    }
    
    
}

