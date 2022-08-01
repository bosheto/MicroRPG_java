package com.microrpg.world.chunk;

import com.microrpg.world.Position;
import com.microrpg.world.tiles.util;
import com.microrpg.constants.constants;
import com.microrpg.world.tiles.Tile;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;


public class Chunk {
    private Position pos;
    private Texture2D texture;
    private int[][] tiles;

    private Raylib raylib;

    public Chunk(Position pos, Texture2D texture, Raylib raylib) {
        this.pos = pos;
        this.texture = texture;
        this.raylib = raylib;
        tiles = new int[constants.CHUNK_SIZE][constants.CHUNK_SIZE];
        generateChunk();
    }

    private void generateChunk() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                tiles[y][x] = 0;
            }
        }
    }

    public void draw() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                Tile t = util.getTileFromId(tiles[y][x]);
                Position pos = this.pos.add(new Position(x, y));
                t.draw(raylib, pos, texture);
            }
        }
    }

}
