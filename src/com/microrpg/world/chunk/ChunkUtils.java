package com.microrpg.world.chunk;

import com.microrpg.constants.constants;
import com.microrpg.world.Position;
import com.raylib.java.raymath.Vector2;

public class ChunkUtils {

    public static Position PositionInChunk(Position targetPosition) {

        float multiple = constants.CHUNK_SIZE;
        Position pos = new Position(0,0);
        pos.setX((int)Math.floor(targetPosition.getX() / multiple) * constants.CHUNK_SIZE);
        pos.setY((int)Math.floor(targetPosition.getY() / multiple) * constants.CHUNK_SIZE);
        return pos;

    }

    public static Position PositionInChunk(Vector2 targetPosition) {

        float multiple = constants.CHUNK_SIZE;
        Position pos = new Position(0,0);
        pos.setX((int)Math.floor(targetPosition.getX() / multiple) * constants.CHUNK_SIZE);
        pos.setY((int)Math.floor(targetPosition.getY() / multiple) * constants.CHUNK_SIZE);
        return pos;

    }
}
