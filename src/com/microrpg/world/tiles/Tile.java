package com.microrpg.world.tiles;
import com.microrpg.constants.EngineConstants;

import com.microrpg.utils.AABB;
import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public abstract class Tile {

    public static final int TILE_MAX = 256;
    public static final Tile[] TILES = new Tile[TILE_MAX];

    public static final AirTile AIR_TILE = new AirTile(0);
    public static final GrassTile GRASS_TILE = new GrassTile(1);
    public static final SandTile SAND_TILE = new SandTile(2);
    public static final StoneTile STONE_TILE = new StoneTile(3);
    public static final WaterTile WATER_TILE = new WaterTile(4);
    public static final TreeTile TREE_TILE = new TreeTile(5);
    public static final SmallGrassTile SMALL_GRASS_TILE = new SmallGrassTile(6);
    public static final CobblestoneTile COBBLESTONE_TILE = new CobblestoneTile(7);
    public static final WoodenPlackTile WOODEN_PLACK_TILE = new WoodenPlackTile(8);
    public static final DirtTile DIRT_TILE = new DirtTile(9);
    private int tileId;
    private int sprite_y = 0;
    private int sprite_x = 0;


    private boolean collider;

    private String tileName;

    public Tile(int sprite_x, int sprite_y, int tileId, String tileName, boolean hasCollider)
    {

        this.tileId = tileId;
        this.tileName = tileName;
        assert(Tile.TILES[this.tileId] == null);
        Tile.TILES[tileId] = this;
        this.collider = hasCollider;
        this.sprite_x = sprite_x;
        this.sprite_y = sprite_y;
    }

    public int getTileId() {
        return tileId;
    }

    public void Draw(Raylib raylib, Position position, Texture2D texture){
        Rectangle rect = new Rectangle(
                (float)sprite_x * EngineConstants.SPRITE_SIZE,
                (float)sprite_y * EngineConstants.SPRITE_SIZE,
                (float) EngineConstants.SPRITE_SIZE, (float) EngineConstants.SPRITE_SIZE);

        raylib.textures.DrawTextureRec(texture, rect, Position.toScreenPosition(position), Color.WHITE);
    }

    public boolean collides(int x, int y, AABB e){

        return false;
    }

    public boolean hasCollider() {
        return collider;
    }

    @Override
    public String toString() {
        return tileName;
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
