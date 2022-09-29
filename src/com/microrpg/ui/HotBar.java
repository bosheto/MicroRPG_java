package com.microrpg.ui;

import com.microrpg.constants.EngineConstants;
import com.microrpg.constants.UiConstants;
import com.microrpg.items.WoodPickaxe;
import com.microrpg.items.conracts.Item;
import com.microrpg.ui.contracts.UiElement;
import com.microrpg.utils.Utils;
import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;


public class HotBar implements UiElement {

    private int size = UiConstants.HOT_BAR_SIZE;
    private int squareSize = 40;
    private int barX = 100;
    private int barY = UiConstants.SCREEN_SIZE_Y / 2;
    private final int texture_x = 0;
    private final int texture_y = 2;

    private final int selector_x = 1;
    private final int selector_y = 2;

    private int selectorPosition = 0;

    private final Raylib raylib;
    private final Camera2D camera;

    private final Texture2D texture;

    private final Texture2D itemsTexture;

    private Item[] items = new Item[size];

    public HotBar(Raylib raylib, Camera2D camera, Texture2D texture, Texture2D itemsTexture){
        this.raylib = raylib;
        this.camera = camera;
        this.texture = texture;
        this.itemsTexture = itemsTexture;
    }

    @Override
    public void DrawElement() {
       for(int i = -size / 2; i < size / 2 ; i++)
            DrawBarSquare(i);
       DrawSelector();
       DrawItemTextures();
    }

    @Override
    public void UpdateElement() {

    }

    public void setSelectorPos(int pos){
        selectorPosition = pos;
    }

    public void setItems(Item[] items){
        if(items.length != size)
            throw new IndexOutOfBoundsException("Hot bar items array must be the same size as hot bar");
        this.items = items;
    }

    private void DrawSelector(){
        int selectorPos = selectorPosition - size /2;
        raylib.textures.DrawTextureRec(texture, Utils.getTextureRect(selector_x, selector_y),
                new Vector2(camera.target.x +(EngineConstants.SPRITE_SIZE * selectorPos),
                        camera.target.y + EngineConstants.SCREEN_HEIGHT /2 - EngineConstants.SPRITE_SIZE),
                Color.WHITE);
    }

    private void DrawBarSquare(int pos){

        raylib.textures.DrawTextureRec(texture,
                Utils.getTextureRect(texture_x, texture_y),
                new Vector2(camera.target.x +(EngineConstants.SPRITE_SIZE * pos),
                        camera.target.y + EngineConstants.SCREEN_HEIGHT /2 - EngineConstants.SPRITE_SIZE),
                Color.WHITE);
    }


    private void DrawItemTextures(){
        int pos = 0;
        for (Item item : items) {
            if(item == null){
                pos ++;
                continue;
            }
            raylib.textures.DrawTextureRec(itemsTexture,
                    item.getTextureRect(),
                    new Vector2(camera.target.x +(EngineConstants.SPRITE_SIZE * (pos - size /2 )),
                            camera.target.y + EngineConstants.SCREEN_HEIGHT /2 - EngineConstants.SPRITE_SIZE),
                    Color.WHITE);
            pos++;
        }

    }
}
