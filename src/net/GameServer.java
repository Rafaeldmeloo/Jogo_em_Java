package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class GameServer extends Thread{
    
    private DatagramSocket socket;
    public static int count = 0;
    public static InetAddress clientAddress;
    public static int clientPort;

    public static String MPData = "true 1104 1008 down ";

    public GameServer(){
        try{
            this.socket = new DatagramSocket(6666);
        } catch (SocketException e){
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

            clientAddress = packet.getAddress(); 
            clientPort = packet.getPort();

            String message = new String(packet.getData());
            if(!message.contains("ping")){
                MPData = message;
            } else {
                System.out.println("CLIENT > " + message);
            }
                if(message.trim().equalsIgnoreCase("ping") && count == 1) {
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
                count++;
            }else if(message.trim().equalsIgnoreCase("ping") && count == 2){
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
                clientAddress = packet.getAddress(); 
                clientPort = packet.getPort(); 
                count++;
            }
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
