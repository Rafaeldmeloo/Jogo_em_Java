package main;

import javax.swing.JFrame;

public class Window extends JFrame{

    GamePanel GP = new GamePanel();

    public Window(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Beserk");
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(GP);
        this.pack();

    }
    
}
