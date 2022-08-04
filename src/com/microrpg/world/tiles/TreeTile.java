package com.microrpg.world.tiles;

public class TreeTile extends DetailTile{
    public TreeTile(int tileId) {
        super(2, 1, tileId, "Tree");
        setGroundTile(Tile.GRASS_TILE);
    }
}
