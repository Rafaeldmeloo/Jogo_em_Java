package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SkullKey extends SuperObject{
    
    public OBJ_SkullKey(){
        name = "SkullKey";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SkullKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}