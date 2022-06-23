package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SilverDoor extends SuperObject{
    
    public OBJ_SilverDoor(){
        name = "SilverDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SilverDoor.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
