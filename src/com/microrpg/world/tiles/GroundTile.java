package com.microrpg.world.tiles;

import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public abstract class GroundTile extends Tile{

    Tile topTile;

    public GroundTile(int sprite_x, int sprite_y, int tileId) {
        super(sprite_x, sprite_y, tileId);
    }

    public void setTopTile(Tile topTile) {
        this.topTile = topTile;
    }

    @Override
    public void Draw(Raylib raylib, Position position, Texture2D texture) {
        super.Draw(raylib, position, texture);
        if(topTile != null){
            topTile.Draw(raylib, position, texture);
        }
    }
}
