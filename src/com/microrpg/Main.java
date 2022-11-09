package com.microrpg;

import com.microrpg.entity.PlayerEntity;

import com.microrpg.ui.HotBar;

import com.microrpg.world.Position;
import com.microrpg.world.World;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import com.microrpg.constants.EngineConstants;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {

        // Initialization
        final int SCREEN_WIDTH = EngineConstants.SCREEN_WIDTH;
        final int SCREEN_HEIGHT = EngineConstants.SCREEN_HEIGHT;

        Raylib raylib = new Raylib();
        raylib.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Raylib-J [core] example -- basic window");
        raylib.core.SetTargetFPS(60);


        // Texture loading
        Image image = rTextures.LoadImage("src/assets/SpriteSheet.png");
        Texture2D texture = rTextures.LoadTextureFromImage(image);

        Image itemsImage = rTextures.LoadImage("src/assets/ItemsSpriteSheet.png");
        Texture2D itemsTexture = rTextures.LoadTextureFromImage(itemsImage);

        World world = new World(texture, raylib);

        // Entity and world creation
        //Generate world;
//        Overworld world = new Overworld(texture, 12345, raylib);
        PlayerEntity player = new PlayerEntity(Position.toScreenPosition(new Position(32, 32)), world, raylib, texture);

//        world.setPlayerPosition(player.getPos());

//        Camera
        Camera2D camera = new Camera2D();
        camera.target = player.getPos();
        camera.offset = new Vector2((float)SCREEN_WIDTH / 2f, (float)SCREEN_HEIGHT / 2f);
        camera.rotation = 0.0f;
        camera.zoom = 1f;

        // UI initialization
        HotBar hotBar = new HotBar(raylib, camera, texture, itemsTexture);

        player.setUiHotBar(hotBar);

        while(!raylib.core.WindowShouldClose()){

            // Update variables here
            player.Update();
//            world.Update(player.getPos());
            camera.target = player.getPos();

            // Draw here

            //Raylib draw calls
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            raylib.core.BeginMode2D(camera);

            //User draw calls
            world.draw();
//            world.draw();
            player.draw(raylib);

            // Draw FPS counter
            raylib.text.DrawFPS(
                    (int)(camera.target.x + SCREEN_WIDTH / 2) - 30,
                    (int)camera.target.y - SCREEN_HEIGHT /2 + 20,
                    Color.PURPLE);

            raylib.text.DrawText(player.getWorldPos().toString(), (int)(camera.target.x + SCREEN_WIDTH / 2) - 100,(int)camera.target.y - SCREEN_HEIGHT /2 + 60, 20, Color.PURPLE);

            hotBar.DrawElement();

            raylib.core.EndDrawing();
        }

    }
    private static String vecToString(Vector2 vec){
        return format("%.2f %.2f", vec.x, vec.y);
    }


}

