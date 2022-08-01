package com.microrpg.world.tiles;

public class util {

    private static final String INVALID_ID_MSG = "Invalid tile Id: %d";

    public static Tile getTileFromId(int id){
        switch (id){
            case 0:
                return new GrassTile();
        }
        throw new IllegalArgumentException(String.format(INVALID_ID_MSG, id));
    }

}
