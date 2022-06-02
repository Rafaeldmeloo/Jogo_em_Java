package main;

import entity.PlayerMP;

public class AssetSetter {
    GamePanel GP;

    public AssetSetter(GamePanel GP){
        this.GP = GP;
    }

    public void setPlayer2(){
        GP.player2[0] = new PlayerMP();
        GP.player2[0].worldX = GP.tileSize*23;
        GP.player2[0].worldY = GP.tileSize*21;
    }
}
