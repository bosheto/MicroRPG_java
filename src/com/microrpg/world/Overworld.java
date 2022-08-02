package com.microrpg.world;

import com.microrpg.constants.constants;
import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.chunk.Chunk;
import com.microrpg.world.chunk.ChunkUtils;
import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Overworld {

    private long seed;
    private Vector2 playerPosition;
    private List<Chunk> chunks;
    private Texture2D texture;

    private Map<Position, Chunk> mapChunks;
    private Raylib raylib;

    private Position playerChunkPosition;

    private OpenSimplexNoise noiseMap;

    public Overworld(Vector2 playerPosition, Texture2D texture, long seed, Raylib raylib) {
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
        Position spawn_chunk_position = ChunkUtils.PositionInChunk(Position.toWorldPosition(playerPosition));
        playerChunkPosition = spawn_chunk_position;
        GenerateChunk(spawn_chunk_position);
        for (Position position : SurroundingPositions(spawn_chunk_position)) {
            GenerateChunk(position);
        }
        for (Position position : mapChunks.keySet()) {
            System.out.println(position.toString());
        }
        System.out.println(playerPosition.toString());
    }

    private void GenerateChunk(Position position){
        Chunk newChunk = new Chunk(position, texture, raylib, noiseMap);
        mapChunks.put(position, newChunk);
    }

    private List<Position> SurroundingPositions(Position position){
        int dist = constants.renderDistance;
        int chunkSize = constants.CHUNK_SIZE;
        List<Position> positions = new ArrayList<>();

        for(int y = -dist; y < dist; y++)
        {
            for(int x = -dist; x < dist; x++){
                if(x == 0 && y == 0 )
                    continue;
                positions.add(position.add(new Position(x * chunkSize, y * chunkSize)));
            }
        }
        return positions;
    }

     public void Update(Vector2 playerPosition){
        this.playerPosition = playerPosition;
        Position currentChunkPosition = ChunkUtils.PositionInChunk(Position.toWorldPosition(this.playerPosition));

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
        mapChunks.get(playerChunkPosition).DrawTiles();
        for (Position position : SurroundingPositions(playerChunkPosition)) {
            mapChunks.get(position).DrawTiles();
        }
//        for (Chunk chunk : chunks) {
//            chunk.draw();
//        }

    }

}
