package tile;

import entity.Entity;

public class Hut extends Tile {
    public Hut() {
        super(true);
    }

    @Override
    public void onCollision(Entity entity) {

        entity.setCollisionOn(true);

    }
}
