package main.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_GoldenDoor extends SuperObject{
    
    public OBJ_GoldenDoor(){
        name = "GoldenDoor";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/GoldenDoor.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }

    
}