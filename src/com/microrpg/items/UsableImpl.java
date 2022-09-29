package com.microrpg.items;

import com.microrpg.items.conracts.Usable;
import com.raylib.java.shapes.Rectangle;

public class UsableImpl extends ItemImpl implements Usable {


    public UsableImpl(String name, int id, int x, int y) {
        super(name, id, x, y);
    }

    @Override
    public void use() {

    }

}
