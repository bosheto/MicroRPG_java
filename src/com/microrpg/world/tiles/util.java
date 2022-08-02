package com.microrpg.world.tiles;

public class util {

    private static final String INVALID_ID_MSG = "Invalid tile Id: %d";

    public static Tile GetTileFromId(int id){
        switch (id){
            case 1:
                return new GrassTile();
            case 2:
                return new SandTile();
            case 3:
                return new StoneTile();
            case 4:
                return new WaterTile();
            case 5:
                return new TreeTile();
        }

        throw new IllegalArgumentException(String.format(INVALID_ID_MSG, id));
    }

    public static int GetIndexFromTile(Tile tile){
        return tile.getTileId();
    }

}
