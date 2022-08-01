package com.microrpg;

import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.Position;
import com.microrpg.world.chunk.Chunk;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import com.microrpg.constants.constants;

public class Main {
    public static void main(String[] args) {

        final int SCREEN_WIDTH = constants.SCREEN_WIDTH;
        final int SCREEN_HEIGHT = constants.SCREEN_HEIGHT;
        Raylib raylib = new Raylib();
        raylib.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Raylib-J [core] example -- basic window");
        raylib.core.SetTargetFPS(60);
        Image image = rTextures.LoadImage("src/assets/SpriteSheet.png");
        Texture2D texture = rTextures.LoadTextureFromImage(image);
        Chunk chunk = new Chunk(new Position(0,0), texture, raylib);
        Chunk chunk1 = new Chunk(new Position(constants.CHUNK_SIZE,0), texture, raylib);


        while(!raylib.core.WindowShouldClose()){

            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);

            chunk.draw();
            chunk1.draw();
            raylib.text.DrawFPS(constants.SCREEN_WIDTH-30,0, Color.RED);
            raylib.core.EndDrawing();
        }
    }

}
