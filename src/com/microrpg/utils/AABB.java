package com.microrpg.utils;
/*

 */


import static java.lang.String.format;

public class AABB {
    public float minX, minY, maxX, maxY;

    public AABB(float minX, float minY, float maxX, float maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean containsPoint(float px, float py) {
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

    @Override
    public String toString() {
        return format("min x: %.2f max x: %.2f min y: %.2f max y: %.2f", minX, maxX, minY, maxY);
    }
}
