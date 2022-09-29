package com.microrpg.utils;

import com.microrpg.constants.EngineConstants;
import com.raylib.java.shapes.Rectangle;

public class Utils {

    public static Rectangle getTextureRect(int x, int y){
        return new Rectangle(
                x * EngineConstants.SPRITE_SIZE,
                y * EngineConstants.SPRITE_SIZE,
                EngineConstants.SPRITE_SIZE,
                EngineConstants.SPRITE_SIZE
        );
    }

}
