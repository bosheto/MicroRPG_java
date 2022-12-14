package com.microrpg.world.chunk;

import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.Position;
import com.microrpg.world.tiles.*;
import com.microrpg.constants.EngineConstants;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

import java.util.Random;


public class Chunk {

    private Position pos;
    private Texture2D texture;
    private int[][] tiles;
    private Raylib raylib;

    private Random rand;

    private OpenSimplexNoise noiseMap;

    public Chunk(Position pos, Texture2D texture, Raylib raylib, OpenSimplexNoise noiseMap) {
        this.pos = pos;
        this.texture = texture;
        this.raylib = raylib;
        rand = new Random();
        tiles = new int[EngineConstants.CHUNK_SIZE][EngineConstants.CHUNK_SIZE];
        this.noiseMap = noiseMap;
        generateChunk();
    }

    private void generateChunk() {
        for (int y = 0; y < EngineConstants.CHUNK_SIZE; y++) {
            for (int x = 0; x < EngineConstants.CHUNK_SIZE; x++) {
                tiles[y][x] = GenerateTiles(x,y);
                GenerateDetails(x, y);
            }
        }
    }

    private boolean inRange(int index){
        return index >=0 && index < EngineConstants.CHUNK_SIZE;
    }

    private void GenerateDetails(int x, int y)
    {
        int n = rand.nextInt(60);
        int id = tiles[y][x];
        Tile t = Tile.TILES[id];
        if(t instanceof GrassTile){
            if(n <  10)
                tiles[y][x] = Tile.TREE_TILE.getTileId();
            if(n > 40)
                tiles[y][x] = Tile.SMALL_GRASS_TILE.getTileId();
        }
    }

    private int GenerateTiles(int x, int y){
        double scale = 0.02;
        double scale2 = 0.15;
        Position pos = this.pos.add(new Position(x,y));
        double n = noiseMap.eval(pos.getX() * scale, pos.getY() * scale);
        n+= noiseMap.eval(pos.getX()  * scale2, pos.getY() * scale2) * 0.5;

        if(n < -0.5)
            return Tile.WATER_TILE.getTileId();
        if(n > -0.5 && n < 0.5)
            return Tile.GRASS_TILE.getTileId();
        else
            return Tile.STONE_TILE.getTileId();
    }

    public Tile GetTile(int x, int y){
        if(inRange(x) && inRange(y)){
            return Tile.TILES[tiles[y][x]];
        }
        return Tile.AIR_TILE;
    }

    public void SetTile(int x, int y, int tile){
        if(inRange(x) && inRange(y))
            tiles[y][x] = tile;
    }

    public void DrawTiles() {
        for (int y = 0; y < EngineConstants.CHUNK_SIZE; y++) {
            for (int x = 0; x < EngineConstants.CHUNK_SIZE; x++) {
                int id = tiles[y][x];
                Tile t = Tile.TILES[id];
                Position pos = this.pos.add(new Position(x, y));
                t.Draw(raylib, pos, texture);
            }
        }
    }

    public Position getPos() {
        return pos;
    }
}
