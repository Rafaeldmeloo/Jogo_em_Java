package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient  extends Thread{
    
    private InetAddress ipAddress;
    private DatagramSocket socket;
    public static int count = 0;

    public static String MPData = "false 1104 1008 down 0 0 0 0 0 0 ";

    public GameClient(String ipAddress){
        try{
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e){
            e.printStackTrace();
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
        count++;
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length); 
            try {
                socket.receive(packet);
            } catch (IOException e) {

                e.printStackTrace();
            }
            String message = new String(packet.getData());
            if(!message.contains("pong")){
                MPData = message;
            } else {
                System.out.println("SERVER > " + message);
            }
        }
    }

    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 6666);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}