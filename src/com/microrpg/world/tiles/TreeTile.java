package com.microrpg.world.tiles;

import com.microrpg.world.contracts.Breakable;

public class TreeTile extends DetailTile implements Breakable {
    public TreeTile(int tileId) {
        super(2, 1, tileId, "Tree", true);
        setGroundTile(Tile.GRASS_TILE);
    }

    @Override
    public Tile BreakBlock() {
        return this.groundTile;
    }
}
