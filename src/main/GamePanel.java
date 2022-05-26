package main;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import net.GameClient;
import net.GameServer;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenX = 16;
    public final int maxScreenY = 12;
    public final int screenWidth = tileSize*maxScreenX;
    public final int screenHeight = tileSize*maxScreenY;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);

    private GameClient socketClient;
    private GameServer socketServer;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.StartGameThread();
        this.addKeyListener(keyH);
        this.setFocusable(true);
        socketClient.sendData("ping".getBytes());;
        
    }

    public void StartGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

        if(JOptionPane.showConfirmDialog(this, "do you want to run the server") == 0){
            socketServer = new GameServer(this);
            socketServer.start();
        }
        
        socketClient = new GameClient(this, "localhost");
        socketClient.start();

    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }            
        }

    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();

    }


}
