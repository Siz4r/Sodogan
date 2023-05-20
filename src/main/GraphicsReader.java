package main;

import entity.Player;
import entity.SpritesKeys;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class GraphicsReader {
    public static void loadPlayerSprites(String pathToSprites, Map<SpritesKeys, BufferedImage> sprites) {
        File resDir = getFileFromURL(pathToSprites);

        if (resDir.exists() && resDir.isDirectory()) {
            for (final File fileEntry : Objects.requireNonNull(resDir.listFiles())) {
                try {
                    String fileName = fileEntry.getName().split("\\.")[0];
                    char number = fileName.charAt(fileName.length() - 1);
                    putSprite(fileEntry, fileName, number, sprites);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void putSprite(File fileEntry, String fileName,
            char number, Map<SpritesKeys, BufferedImage> sprites) throws IOException {
        if (fileName.contains("up")) {
            sprites.put(number == '1' ? SpritesKeys.UP_1 : SpritesKeys.UP_2, ImageIO.read(fileEntry));
        } else if (fileName.contains("down")) {
            sprites.put(number == '1' ? SpritesKeys.DOWN_1 : SpritesKeys.DOWN_2, ImageIO.read(fileEntry));
        } else if (fileName.contains("left")) {
            sprites.put(number == '1' ? SpritesKeys.LEFT_1 : SpritesKeys.LEFT_2, ImageIO.read(fileEntry));
        } else {
            sprites.put(number == '1' ? SpritesKeys.RIGHT_1 : SpritesKeys.RIGHT_2, ImageIO.read(fileEntry));
        }
    }

    public static File getFileFromURL(String pathToSprites) {
        URL url = GraphicsReader.class.getResource(File.separator + pathToSprites);

        if (url == null) throw new RuntimeException("Could not load resources: " + File.separator + pathToSprites);

        File file;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        return file;
    }
}
