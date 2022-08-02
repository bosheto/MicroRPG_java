package com.microrpg;

import com.microrpg.entity.PlayerEntity;
import com.microrpg.utils.OpenSimplexNoise;
import com.microrpg.world.Overworld;
import com.microrpg.world.Position;
import com.microrpg.world.chunk.Chunk;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import com.microrpg.constants.constants;

public class Main {
    public static void main(String[] args) {

        // Initialization
        final int SCREEN_WIDTH = constants.SCREEN_WIDTH;
        final int SCREEN_HEIGHT = constants.SCREEN_HEIGHT;
        Raylib raylib = new Raylib();
        raylib.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Raylib-J [core] example -- basic window");
        raylib.core.SetTargetFPS(60);
        Image image = rTextures.LoadImage("src/assets/SpriteSheet.png");
        Texture2D texture = rTextures.LoadTextureFromImage(image);

        // Entity and world creation
        PlayerEntity player = new PlayerEntity(Position.toWorldPosition(new Vector2(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)));
        Overworld world = new Overworld(player.getPos(), texture, 12345, raylib);


        while(!raylib.core.WindowShouldClose()){

            // Update variables here
            player.move();

            // Draw here
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            world.draw();


            player.draw(raylib, texture);
            raylib.text.DrawFPS(constants.SCREEN_WIDTH-30,0, Color.RED);
            raylib.core.EndDrawing();
        }


    }

}
