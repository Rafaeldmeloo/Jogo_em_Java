package main;

import javax.swing.JOptionPane;

import entity.Player;

import javax.swing.JFrame;

public class Window extends JFrame{

    GamePanel GP = new GamePanel();
    
    public Window(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Beserk");
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(GP);
        this.pack();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to close this window?", "Close Window?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    Player.online = false;
                    System.exit(0);
                }
            }
        });

    }
    
}
