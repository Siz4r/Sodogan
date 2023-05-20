package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity implements Drawable, Moveable {
    Action upAction;
    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp.tileSize * 23, gp.tileSize * 23, 7);
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        this.screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "upAction");
        this.getActionMap().put("upAction",upAction);
        this.solidArea = new Rectangle(8, 20, 24, 24);
    }



    @Override
    public void update() {
        if (keyHandler.upPressed) {
            this.worldY -= this.speed;
            this.direction = "up";
        } else if (keyHandler.downPressed) {
            this.worldY += this.speed;
            this.direction = "down";
        } else if (keyHandler.leftPressed) {
            this.worldX -= this.speed;
            this.direction = "left";
        } else if (keyHandler.rightPressed){
            this.worldX += this.speed;
            this.direction = "right";
        }

        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        this.spriteCounter++;
        if (spriteCounter > 15) {
            spriteNum = spriteNum == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage sprite = switch (direction) {
            case "up" -> {
                if (spriteNum == 1) yield sprites.get(SpritesKeys.UP_2);
                yield sprites.get(SpritesKeys.UP_1);
            }
            case "down" -> {
                if (spriteNum == 1) yield sprites.get(SpritesKeys.DOWN_2);
                yield sprites.get(SpritesKeys.DOWN_1);
            }
            case "right" -> {
                if (spriteNum == 1) yield sprites.get(SpritesKeys.RIGHT_2);
                yield sprites.get(SpritesKeys.RIGHT_1);
            }
            case "left" -> {
                if (spriteNum == 1) yield sprites.get(SpritesKeys.LEFT_2);
                yield sprites.get(SpritesKeys.LEFT_1);
            }
            default -> null;
        };

        g2.drawImage(sprite, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
