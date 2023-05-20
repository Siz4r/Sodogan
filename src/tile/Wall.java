package tile;

import entity.Entity;

public class Wall extends Tile{
    public Wall() {
        super(true);
    }

    @Override
    public void onCollision(Entity entity) {
        entity.setCollisionOn(true);
    }
}
