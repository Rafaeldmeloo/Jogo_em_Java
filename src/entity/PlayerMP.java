package entity;

import java.net.InetAddress;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerMP extends Entity2{

    public InetAddress ipAddress;
    public int port;

    public int worldX, worldY;
    public static int previousX = 1104; 
    public static int previousY = 1008; 
    public int speed;
    public String direction;

    public PlayerMP(){
        getPlayerImage();
    }

        public void draw(Graphics2D g2, GamePanel gp, String XYDirection){
            
            String[] position = XYDirection.split(" ");
            worldX = Integer.parseInt(position[0]);
            worldY = Integer.parseInt(position[1]);
            direction = position[2];


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

        public void getPlayerImage(){

            try{
    
                standing_front       = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-parado-frente.png"));
                walking_1_front    = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peEsquerdo-frente.png"));
                walking_2_front    = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peDireito-frente.png"));
    
                standing_back       = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-parado-costas.png"));
                walking_1_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peEsquerdo-costas.png"));
                walking_2_back    = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peDireito-costas.png"));
    
                standing_left     = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-parado-esquerda.png"));
                walking_1_left  = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peEsquerdo-esquerda.png"));
                walking_2_left  = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peDireito-esquerda.png"));
    
                standing_right      = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-parado-direita.png"));
                walking_1_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peEsquerdo-direita.png"));
                walking_2_right   = ImageIO.read(getClass().getResourceAsStream("/res/player/pixil-frame-peDireito-direita.png"));
    
    
            }catch(IOException e){
                e.printStackTrace();
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
