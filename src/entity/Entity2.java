package entity;

import java.awt.image.BufferedImage;

public class Entity2 {
    public int worldX, worldY;
    public int speed;
    public String direction;
    
    public BufferedImage standing_front, standing_back, standing_left, standing_right;
    public BufferedImage walking_1_front, walking_1_back, walking_1_left, walking_1_right;
    public BufferedImage walking_2_front, walking_2_back, walking_2_left, walking_2_right;

    public int spriteCounter    = 0;
    public int spriteNumb       = 1;
    
}
