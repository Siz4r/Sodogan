package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static main.GraphicsReader.loadPlayerSprites;

public abstract class Entity extends JLabel {
    public int worldX, worldY;
    public int speed = 4;
    public Map<SpritesKeys, BufferedImage> sprites;
    public String direction;
    public int spriteCounter = 0, spriteNum = 0;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity(int worldX, int worldY, int speed) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        this.direction = "down";
        this.sprites = new HashMap<>();
        loadPlayerSprites(this.getClass().getSimpleName().toLowerCase(), sprites);
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
}
