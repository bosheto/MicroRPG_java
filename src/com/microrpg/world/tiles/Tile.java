package com.microrpg.world.tiles;
import com.microrpg.constants.constants;

import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public abstract class Tile {

    private int tileId;
    private int sprite_y = 0;
    private int sprite_x = 0;

    public Tile(int sprite_x, int sprite_y, int tileId)
    {
        this.sprite_x = sprite_x;
        this.sprite_y = sprite_y;
        this.tileId = tileId;
    }

    public int getTileId() {
        return tileId;
    }

    public void Draw(Raylib raylib, Position position, Texture2D texture){
        Rectangle rect = new Rectangle((float)sprite_x * constants.SPRITE_SIZE, (float)sprite_y * constants.SPRITE_SIZE
                ,(float)constants.SPRITE_SIZE, (float)constants.SPRITE_SIZE);

        raylib.textures.DrawTextureRec(texture, rect, Position.toScreenPosition(position), Color.WHITE);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tile)
        {
            return ((Tile) obj).getTileId() == tileId;
        }
        return false;
    }
}
