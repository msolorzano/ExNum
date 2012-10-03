/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author miguelavg
 */
public class Serializer {

    public void serializa(ArrayList lista, String arch) {

        XStream xstream = new XStream();
        String xml = xstream.toXML(lista);

        try {
            FileWriter fw = new FileWriter(arch);
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public ArrayList<Aeropuerto> aDeserializa(String nombreArch) {

        ArrayList<Aeropuerto> aeropuertos = null;
                
        try {
            XStream xstream = new XStream();
            FileReader fr = new FileReader(nombreArch);
            aeropuertos = (ArrayList<Aeropuerto>) xstream.fromXML(fr);
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return aeropuertos;
    }
    
    public ArrayList<Vuelo> vDeserializa(String nombreArch) {

        ArrayList<Vuelo> vuelos = null;
                
        try {
            XStream xstream = new XStream();
            FileReader fr = new FileReader(nombreArch);
            vuelos = (ArrayList<Vuelo>) xstream.fromXML(fr);
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return vuelos;
    }
}
