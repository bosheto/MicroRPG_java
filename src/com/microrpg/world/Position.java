package com.microrpg.world;

import com.microrpg.constants.constants;
import com.raylib.java.raymath.Vector2;
import org.lwjgl.system.CallbackI;

import java.util.Objects;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Float.compare(position.x, x) == 0 && Float.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("%.5f, %.5f", this.x, this.y);
    }
}
