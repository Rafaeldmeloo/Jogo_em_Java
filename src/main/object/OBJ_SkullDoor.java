package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SkullDoor extends SuperObject{
    
    public OBJ_SkullDoor(){
        name = "SkullDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SkullKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
