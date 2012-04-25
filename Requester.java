/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cracker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hammad
 */
public class Requester {

    public static void main(String args[]) {
        
        int convenerSeemsAlive = 3;

        while (convenerSeemsAlive>0) {
            try { 

                byte[] receiveData = new byte[1024];

               
                // listening and sending socket
                DatagramSocket requesterSocket = new DatagramSocket();  // throws socket exception
                requesterSocket.setSoTimeout(10000); // throws socket exception
                
                // Sending request to convener
                iRequest(requesterSocket, "requestHere");


                // Getting reply from convener
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                requesterSocket.receive(receivePacket);

                String recievedStr = new String(receivePacket.getData());
                System.out.println("From Convener:" + recievedStr);
                requesterSocket.close();
                break;

            } catch (InterruptedException ex) {
                Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("InterruptedException in Requester.java");
                convenerSeemsAlive--;
            } catch (SocketException ex) {
                Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SocketException in Requester.java");
                convenerSeemsAlive--;
            } catch (IOException ex) {
                Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("IOException in Requester.java");
                convenerSeemsAlive--;
            }
        }

    }

    
    private static void iRequest(DatagramSocket soc, String request) throws IOException, InterruptedException {
        //while (true) {
            Variables vars = new Variables();

            String msgForConvener = "Hello, I requester";
            byte[] sendData = msgForConvener.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, vars.ConvenerAddr, vars.ConvenerPort);
            soc.send(sendPacket);


            //Thread.sleep(20000);
        //}
    }
}

/*
 * REQUESTER should prediocally ping convener. If 3 successive pings fail, then
 * exit program and report that, "the convener seems to have crashed"
 *
 */
