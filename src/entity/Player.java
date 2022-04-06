package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.awt.Color;
import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel GP;
    KeyHandler KH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel GP, KeyHandler KH){
        this.GP = GP;
        this.KH = KH;

        screenX = GP.screenWidth/2 - (GP.tileSize/2);
        screenY = GP.screenHeight/2 - (GP.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX      = GP.tileSize*23;
        worldY      = GP.tileSize*21;
        speed       = 3;
        direction   = "down";
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

    public void update(){
        if(KH.upPressed || KH.downPressed || KH.leftPressed || KH.rightPressed){
            if(KH.upPressed){
                worldY -= speed;
                direction = "up";
            }
            else if(KH.downPressed){
                worldY += speed;
                direction = "down";
            }
            else if(KH.leftPressed){
                worldX -= speed;
                direction = "left";
            }
            else if(KH.rightPressed){
                worldX += speed;
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
