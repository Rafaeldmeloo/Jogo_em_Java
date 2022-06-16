package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SilverKey extends SuperObject{
    
    public OBJ_SilverKey(){
        name = "SilverKey";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SilverKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}