/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;
import java.util.Date;
        
/**
 *
 * @author msolorzano
 */
public class Vuelo {
    
    private int idVuelo;
    private int idOrigen;
    private int idDestino;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Date fSalida;
    private Date fLlegada;
    private int capacEnvioMax;
    private int capacEnvioUsado;
    private double costoAlquiler;
    private double tiempoAlmacenaje; //tiempo maximo de almacenaje 12 horas
//    private double tVuelo; generar un metodo que a partir de diferencia de fechas genere la diferencia en tiempo en milisegundos

    public Vuelo(Aeropuerto origen, Aeropuerto destino, Date fSalida, Date fLlegada, int capacEnvioMax, int capacEnvioUsado, double costoAlquiler) {
        this.origen = origen;
        this.destino = destino;
        this.fSalida = fSalida;
        this.fLlegada = fLlegada;
        this.capacEnvioMax = capacEnvioMax;
        this.capacEnvioUsado = capacEnvioUsado;
        this.costoAlquiler = costoAlquiler;
    }

    public Vuelo() {
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public Date getfSalida() {
        return fSalida;
    }

    public void setfSalida(Date fSalida) {
        this.fSalida = fSalida;
    }

    public Date getfLlegada() {
        return fLlegada;
    }

    public void setfLlegada(Date fLlegada) {
        this.fLlegada = fLlegada;
    }

    public int getCapacEnvioMax() {
        return capacEnvioMax;
    }

    public void setCapacEnvioMax(int capacEnvioMax) {
        this.capacEnvioMax = capacEnvioMax;
    }

    public int getCapacEnvioUsado() {
        return capacEnvioUsado;
    }

    public void setCapacEnvioUsado(int capacEnvioUsado) {
        this.capacEnvioUsado = capacEnvioUsado;
    }

    public double getCostoAlquiler() {
        return costoAlquiler;
    }

    public void setCostoAlquiler(double costoAlquiler) {
        this.costoAlquiler = costoAlquiler;
    }
    
    
    
    
}
