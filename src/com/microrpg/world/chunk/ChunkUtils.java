package com.microrpg.world.chunk;

import com.microrpg.constants.constants;
import com.microrpg.world.Position;

public class ChunkUtils {

    public static Position PositionInChunk(Position targetPosition) {

        float multiple = constants.CHUNK_SIZE;
        Position pos = new Position(0,0);
        pos.setX(Math.round(targetPosition.getX() / multiple) * constants.CHUNK_SIZE);
        pos.setY(Math.round(targetPosition.getY() / multiple) * constants.CHUNK_SIZE);
        return pos;


    }


}
