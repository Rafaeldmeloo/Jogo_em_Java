package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Npc{

    GamePanel GP;

    public int worldX;
    public int worldY;

    public BufferedImage standing_front, starting_jump, jumping;

    public int spriteCounter    = 0;
    public int spriteNumb       = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Npc(GamePanel gp){
        this.GP = gp;

        worldX = GP.tileSize*10;
        worldY = GP.tileSize*8;

        solidArea         = new Rectangle();
        solidArea.x       = 8;
        solidArea.y       = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width   = 32;
        solidArea.height  = 32;

        getPlayerImage();
    }

    public void getPlayerImage(){

            try{
                standing_front     = ImageIO.read(getClass().getResourceAsStream("/res/npc/casca_standing_front.png"));
                starting_jump      = ImageIO.read(getClass().getResourceAsStream("/res/npc/casca_starting_jump.png"));
                jumping            = ImageIO.read(getClass().getResourceAsStream("/res/npc/casca_jumping.png"));

            }catch(IOException e){
                e.printStackTrace();
            }
    }

    public void draw(Graphics2D G2){
        BufferedImage image = null;

        int screenX = worldX - GP.player.worldX + GP.player.screenX;
        int screenY = worldY - GP.player.worldY + GP.player.screenY;

        spriteCounter++;
            if(spriteCounter > 24){
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

        if(spriteNumb == 1)
            image = standing_front;
        else if(spriteNumb == 2)
            image = starting_jump;
        else if (spriteNumb == 3)
            image = jumping;
        else if(spriteNumb == 4)
            image = starting_jump;
           
        G2.drawImage(image, screenX, screenY, GP.tileSize, GP.tileSize, null);
    }
    
}
