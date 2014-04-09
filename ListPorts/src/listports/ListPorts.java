/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listports;

/**
 *
 * @author jcarrillo
 */

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

public class ListPorts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

// TODO code application logic here
        
                
        String libPathProperty = System.getProperty("java.library.path");
        System.out.println("Path Libreria: " + libPathProperty);
        
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            String type;
            switch (port.getPortType()) {
                case CommPortIdentifier.PORT_PARALLEL:
                    type = "Parallel";
                    break;
                case CommPortIdentifier.PORT_SERIAL:
                    type = "Serial";
                    break;
                default:  /// Shouldn't happen
                    type = "Unknown";
                    break;
            }
            System.out.println(port.getName() + ": " + type);
        }

    }

}
