package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    final int FPS = 120;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyHandler = new KeyHandler();

    private Thread gameThread;

    Player player;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.player = new Player(this, keyHandler);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;

        player.draw(g2);
        g2.dispose();
    }

    private void update() {
        player.update();
    }
}
