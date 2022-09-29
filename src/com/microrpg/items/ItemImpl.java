package com.microrpg.items;

import com.microrpg.items.conracts.Item;
import com.microrpg.utils.Utils;
import com.raylib.java.shapes.Rectangle;


public abstract class ItemImpl implements Item {

    String name;
    int id;

    int spriteX = 0;
    int spriteY = 0;


    public ItemImpl(String name, int id, int x, int y){
        this.name = name;
        this.id = id;
        spriteX = x;
        spriteY = y;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public Rectangle getTextureRect() {
        return Utils.getTextureRect(spriteX, spriteY);
    }
}
