package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class Player extends Entity implements Drawable, Moveable {
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

    public void loadPlayerSprites() {
        try {
            File resDir = getFileFromURL();

            if (resDir.exists() && resDir.isDirectory()) {

            }
        } catch (IOException e) {

        } catch (URISyntaxException e) {
        }
    }

    private File getFileFromURL() {
        URL url = this.getClass().getClassLoader().getResource("/sql");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }

    @Override
    public void update() {
        if (keyHandler.upPressed) {
            System.out.println("cze");
            this.y -= this.speed;
        } else if (keyHandler.downPressed) {
            this.y += this.speed;
        } else if (keyHandler.leftPressed) {
            this.x -= this.speed;
        } else if (keyHandler.rightPressed){
            this.x += this.speed;
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(this.x, this.y, gp.tileSize, gp.tileSize);
    }
}
