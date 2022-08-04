package com.microrpg.utils;
/*

 */



public class AABB {
    public int minX, minY, maxX, maxY;

    public AABB(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean containsPoint(int px, int py) {
        return px >= this.minX && px <= this.maxX && py >= this.minY && py <= this.maxY;
    }

    public boolean collides(AABB other){
        return this.minX <= other.maxX &&
                this.maxX >= other.minX &&
                this.minY <= other.maxY &&
                this.maxY >= other.minY;
    }


    public static boolean collide(int ax0, int ay0, int ax1, int ay1,
                                  int bx0, int by0, int bx1, int by1){
        return ax0 <= bx1 &&
                ax1 >= bx0 &&
                ay0 <= by1 &&
                ay1 >= by0;
    }


}
