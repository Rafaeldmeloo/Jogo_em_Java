package entity;

import java.net.InetAddress;

import net.GameClient;
import net.GameServer;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerMP extends Entity{

    GamePanel GP;

    public InetAddress ipAddress;
    public int port;

    public static String[] objInteraction = {"0", "0", "0", "0", "0", "0"};

    public static String online = "true";
    public static int previousX = 1104; 
    public static int previousY = 1008; 

    public PlayerMP(GamePanel gp){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;

        solidArea         = new Rectangle();
        solidArea.x       = 8;
        solidArea.y       = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width   = 32;
        solidArea.height  = 32;
    }

    public void draw(Graphics2D g2, GamePanel gp, String MPData){
        String[] data = MPData.split(" ");
        online = data[0];
        worldX = Integer.parseInt(data[1]);
        worldY = Integer.parseInt(data[2]);
        direction = data[3];

        objInteraction[0] = data[4];
        objInteraction[1] = data[5];
        objInteraction[2] = data[6];
        objInteraction[3] = data[7];
        objInteraction[4] = data[8];
        objInteraction[5] = data[9];

        if(online.contains("true")){
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            BufferedImage image = null;
            spriteSetter();
            if(previousX == worldX && previousY == worldY){
                spriteNumb = 4;
            }else {
                previousX = worldX;
                previousY = worldY;
            }
            switch(direction){
                case "up" :
                    if(spriteNumb == 1)
                        image = walking_1_back;
                    else if(spriteNumb == 3)
                        image = walking_2_back;
                    else
                        image = standing_back;
                    break;

                case "down" :
                    if(spriteNumb == 1)
                        image = walking_1_front;
                    else if(spriteNumb == 3)
                        image = walking_2_front;
                    else
                        image = standing_front;
                    break;

                case "left" :
                    if(spriteNumb == 1)
                        image = walking_1_left;
                    else if(spriteNumb == 3)
                        image = walking_2_left;
                    else
                        image = standing_left;
                    break;

                case "right":   
                    if(spriteNumb == 1)
                        image = walking_1_right;
                    else if(spriteNumb == 3)
                        image = walking_2_right;
                    else
                        image = standing_right;
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void getPlayerImage(){
        if(GameServer.count == 0 && GameClient.count == 1){
            try{
                standing_front       = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_front.png"));
                walking_1_front    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_front.png"));
                walking_2_front    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_front.png"));

                standing_back       = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_back.png"));
                walking_1_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_back.png"));
                walking_2_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_back.png"));

                standing_left     = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_left.png"));
                walking_1_left  = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_left.png"));
                walking_2_left  = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_left.png"));

                standing_right      = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_right.png"));
                walking_1_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_right.png"));
                walking_2_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_right.png"));


            }catch(IOException e){
                e.printStackTrace();
            }

        }else{
            try{
                standing_front       = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_front.png"));
                walking_1_front    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_front.png"));
                walking_2_front    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_front.png"));

                standing_back       = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_back.png"));
                walking_1_back    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_back.png"));
                walking_2_back    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_back.png"));

                standing_left     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_left.png"));
                walking_1_left  = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_left.png"));
                walking_2_left  = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_left.png"));

                standing_right      = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_right.png"));
                walking_1_right   = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_right.png"));
                walking_2_right   = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_right.png"));
    
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void spriteSetter(){
        if(direction.contains("up")){
            direction = "up";
        }else if(direction.contains("down")){
            direction = "down";
        }else if(direction.contains("left")){
            direction = "left";
        }else if(direction.contains("right")){
            direction = "right";
        }

        if(collisionOn == false){
            switch(direction){
                case "up":
                    worldY -= speed;
                    break;

                case "down":
                    worldY += speed;
                    break;

                case "left":
                    worldX -= speed;
                    break;

                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNumb == 1)
                spriteNumb = 2;
            else if(spriteNumb == 2)
                spriteNumb = 3;
            else if(spriteNumb == 3)
                spriteNumb = 4;
            else
                spriteNumb = 1; 

            spriteCounter = 0;
        }     
    }

}


