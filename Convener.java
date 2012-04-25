/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cracker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Convener {

    public static void main(String args[]) throws Exception {
        Variables vars = new Variables();
        
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        // listening and sending socket
        DatagramSocket convenerSocket = new DatagramSocket(vars.ConvenerPort);

        // getting request
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            while(true)
               {
                  System.out.println("Waiting for a request...");
                  convenerSocket.receive(receivePacket);
                  
                  String sentence = new String( receivePacket.getData() );
                  System.out.println("recieved:" + sentence);
                  System.out.println("addr: " + receivePacket.getSocketAddress().toString());
                  
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  
                  String capitalizedSentence = "hey requester, i got ur msg: " + sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  
                  convenerSocket.send(sendPacket);
               }
    }
}

/*
 * Convener should have multiple threads that share variables
 * Thread one: listens to requests from REQUESTER(s) - adds the hash to the TODO list
 * Thread two: listens for volunteer join requests(includes system speed) and alive status
 * Thread three: gets the HASH from the TODO list and distributes work to 
 * Thread three: periodically pings workers to see if they are alive
 * 
 */