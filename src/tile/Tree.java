package tile;

import entity.Entity;

public class Tree extends Tile {
    public Tree() {
        super(true);
    }

    @Override
    public void onCollision(Entity entity) {
        entity.setCollisionOn(true);
    }
}
