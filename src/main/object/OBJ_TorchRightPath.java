package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_TorchRightPath extends SuperObject{
    
    public OBJ_TorchRightPath(){
        name = "TorchRightPath";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SkullKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}