package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_TorchLeftPath extends SuperObject{
    
    public OBJ_TorchLeftPath(){
        name = "TorchLeftPath";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SkullKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}