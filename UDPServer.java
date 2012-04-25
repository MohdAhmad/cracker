/**
 * SERVER: 
 * 
 * @author Hammad
 */


package cracker;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            while(true)
               {
                  System.out.println("Waiting for packet...");
                  serverSocket.receive(receivePacket);
                  
                  String sentence = new String( receivePacket.getData() );
                  System.out.println("got:" + sentence);
                  System.out.println("addr: " + receivePacket.getSocketAddress().toString());
                  
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  
                  String capitalizedSentence = sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  
                  serverSocket.send(sendPacket);
               }
      }
}