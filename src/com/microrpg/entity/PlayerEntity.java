package com.microrpg.entity;

import com.microrpg.constants.constants;
import com.microrpg.utils.AABB;
import com.microrpg.world.Overworld;

import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

import static com.raylib.java.core.input.Keyboard.*;
import static java.lang.String.format;


public class PlayerEntity extends Entity{

    public PlayerEntity(Vector2 pos, Overworld world, Raylib raylib) {
        super(pos, 10, 4f, 0, 1, world, raylib);
        setCollider(new AABB(0,0,1,1));

    }

    private boolean inCollision(Vector2 pos){
        Vector2 tempPos = new Vector2(pos.x, pos.y);
        float offset = constants.SPRITE_SIZE / 2.0f - 6;
        if(pos.getX() < getPos().getX())
            tempPos.setX(pos.x - offset);
        if(pos.getX() > getPos().getX())
            tempPos.setX(pos.x + offset);
        if(pos.getY() > getPos().getY())
            tempPos.setY(pos.y + offset);
        if(pos.getY() < getPos().getY())
            tempPos.setY(pos.y - offset);
        return getWorld().GetTile(Position.toWorldPosition(tempPos)).isHasCollider();
    }

    public void move() {
        Vector2 new_pos = new Vector2();
        float x = getPos().getX();
        float y = getPos().getY();
        if(rCore.IsKeyDown(KEY_W)){
            y += -getSpeed();
        }
        if(rCore.IsKeyDown(KEY_S)){
            y += getSpeed();
        }
        if(rCore.IsKeyDown(KEY_A)){
            x += -getSpeed();
        }
        if(rCore.IsKeyDown(KEY_D)){
            x += getSpeed();
        }

        new_pos.setX(x);
        new_pos.setY(y);
        if(!inCollision(new_pos))
            setPos(new_pos);

    }
}
