package entity;

import java.awt.image.BufferedImage;
import java.util.Map;

abstract class Entity {
    int x, y;
    int speed;

    public Map<String, BufferedImage> sprites;
    public String direction;
}
