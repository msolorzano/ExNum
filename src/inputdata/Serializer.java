/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputdata;

import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author miguelavg
 */
public class Serializer {

    public void serializacion(ArrayList<Aeropuerto> aeropuertos, String arch) {
        
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
    
}
