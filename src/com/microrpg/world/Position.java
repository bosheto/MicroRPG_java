package com.microrpg.world;

import com.microrpg.constants.constants;
import com.raylib.java.raymath.Vector2;
import org.lwjgl.system.CallbackI;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Position add(Position pos) {
        return new Position(x + pos.x, y + pos.y);
    }

    public static Position toWorldPosition(Vector2 pos){
        return new Position(pos.x / constants.SPRITE_SIZE, pos.y / constants.SPRITE_SIZE);
    }

    public static Vector2 toScreenPosition(Position pos){
        return new Vector2(pos.x * constants.SPRITE_SIZE, pos.y * constants.SPRITE_SIZE);
    }
}
