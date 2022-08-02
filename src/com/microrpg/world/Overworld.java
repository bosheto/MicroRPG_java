package com.microrpg.world;

import com.microrpg.constants.constants;
import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.chunk.Chunk;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

import java.util.ArrayList;
import java.util.List;

public class Overworld {

    private long seed;
    private Position playerPosition;
    private List<Chunk> chunks;
    private Texture2D texture;

    private Raylib raylib;

    private OpenSimplexNoise noiseMap;

    public Overworld(Position playerPosition, Texture2D texture, long seed, Raylib raylib) {
        this.playerPosition = playerPosition;
        this.texture = texture;
        this.seed = seed;
        chunks = new ArrayList<>();
        this.raylib = raylib;
        noiseMap = new OpenSimplexNoise(this.seed);
        InitializeWorld();
    }

    private void InitializeWorld(){
        Position spawn_chunk_position = playerPosition.add(new Position(-constants.CHUNK_SIZE / 2, - constants.CHUNK_SIZE/2));
        chunks.add(new Chunk(spawn_chunk_position, texture, raylib, noiseMap));
        for (Position position : SurroundingPositions(spawn_chunk_position)) {
            chunks.add(new Chunk(position, texture, raylib, noiseMap));
        }
        
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

    public void draw(){
        for (Chunk chunk : chunks) {
            chunk.draw();
        }
    }

}
