/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cracker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Hammad
 */
public class Volunteers {

    public static void main(String args[]) throws Exception {
        Variables vars = new Variables();

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        // listening and sending socket
        DatagramSocket volunteerSocket = new DatagramSocket();

        // Sending request to convener
        iVolunteer(volunteerSocket);

        // message from convener
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        volunteerSocket.receive(receivePacket);

        String recievedStr = new String(receivePacket.getData());
        System.out.println("From Convener:" + recievedStr);
        volunteerSocket.close();
        //analyze convener's message
        //start crack()
        //send completion message
    }

    // this should run on a thread in volunteer    
    public void iAlive(DatagramSocket soc) throws IOException, InterruptedException {
        while (true) {
            Variables vars = new Variables();

            String msgForConvener = "Hello, I am alive";
            byte[] sendData = msgForConvener.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, vars.ConvenerAddr, vars.ConvenerPort);
            soc.send(sendPacket);


            Thread.sleep(20000);
        }
    }

    // this should run when the volunteer starts    
    private static void iVolunteer(DatagramSocket soc) throws IOException, InterruptedException {
        Variables vars = new Variables();


        long speed = MySpeed();
        String msgForConvener = "Hello, I Volunteer - my speed is: " + speed;
        byte[] sendData = msgForConvener.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, vars.ConvenerAddr, vars.ConvenerPort);
        soc.send(sendPacket);


        //Thread.sleep(20000);

    }

    //returns computer speed (millisec to compute... )
    private static long MySpeed() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        String hashCode = Password.crypt("testing", "ab");
        for (int i = 0; i < 20000; i++) {
            if (hashCode.equals(Password.crypt(Integer.toString(i), "ab"))) {
                //
            }
        }
        stopwatch.stop();
        System.out.println(stopwatch);

        return stopwatch.toValue();
    }


    // returns password. null if no password found.
    public static String crack(String hashCode, int start, int end, String Salt) {
        
        String answer = null;
        for (int pass = start; pass <= end; pass++) {            
            if (hashCode.equals(Password.crypt(Integer.toString(pass), Salt))) {
                answer = Integer.toString(pass);
                break;
            }
        }
        
        return answer;
    }
}