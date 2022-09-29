package com.microrpg.ui;

import com.raylib.java.Raylib;
import com.raylib.java.core.camera.Camera2D;

public class UiManager {

    private boolean debugMode = false;

    private final DebugView debugView;
   // private final HotBar hotBar;


    public UiManager(Raylib raylib, Camera2D camera2D){
        debugView = new DebugView();
        //hotBar = new HotBar(raylib, camera2D);
    }

    public void setDebugMode(boolean debugMode){
        this.debugMode = debugMode;
    }

    public void Draw(){
        //hotBar.DrawElement();
        if(debugMode)
            debugView.DrawElement();
    }
}
