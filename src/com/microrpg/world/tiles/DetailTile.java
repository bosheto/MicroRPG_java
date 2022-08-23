package com.microrpg.world.tiles;

import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.textures.Texture2D;

public abstract class DetailTile extends Tile{

    Tile groundTile;

    public DetailTile(int sprite_x, int sprite_y, int tileId, String name, boolean hasCollider) {
        super(sprite_x, sprite_y, tileId, name, hasCollider);
    }

    public void setGroundTile(Tile groundTile) {
        this.groundTile = groundTile;
    }

    @Override
    public void Draw(Raylib raylib, Position position, Texture2D texture) {
        groundTile.Draw(raylib, position, texture);
        super.Draw(raylib, position, texture);
    }
}
