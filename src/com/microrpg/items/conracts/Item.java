package com.microrpg.items.conracts;

import com.raylib.java.shapes.Rectangle;

public interface Item {

    String getName();

    int getId();

    Rectangle getTextureRect();
}
