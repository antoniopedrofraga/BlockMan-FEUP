package com.blockman.data;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Pedro on 01/06/2015.
 */
public class Box {
    private String kind;
    private int x;
    private int y;
    private Sprite sprite;

    Box(String kind, int x, int y, Sprite sprite){
        this.kind = kind;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public String getKind(){
        return kind;
    }

    public void setKind(String new_kind){
        this.kind = new_kind;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
