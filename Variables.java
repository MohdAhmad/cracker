/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cracker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hammad
 */
public class Variables {

    boolean breakLoop = false;
    int ConvenerPort;
    InetAddress ConvenerAddr;

    public Variables() {
        while (!breakLoop) {
            try {
                ConvenerPort = 9999;
                ConvenerAddr = InetAddress.getByName("localhost");


                breakLoop = true;
            } catch (UnknownHostException ex) {
                Logger.getLogger(Variables.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception in Variables.java - reattempting.");
            }
        }

    }
}
