package entity;

import java.net.InetAddress;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.awt.Color;
import javax.imageio.ImageIO;

public class PlayerMP extends Entity2{

    public InetAddress ipAddress;
    public int port;

    GamePanel GP;
    //KeyHandler KH;

    public PlayerMP(){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-parado-frente.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
