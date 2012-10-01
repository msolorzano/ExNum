/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

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

    public void serializa(ArrayList<Aeropuerto> aeropuertos, String arch) {

        XStream xstream = new XStream();
        String xml = xstream.toXML(aeropuertos);

        try {
            FileWriter fw = new FileWriter(arch);
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public ArrayList<Aeropuerto> deserializa(String nombreArch) {

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
}
