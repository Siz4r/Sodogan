package tile;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private final GamePanel gp;
    public final Tile[][] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[gp.maxWorldCol][gp.maxWorldRow];
        initializeMap();
    }

    public void initializeMap() {
        try (InputStream is = TileManager.class.getResourceAsStream("/maps/first.txt");
             var br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] tiles = line.split("\\s+");

                if (tiles.length < gp.maxScreenCol) {
                    throw new RuntimeException(
                            "Error at line " + (row + 1) + " while reading /maps/first.txt, not enough tiles!");
                }

                for (int i = 0; i < gp.maxWorldCol; i++) {
                    var parsedTile = parseTile(tiles[i]);

                    if (parsedTile == null) {
                        throw new RuntimeException("Error while reading /maps/first.txt, unknown tile type");
                    }

                    this.tiles[i][row] = parsedTile;
                }

                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Tile parseTile(String tile) {
        return switch (tile) {
            case "0" -> new Grass();
            case "1" -> new Wall();
            case "2" -> new Water();
            case "3" -> new Earth();
            case "4" -> new Tree();
            case "5" -> new Sand();
            default -> null;
        };
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.maxWorldCol; i++) {
            for (int j = 0; j < gp.maxWorldRow; j++) {
                if (tiles[i][j] != null) {
                    int worldX = i * gp.tileSize, worldY = j * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX,
                            screenY = worldY - gp.player.worldY + gp.player.screenY;

                    if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                            worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                            worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                            worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
                        g2.drawImage(tiles[i][j].image,
                                screenX, screenY,
                                gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}
