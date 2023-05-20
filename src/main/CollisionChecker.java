package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x, entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y, entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize, entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize, entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                gamePanel.tileManager.tiles[entityLeftCol][entityTopRow].onCollision(entity);
                gamePanel.tileManager.tiles[entityRightCol][entityTopRow].onCollision(entity);
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                gamePanel.tileManager.tiles[entityLeftCol][entityBottomRow].onCollision(entity);
                gamePanel.tileManager.tiles[entityRightCol][entityBottomRow].onCollision(entity);
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                gamePanel.tileManager.tiles[entityLeftCol][entityTopRow].onCollision(entity);
                gamePanel.tileManager.tiles[entityLeftCol][entityBottomRow].onCollision(entity);
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                gamePanel.tileManager.tiles[entityRightCol][entityTopRow].onCollision(entity);
                gamePanel.tileManager.tiles[entityRightCol][entityBottomRow].onCollision(entity);
            }
        }
    }
}
