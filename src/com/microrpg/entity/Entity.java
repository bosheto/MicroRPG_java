package com.microrpg.entity;

import com.microrpg.constants.constants;
import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public abstract class Entity {

    private Position pos;
    private int hp;
    private float speed;
    private int tile_x;
    private int tile_y;

    public Entity(Position pos, int hp, float speed, int tile_x, int tile_y){
        this.hp = hp;
        this.speed = speed;
        this.pos = pos;
        this.tile_x = tile_x;
        this.tile_y = tile_y;
    }

    public void draw(Raylib raylib, Texture2D texture){
        Rectangle rect = new Rectangle((float)tile_x * constants.SPRITE_SIZE, (float)tile_y * constants.SPRITE_SIZE
                ,(float)constants.SPRITE_SIZE, (float)constants.SPRITE_SIZE);

        raylib.textures.DrawTextureRec(texture, rect, Position.toScreenPosition(pos), Color.WHITE);
    }

    public float getSpeed() {
        return speed;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }




}
