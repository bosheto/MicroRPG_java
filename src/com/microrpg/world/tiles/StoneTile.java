package com.microrpg.world.tiles;


import com.microrpg.world.contracts.Breakable;

public class StoneTile extends Tile implements Breakable {
    public StoneTile(int tileId) {
        super(3, 0, tileId, "Stone", true);
    }

    @Override
    public Tile BreakBlock() {
        return Tile.COBBLESTONE_TILE;
    }
}
