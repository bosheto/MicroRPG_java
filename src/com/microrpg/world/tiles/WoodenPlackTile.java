package com.microrpg.world.tiles;

import com.microrpg.world.contracts.Breakable;

public class WoodenPlackTile extends Tile implements Breakable {
    public WoodenPlackTile(int tileId) {
        super(6, 0, tileId, "wooden planks", true);
    }

    @Override
    public Tile BreakBlock() {
        return Tile.DIRT_TILE;
    }
}
