package main;

import main.object.OBJ_GoldenKey;
import main.object.OBJ_SilverKey;
import main.object.OBJ_SkullKey;

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
        
    }
}
