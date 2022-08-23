package com.microrpg.world.tiles;

public class SmallGrassTile extends DetailTile{
    public SmallGrassTile(int tileId) {
        super(3, 1, tileId, "SmallGrass", false);
        super.setGroundTile(Tile.GRASS_TILE);
    }
}
