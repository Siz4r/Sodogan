package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

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

    @Override
    public void update() {
        if (keyHandler.upPressed) {
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
