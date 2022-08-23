package com.microrpg.entity;

import com.microrpg.utils.AABB;
import com.microrpg.world.Overworld;

import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

import static com.raylib.java.core.input.Keyboard.*;


public class PlayerEntity extends Entity{

    public PlayerEntity(Vector2 pos, Overworld world, Raylib raylib) {
        super(pos, 10, 5f, 0, 1, world, raylib);
        setCollider(new AABB(0,0,1,1));

    }



    public void move() {
        Vector2 new_pos = getPos();
        if(rCore.IsKeyDown(KEY_W)){
            new_pos.setY(getPos().y += -getSpeed());
        }
        if(rCore.IsKeyDown(KEY_S)){
            new_pos.setY(getPos().y += getSpeed());
        }
        if(rCore.IsKeyDown(KEY_A)){
            new_pos.setX(getPos().x += -getSpeed());
        }
        if(rCore.IsKeyDown(KEY_D)){

            new_pos.setX(getPos().x += getSpeed());
        }

        setPos(new_pos);
    }
}
