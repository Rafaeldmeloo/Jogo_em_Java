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
//import net.packets.Packet;
//import net.packets.Packet00Login;
//import net.packets.Packet.PacketTypes;

public class GameServer extends Thread{
    
    private DatagramSocket socket;
    private GamePanel GP;
    private KeyHandler KH;
    public static int count = 0;
    public static InetAddress clientAddress;
    public static int clientPort;

    //private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

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

            //parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            String message = new String(packet.getData());
            System.out.println("CLIENT > " + message);
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

    // private void parsePacket(byte[] data, InetAddress address, int port) {
    //     String message = new String(data).trim();
    //     PacketTypes type = Packet.lookupPacket(message.substring(0,2));
    //     switch(type){
    //         default:
    //         case INVALID:
    //             break;
    //         case LOGIN:
    //             Packet00Login packet = new Packet00Login(data);
    //             System.out.println("[" + address.getHostAddress() + ":" + port + "]"+ packet.getUsername() +" has connected...");

    //             PlayerMP player = null;
    //             System.out.println(address.getHostAddress());
    //             if(address.getHostAddress().equalsIgnoreCase("192.168.1.2")){
    //                 player = new PlayerMP(GP.tileSize*22, GP.tileSize*21, GP, KH, packet.getUsername(), address, port);
    //             } else {
    //                 player = new PlayerMP(GP.tileSize*22, GP.tileSize*21, GP, packet.getUsername(), address, port);
    //             }
    //             if(player != null){
    //                 this.connectedPlayers.add(player);
    //                 GP.player = player;
    //             }
    //             break;
    //         case DISCONNECT:
    //             break;
    //     }
    // }

    public void sendData(byte[] data, InetAddress ipAddress, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public void sendDataToAllClients(byte[] data) {
    //     for(PlayerMP p : connectedPlayers){
    //         sendData(data, p.ipAddress, p.port);
    //     }
    // }

}
