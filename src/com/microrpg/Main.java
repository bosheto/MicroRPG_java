package com.microrpg;

import com.microrpg.entity.PlayerEntity;

import com.microrpg.world.Overworld;


import com.microrpg.world.Position;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.camera.Camera2D;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import com.microrpg.constants.constants;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {

        // Initialization
        final int SCREEN_WIDTH = constants.SCREEN_WIDTH;
        final int SCREEN_HEIGHT = constants.SCREEN_HEIGHT;

        Raylib raylib = new Raylib();
        raylib.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Raylib-J [core] example -- basic window");
        raylib.core.SetTargetFPS(60);


        // Texture loading
        Image image = rTextures.LoadImage("src/assets/SpriteSheet.png");
        Texture2D texture = rTextures.LoadTextureFromImage(image);

        // Entity and world creation
        //Generate world;
        Overworld world = new Overworld(texture, 12345, raylib);
        PlayerEntity player = new PlayerEntity(new Vector2(0, 0), world, raylib);

        world.setPlayerPosition(player.getPos());

        //Camera
        Camera2D camera = new Camera2D();
        camera.target = player.getPos();
        camera.offset = new Vector2((float)SCREEN_WIDTH / 2, (float)SCREEN_HEIGHT / 2);
        camera.rotation = 0.0f;
        camera.zoom = 1f;

        while(!raylib.core.WindowShouldClose()){

            // Update variables here
            player.move();
            world.Update(player.getPos());
            camera.target = player.getPos();


            // Draw here

            //Raylib draw calls
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            raylib.core.BeginMode2D(camera);

            //User draw calls

            world.draw();
            player.draw(raylib, texture);

            // Draw FPS counter
            raylib.text.DrawFPS((int)(camera.target.x + SCREEN_WIDTH / 2) - 30,(int)camera.target.y - SCREEN_HEIGHT /2 + 20  , Color.PURPLE);
            raylib.text.DrawText(Position.toWorldPosition(player.getPos()).toString(), (int)(camera.target.x + SCREEN_WIDTH / 2) - 100,(int)camera.target.y - SCREEN_HEIGHT /2 + 60, 20, Color.PURPLE);
            raylib.text.DrawText(format("%.2f", player.getCollider().minX), (int)(camera.target.x + SCREEN_WIDTH / 2) - 100,(int)camera.target.y - SCREEN_HEIGHT /2 + 80, 20, Color.PURPLE);

           // raylib.text.DrawText(world.GetTile(player.getWorldPos()).toString(),(int)(camera.target.x + SCREEN_WIDTH / 2) - 100,(int)camera.target.y - SCREEN_HEIGHT /2 + 80, 20, Color.PURPLE );
            //raylib.text.DrawText(player.getCollider().toString(),(int)(camera.target.x + SCREEN_WIDTH / 2) - 300,(int)camera.target.y - SCREEN_HEIGHT /2 + 100, 20, Color.PURPLE );
            //raylib.text.DrawText(world.GetTile(Position.toWorldPosition(new Vector2(player.getCollider().maxX, player.getCollider().maxY))).toString(),(int)(camera.target.x + SCREEN_WIDTH / 2) - 100,(int)camera.target.y - SCREEN_HEIGHT /2 + 80, 20, Color.PURPLE );


            raylib.core.EndDrawing();
        }


    }

}
