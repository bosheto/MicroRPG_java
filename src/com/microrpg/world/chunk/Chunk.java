package com.microrpg.world.chunk;

import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.Position;
import com.microrpg.world.tiles.*;
import com.microrpg.constants.constants;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;


public class Chunk {
    private Position pos;
    private Texture2D texture;
    private int[][] tiles;

    private Raylib raylib;

    private OpenSimplexNoise noiseMap;

    public Chunk(Position pos, Texture2D texture, Raylib raylib, OpenSimplexNoise noiseMap) {
        this.pos = pos;
        this.texture = texture;
        this.raylib = raylib;
        tiles = new int[constants.CHUNK_SIZE][constants.CHUNK_SIZE];
        this.noiseMap = noiseMap;
        generateChunk();
    }

    private void generateChunk() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                tiles[y][x] = GetTileFromCoordinates(x,y);
            }
        }
    }

    private int GetTileFromCoordinates(int x, int y){
        Position pos = this.pos.add(new Position(x,y));
        double n = noiseMap.eval(pos.getX(), pos.getY());

        if(n < 0.01)
            return util.GetIndexFromTile(new SandTile());
        if(n < 0.2)
            return util.GetIndexFromTile(new GrassTile());
        else
            return  util.GetIndexFromTile(new StoneTile());

    }

    public void draw() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                Tile t = util.GetTileFromId(tiles[y][x]);
                Position pos = this.pos.add(new Position(x, y));
                t.draw(raylib, pos, texture);
            }
        }
    }

}
