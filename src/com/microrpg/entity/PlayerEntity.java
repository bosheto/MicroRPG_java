package com.microrpg.entity;

import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import static com.raylib.java.core.input.Keyboard.*;


public class PlayerEntity extends Entity{

    public PlayerEntity(Position pos) {
        super(pos, 10, 0.1f, 0, 1);
    }

    public void move() {
        Position new_pos = getPos();
        if(rCore.IsKeyDown(KEY_W)){
            new_pos = new_pos.add(new Position(0, -getSpeed()));
        }
        if(rCore.IsKeyDown(KEY_S)){
            new_pos = new_pos.add(new Position(0, getSpeed()));
        }
        if(rCore.IsKeyDown(KEY_A)){
            new_pos = new_pos.add(new Position(-getSpeed(), 0));
        }
        if(rCore.IsKeyDown(KEY_D)){
            new_pos = new_pos.add(new Position(getSpeed(), 0));
        }

        setPos(new_pos);
    }
}
