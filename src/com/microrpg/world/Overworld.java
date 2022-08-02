package com.microrpg.world;

import com.microrpg.constants.constants;
import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.chunk.Chunk;
import com.microrpg.world.chunk.ChunkUtils;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Overworld {

    private long seed;
    private Position playerPosition;
    private List<Chunk> chunks;
    private Texture2D texture;

    private Map<Position, Chunk> mapChunks;
    private Raylib raylib;

    private Position playerChunkPosition;

    private OpenSimplexNoise noiseMap;

    public Overworld(Position playerPosition, Texture2D texture, long seed, Raylib raylib) {
        this.playerPosition = playerPosition;
        this.texture = texture;
        this.seed = seed;
        chunks = new ArrayList<>();
        mapChunks = new HashMap<Position, Chunk>();
        this.raylib = raylib;
        noiseMap = new OpenSimplexNoise(this.seed);
        InitializeWorld();
    }

    private void InitializeWorld(){
        Position spawn_chunk_position = ChunkUtils.PositionInChunk(playerPosition);
        playerChunkPosition = spawn_chunk_position;
        GenerateChunk(spawn_chunk_position);
        for (Position position : SurroundingPositions(spawn_chunk_position)) {
            GenerateChunk(position);
        }
        for (Position position : mapChunks.keySet()) {
            System.out.println(position.toString());
        }
        System.out.println(ChunkUtils.PositionInChunk(playerPosition).toString());
    }

    private void GenerateChunk(Position position){
        Chunk newChunk = new Chunk(position, texture, raylib, noiseMap);
        chunks.add(newChunk);
        mapChunks.put(position, newChunk);
    }

     private Position[] SurroundingPositions(Position position){
        return new Position[]{
                position.add(new Position(-constants.CHUNK_SIZE, - constants.CHUNK_SIZE)),
                position.add(new Position(-constants.CHUNK_SIZE, 0)),
                position.add(new Position(-constants.CHUNK_SIZE, constants.CHUNK_SIZE)),
                position.add(new Position(0, constants.CHUNK_SIZE)),
                position.add(new Position(constants.CHUNK_SIZE, constants.CHUNK_SIZE)),
                position.add(new Position(constants.CHUNK_SIZE, 0)),
                position.add(new Position(constants.CHUNK_SIZE, -constants.CHUNK_SIZE)),
                position.add(new Position(0, -constants.CHUNK_SIZE))
        };
     }

     public void Update(Position playerPosition){
        this.playerPosition = playerPosition;
        Position currentChunkPosition = ChunkUtils.PositionInChunk(this.playerPosition);
        if(playerChunkPosition.equals(currentChunkPosition)){
            return;
        }
        for (Position position : SurroundingPositions(currentChunkPosition)) {
            if(!mapChunks.containsKey(position)) {
                GenerateChunk(position);
            }
        }
        System.out.println(currentChunkPosition);
        playerChunkPosition = currentChunkPosition;

     }

    public void draw(){
        for (Chunk chunk : chunks) {
            chunk.draw();
        }
    }

}
