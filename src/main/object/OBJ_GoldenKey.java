package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_GoldenKey extends SuperObject{
    
    public OBJ_GoldenKey(){
        name = "GoldenKey";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/GoldenKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
