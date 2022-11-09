package com.microrpg.world;

import com.microrpg.constants.EngineConstants;
import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.chunk.Chunk;
import com.microrpg.world.tiles.Tile;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public class World {

    int verticalChunks = 6;
    int horizontalChunks = 6;

    int xSize = horizontalChunks * EngineConstants.CHUNK_SIZE;
    int ySize = verticalChunks * EngineConstants.CHUNK_SIZE;


    Chunk[][] chunks = new Chunk[verticalChunks][horizontalChunks];

    private Texture2D texture;

    private Raylib raylib;

    private OpenSimplexNoise noiseMap;

    public World(Texture2D texture, Raylib raylib) {
        this.texture = texture;
        this.raylib = raylib;
        this.noiseMap = new OpenSimplexNoise(1234);
        initializeWorld();
    }

    private void initializeWorld(){
        for(int y = 0; y < verticalChunks; y++) {
            for (int x = 0; x < horizontalChunks; x++) {
                Position chunkPosition = new Position(x * EngineConstants.CHUNK_SIZE, y * EngineConstants.CHUNK_SIZE);
                chunks[y][x] = new Chunk(chunkPosition, texture, raylib, noiseMap);
            }
        }
    }

    public Tile GetTile(Position pos){
        Chunk containingChunk = getChunkFromPos(pos);
        int x = pos.getX() - containingChunk.getPos().getX();
        int y = pos.getY() - containingChunk.getPos().getY();
        return containingChunk.GetTile(x, y);
    }

    private Chunk getChunkFromPos(Position pos){
        float multiple = EngineConstants.CHUNK_SIZE;
        int chunkX = (int)Math.floor(pos.getX() / multiple);
        int chunkY = (int)Math.floor(pos.getY() / multiple);
        return chunks[chunkY][chunkX];

    }

    public boolean inWorld(Position pos){

        if(pos.getX() < 0 || pos.getX() > horizontalChunks * EngineConstants.CHUNK_SIZE)
            return false;
        else return pos.getY() >= 0 && pos.getY() <= horizontalChunks * EngineConstants.CHUNK_SIZE;
    }

    // TODO only render chunks the player sees
    public void draw(){
        for(int y = 0; y < verticalChunks; y++) {
            for (int x = 0; x < horizontalChunks; x++) {
                chunks[y][x].DrawTiles();
            }
        }
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}
