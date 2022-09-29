package com.microrpg.entity;

import com.microrpg.constants.EngineConstants;
import com.microrpg.constants.UiConstants;
import com.microrpg.items.conracts.Item;
import com.microrpg.ui.HotBar;
import com.microrpg.utils.AABB;
import com.microrpg.world.Overworld;

import com.microrpg.world.Position;
import com.microrpg.world.contracts.Breakable;
import com.microrpg.world.tiles.Tile;
import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

import static com.raylib.java.core.input.Keyboard.*;


public class PlayerEntity extends Entity{

    private HotBar uiHotBar;
    private int hotBarPos = 0;

    private Item[] hotbarItems = new Item[UiConstants.HOT_BAR_SIZE];

    public PlayerEntity(Vector2 pos, Overworld world, Raylib raylib) {
        super(pos, 10, 4f, 0, 1, world, raylib);
        setCollider(new AABB(0,0,1,1));


    }

    public void setUiHotBar(HotBar uiHotBar) {
        this.uiHotBar = uiHotBar;
    }


    private boolean inCollision(Vector2 pos){
        Vector2 tempPos = new Vector2(pos.x, pos.y);
        float feather = 1;
        float offset = (EngineConstants.SPRITE_SIZE - feather)/2.0f ;

        if(pos.getX() < getPos().getX())
            tempPos.setX(pos.x - offset);
        if(pos.getX() > getPos().getX())
            tempPos.setX(pos.x + offset);
        if(pos.getY() > getPos().getY())
            tempPos.setY(pos.y + offset);
        if(pos.getY() < getPos().getY())
            tempPos.setY(pos.y - offset);
        return getWorld().GetTile(Position.toWorldPosition(tempPos)).hasCollider();
    }

    public void move() {
        HorizontalMove();
        VerticalMove();
    }

    public Position getMouseClickPosition(){
        Vector2 vPos = rCore.GetMousePosition();
        vPos.setX(Math.round(vPos.x / EngineConstants.SPRITE_SIZE));
        vPos.setY(Math.round(vPos.y / EngineConstants.SPRITE_SIZE));
        float x = (vPos.x - ((EngineConstants.SCREEN_WIDTH / 2f) / (float) EngineConstants.SPRITE_SIZE));
        float y = (vPos.y - ((EngineConstants.SCREEN_HEIGHT / 2f) / (float) EngineConstants.SPRITE_SIZE));

        Position pos = new Position((int)Math.floor(x), (int)Math.floor(y));

        return getWorldPos().add(pos);
    }

    private void VerticalMove(){
        Vector2 new_pos = new Vector2();
        float x = getPos().getX();
        float y = getPos().getY();
        if(rCore.IsKeyDown(KEY_W)){
            y += -1f * getSpeed();
        }
        if(rCore.IsKeyDown(KEY_S)){
            y += 1 * getSpeed();
        }
        new_pos.setY(y);
        new_pos.setX(getPos().x);
        if(!inCollision(new_pos))
            setPos(new_pos);
    }

    private void HorizontalMove(){
        Vector2 new_pos = new Vector2();
        float x = getPos().getX();
        float y = getPos().getY();

        if(rCore.IsKeyDown(KEY_A)){
            x += -1f * getSpeed();
        }
        if(rCore.IsKeyDown(KEY_D)){
            x += 1 * getSpeed();
        }
        new_pos.setX(x);
        new_pos.setY(getPos().y);
        if(!inCollision(new_pos))
            setPos(new_pos);
    }

    private void MouseClick(){
        Position tPos = getMouseClickPosition();

        if(rCore.IsMouseButtonDown(0)) {
            Tile t = getWorld().GetTile(tPos);
            Tile newT;
            if (t instanceof Breakable) {
                Breakable breakable = (Breakable) t;
                newT = breakable.BreakBlock();
                getWorld().SetTile(tPos, newT.getTileId());
            }
        }else if(rCore.IsMouseButtonDown(1)){
            Tile t = getWorld().GetTile(tPos);
            // TODO make the tile placed player selectable
            if(!(t instanceof Breakable)){
              getWorld().SetTile(tPos, Tile.WOODEN_PLACK_TILE.getTileId());
            }
        }
    }

    private void updateHotBar(){

        if(rCore.IsKeyDown(KEY_ONE))
            hotBarPos = 0;
        if(rCore.IsKeyDown(KEY_TWO))
            hotBarPos = 1;
        if(rCore.IsKeyDown(KEY_THREE))
            hotBarPos = 2;
        if(rCore.IsKeyDown(KEY_FOUR))
            hotBarPos = 3;
        if(rCore.IsKeyDown(KEY_FIVE))
            hotBarPos = 4;
        if(rCore.IsKeyDown(KEY_SIX))
            hotBarPos = 5;
        if(rCore.IsKeyDown(KEY_SEVEN))
            hotBarPos = 6;
        if(rCore.IsKeyDown(KEY_EIGHT))
            hotBarPos = 7;

        float mouseWheelMove = rCore.GetMouseWheelMove();
        if(mouseWheelMove != 0){
            hotBarPos += mouseWheelMove;
        }

        if(hotBarPos > 7)
            hotBarPos = 7;
        if(hotBarPos < 0)
            hotBarPos = 0;

        uiHotBar.setSelectorPos(hotBarPos);
    }

    public void Update(){
        uiHotBar.setItems(hotbarItems);
        updateHotBar();
        MouseClick();
        move();
    }

}
