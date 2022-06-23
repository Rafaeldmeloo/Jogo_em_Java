package main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;
import entity.PlayerMP;
import main.object.SuperObject;
import net.GameClient;
import net.GameServer;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    private JFrame f;
    
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
    public AssetSetter aSetter = new AssetSetter(this);

    public Player player = new Player(tileSize*23, tileSize*21, this, keyH/*, JOptionPane.showInputDialog(this, "Please enter your username")*/);
    public PlayerMP player2 = new PlayerMP(this);
    public SuperObject obj[] = new SuperObject[10];

    public CollisionChecker cChecker = new CollisionChecker(this);

    public GameClient socketClient;
    public GameServer socketServer;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setupGame();
        this.StartGameThread();
        this.addKeyListener(keyH);
        this.setFocusable(true);
        socketClient.sendData("ping".getBytes());
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){
        aSetter.setObject();
    }

    public void StartGameThread(){
        if(JOptionPane.showConfirmDialog(this, "do you want to run the server") == 0){
            socketServer = new GameServer();
            socketServer.start();
            socketClient = new GameClient("localhost");
            socketClient.start();
            GameClient.XYDirection = "true 1104 1008 down ";

        } else {
            socketClient = new GameClient("localhost");
            socketClient.start();
        }
        
        player.getPlayerImage();
        player2.getPlayerImage();
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
        player.locationToClient();
        if(GameClient.XYDirection.contains("false")){
            f = new JFrame();  
            JOptionPane.showMessageDialog(f,"Please, a functional server must being working.");
            System.exit(0);  
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        if(PlayerMP.online.contains("true")){
            if(GameServer.count == 3){
                player2.draw(g2, this, GameServer.XYDirection);
            } else if (GameServer.count == 0 && GameClient.count == 1){
                player2.draw(g2, this, GameClient.XYDirection);
            }
        }

        player.draw(g2);

        g2.dispose();
    }
}
