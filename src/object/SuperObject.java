package object;

import entity.Entity;
import tile.CollisionAble;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.GraphicsReader.getFileFromURL;

public class SuperObject implements CollisionAble {
    public BufferedImage image;
    public String name;
    public int worldX, worldY;

    public SuperObject() {
        readImage(this.getClass().getSimpleName().toLowerCase());
    }

    public void readImage(String path) {
        var file = getFileFromURL("objects" + File.separator + path + ".png");

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
