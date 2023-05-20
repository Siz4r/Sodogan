package tile;

import entity.Entity;

public class Water extends Tile{
    public Water() {
        super(true);
    }

    @Override
    public void onCollision(Entity entity) {
        entity.setCollisionOn(true);
    }
}
