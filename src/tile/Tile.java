package tile;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.GraphicsReader.getFileFromURL;

public abstract class Tile implements CollisionAble {
    public BufferedImage image;
    public boolean collision = false;

    public Tile() {
        readImage(this.getClass().getSimpleName().toLowerCase());
    }

    public Tile(Boolean collision) {
        readImage(this.getClass().getSimpleName().toLowerCase());
        this.collision = true;
    }

    public void readImage(String path) {
        var file = getFileFromURL("tiles" + File.separator + path + ".png");

        if (file == null || file.isDirectory()) {
            throw new RuntimeException("Couldn't find a class tile!");
        }

        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCollision(Entity entity) {}
}
