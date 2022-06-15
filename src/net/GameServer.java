package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import entity.PlayerMP;
import main.GamePanel;
import main.KeyHandler;

public class GameServer extends Thread{
    
    private DatagramSocket socket;
    private GamePanel GP;
    private KeyHandler KH;
    public static int count = 0;
    public static InetAddress clientAddress;
    public static int clientPort;

    public static String xy = "1104 1008 ";

    public GameServer(GamePanel GP){
        this.GP = GP;
        try{
            this.socket = new DatagramSocket(6666);
        } catch (SocketException e){
            e.printStackTrace();
        }
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
            if(!message.contains("ping")){
                xy = message;
            } else {
                System.out.println("CLIENT > " + message);
            }
                if(message.trim().equalsIgnoreCase("ping") && count == 0) {
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
                count++;
            }else if(message.trim().equalsIgnoreCase("ping") && count == 1){
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
