package com.microrpg.world;

import com.microrpg.constants.EngineConstants;
import com.raylib.java.raymath.Vector2;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position add(Position pos) {
        return new Position(x + pos.x, y + pos.y);
    }

    public static Position toWorldPosition(Vector2 pos){
        return new Position(Math.round(pos.x / EngineConstants.SPRITE_SIZE), Math.round(pos.y / EngineConstants.SPRITE_SIZE));
    }

    public static Vector2 toScreenPosition(Position pos){
        return new Vector2(pos.x * EngineConstants.SPRITE_SIZE, pos.y * EngineConstants.SPRITE_SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return position.x == x && position.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("%d, %d", this.x, this.y);
    }
}
