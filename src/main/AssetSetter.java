package main;

import main.object.OBJ_GoldenDoor;
import main.object.OBJ_GoldenKey;
import main.object.OBJ_SilverDoor;
import main.object.OBJ_SilverKey;
import main.object.OBJ_SkullDoor;
import main.object.OBJ_SkullKey;
import main.object.OBJ_TorchLeftPath;
import main.object.OBJ_TorchRightPath;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_GoldenKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_SilverKey();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_SkullKey();
        gp.obj[2].worldX = 30 * gp.tileSize;
        gp.obj[2].worldY = 21 * gp.tileSize;

        gp.obj[3] = new OBJ_GoldenDoor();
        gp.obj[3].worldX = 25 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_SilverDoor();
        gp.obj[4].worldX = 25 * gp.tileSize;
        gp.obj[4].worldY = 40 * gp.tileSize;

        gp.obj[5] = new OBJ_SkullDoor();
        gp.obj[5].worldX = 32 * gp.tileSize;
        gp.obj[5].worldY = 21 * gp.tileSize;

        gp.obj[6] = new OBJ_TorchLeftPath();
        gp.obj[6].worldX = 33 * gp.tileSize;
        gp.obj[6].worldY = 21 * gp.tileSize;

        gp.obj[7] = new OBJ_TorchRightPath();
        gp.obj[7].worldX = 34 * gp.tileSize;
        gp.obj[7].worldY = 21 * gp.tileSize;
        
    }
}
