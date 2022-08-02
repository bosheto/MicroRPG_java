package com.microrpg.world.chunk;

import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.Position;
import com.microrpg.world.tiles.*;
import com.microrpg.constants.constants;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

import java.util.Random;


public class Chunk {
    private Position pos;
    private Texture2D texture;
    private int[][] tiles;
    private int[][] details;
    private Raylib raylib;

    private Random rand;

    private OpenSimplexNoise noiseMap;

    public Chunk(Position pos, Texture2D texture, Raylib raylib, OpenSimplexNoise noiseMap) {
        this.pos = pos;
        this.texture = texture;
        this.raylib = raylib;
        rand = new Random();
        tiles = new int[constants.CHUNK_SIZE][constants.CHUNK_SIZE];
        details = new int[tiles.length][tiles.length];
        this.noiseMap = noiseMap;
        generateChunk();
    }

    private void generateChunk() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                tiles[y][x] = GenerateTiles(x,y);
                GenerateDetails(x, y);
            }
        }
    }

    private void GenerateDetails(int x, int y)
    {
        int n = rand.nextInt(10);
        Tile t = util.GetTileFromId(tiles[y][x]);
        if(t instanceof GroundTile){
            ((GroundTile) t).setTopTile(new TreeTile());

        }

    }


    private int GenerateTiles(int x, int y){
        double scale = 0.02;
        double scale2 = 0.15;
        Position pos = this.pos.add(new Position(x,y));
        double n = noiseMap.eval(pos.getX() * scale, pos.getY() * scale);
        n+= noiseMap.eval(pos.getX()  * scale2, pos.getY() * scale2) * 0.5;

        if(n < -0.5)
            return util.GetIndexFromTile(new WaterTile());
        if(n > -0.5 && n < 0.5)
            return util.GetIndexFromTile(new GrassTile());
        else
            return  util.GetIndexFromTile(new StoneTile());
    }

    public void DrawTiles() {
        for (int y = 0; y < constants.CHUNK_SIZE; y++) {
            for (int x = 0; x < constants.CHUNK_SIZE; x++) {
                Tile t = util.GetTileFromId(tiles[y][x]);
                Position pos = this.pos.add(new Position(x, y));
                t.Draw(raylib, pos, texture);
            }
        }
    }

}
