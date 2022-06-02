package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import main.GamePanel;

public class GameClient  extends Thread{
    
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GamePanel GP;
    public static int count = 0;

    public GameClient(GamePanel GP, String ipAddress){
        this.GP = GP;
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
            System.out.println("SERVER > " + message);

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
