package com.microrpg.world.tiles;

public class util {

    private static final String INVALID_ID_MSG = "Invalid tile Id: %d";

    public static Tile GetTileFromId(int id){
        switch (id){
            case 0:
                return new GrassTile();
            case 1:
                return new SandTile();
            case 2:
                return new StoneTile();
        }

        throw new IllegalArgumentException(String.format(INVALID_ID_MSG, id));
    }

    public static int GetIndexFromTile(Tile tile){
        return tile.getTileId();
    }

}
