package com.microrpg.entity;

import com.microrpg.constants.EngineConstants;
import com.microrpg.utils.AABB;
import com.microrpg.world.Overworld;
import com.microrpg.world.Position;
import com.microrpg.world.World;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public abstract class Entity {

    private Vector2 pos;
    private Position worldPos;
    private int hp;
    private float speed;
    private int tile_x;
    private int tile_y;

    private World world;
    private boolean hasCollision;
    private AABB collider;

    private Raylib raylib;

    private Texture2D texture;

    public Texture2D getTexture() {
        return texture;
    }

    public Entity(Vector2 pos, int hp, float speed, int tile_x, int tile_y, World world, Raylib raylib, Texture2D texture) {
        this.hp = hp;
        this.speed = speed;
        this.pos = pos;
        worldPos = Position.toWorldPosition(pos);
        this.tile_x = tile_x;
        this.tile_y = tile_y;
        this.hasCollision = false;
        this.world = world;
        this.raylib = raylib;
        this.texture = texture;
    }

    protected Raylib GetRaylib() {
        return this.raylib;
    }

    public Position getWorldPos() {
        return worldPos;
    }

    public void UpdateWorldPos() {
        worldPos = Position.toWorldPosition(pos);
    }

    public void UpdateColliderPos() {
        collider.minX = getPos().x;
        collider.minY = getPos().y;
        collider.maxX = EngineConstants.SPRITE_SIZE;
        collider.maxY = EngineConstants.SPRITE_SIZE;
    }

    public World getWorld() {
        return world;
    }

    public AABB getCollider() {
        return collider;
    }

    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }

    public void setCollider(AABB collider) {
        this.collider = collider;
    }

    public void draw(Raylib raylib) {
        float spriteSize = (float) EngineConstants.SPRITE_SIZE;
        float rectX = (float) tile_x * spriteSize;
        float rectY = (float) tile_y * spriteSize;

        Rectangle rect = new Rectangle(rectX, rectY,spriteSize, spriteSize);

//        raylib.shapes.DrawRectangle((int) collider.minX, (int) collider.minY, (int) constants.CHUNK_SIZE, (int) collider.maxY, Color.GREEN);
        raylib.textures.DrawTextureRec(texture, rect, getPos(), Color.WHITE);

    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
        UpdateColliderPos();
        UpdateWorldPos();
    }


    protected Raylib getRaylib() {
        return raylib;
    }
}
