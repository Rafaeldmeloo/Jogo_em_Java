package entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import main.GamePanel;
import net.GameClient;

public class Entity2 {
    public int worldX, worldY;
    public int speed;
    public String direction;
    public BufferedImage image;

        public void draw(Graphics2D g2, GamePanel gp){
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    
}
