package entity;

import main.GamePanel;
import main.KeyHandler;
import net.GameClient;
import net.GameServer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel GP;
    KeyHandler KH;

    public final int screenX;
    public final int screenY;
    
    public String packetDirection; 

    public Player(int x, int y, GamePanel GP, KeyHandler KH){
        this.GP = GP;
        this.KH = KH;

        screenX = GP.screenWidth/2 - (GP.tileSize/2);
        screenY = GP.screenHeight/2 - (GP.tileSize/2);

        solidArea        = new Rectangle();
        solidArea.x      = 8;
        solidArea.y      = 16;
        solidArea.width  = 32;
        solidArea.height = 32;


        setDefaultValues(x, y);
        //getPlayerImage();
    }

    public void setDefaultValues(int x, int y){
        worldX      = x;
        worldY      = y;
        speed       = 3;
        direction   = "down";
    }

    public void getPlayerImage(){

        if(GameServer.count == 0 && GameClient.count == 1){
            try{
                standing_front     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_front.png"));
                walking_1_front    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_front.png"));
                walking_2_front    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_front.png"));

                standing_back      = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_back.png"));
                walking_1_back     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_back.png"));
                walking_2_back     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_back.png"));

                standing_left      = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_left.png"));
                walking_1_left     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_left.png"));
                walking_2_left     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_left.png"));

                standing_right     = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_standing_right.png"));
                walking_1_right    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_leftFoot_right.png"));
                walking_2_right    = ImageIO.read(getClass().getResourceAsStream("/res/player2/griffith_rightFoot_right.png"));

            }catch(IOException e){
                e.printStackTrace();
            }

        }else{
            try{
                standing_front    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_front.png"));
                walking_1_front   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_front.png"));
                walking_2_front   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_front.png"));
    
                standing_back     = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_back.png"));
                walking_1_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_back.png"));
                walking_2_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_back.png"));
    
                standing_left     = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_left.png"));
                walking_1_left    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_left.png"));
                walking_2_left    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_left.png"));
    
                standing_right    = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_standing_right.png"));
                walking_1_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_leftFoot_right.png"));
                walking_2_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/gutts_rightFoot_right.png"));

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void locationToClient(){
        if(GameServer.count == 3){
            GP.socketServer.sendData((worldX + " " + worldY + " " + direction + " ").getBytes(), GameServer.clientAddress, GameServer.clientPort); 
        }else if(GameServer.count == 0 && GameClient.count == 1){
            GP.socketClient.sendData((worldX + " " + worldY + " " + direction + " ").getBytes());
        }
    }

    public void update(){
        if(KH.upPressed || KH.downPressed || KH.leftPressed || KH.rightPressed){
            //Direção do movimento
            if(KH.upPressed){
                direction = "up";

            }else if(KH.downPressed){
                direction = "down";

            }else if(KH.leftPressed){
                direction = "left";
            
            }else if(KH.rightPressed){
                direction = "right";
            }

            //Movimentação e colisão
            collisionOn = false;
            GP.cChecker.checkTile(this);

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

            //Animação do sprite
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
        }else{
            spriteNumb = 4;
        }
    }

    public void draw(Graphics2D G2){
        BufferedImage image = null;

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
        G2.drawImage(image, screenX, screenY, GP.tileSize, GP.tileSize, null);
    }
}
