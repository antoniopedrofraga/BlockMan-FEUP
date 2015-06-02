package com.blockman.data;

import org.andengine.entity.sprite.Sprite;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Pedro on 01/06/2015.
 */
public class Map {
    private int width;
    private int height;
    private Box map[][];
    private ArrayList<Position> sprites;

    public Map(int width, int height){
        this.width = width;
        this.height = height;
        map = new Box[width][height];
        sprites = new ArrayList<Position>();
    }
    public Map(){};

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Box [][] getMap(){
        return map;
    }

    public void generateMap(){
       width = 18;
       height = 7;
       map = new Box[width][height];
       sprites = new ArrayList<Position>();

       for(int i = 0; i < height; i++){
           for(int a = 0; a < width; a++){
                map[a][i] = new Box("blank", a, i, null);
           }
       }

       map[6][0] = new Box("rock", 6, 0, null);
       for(int i = 0; i < height; i++){
            map[0][i] = new Box("rock",0,i, null);
            map[width - 1][i] = new Box("rock", width - 1, i, null);
       }
    }

    public void pushPos(Position pos){
        sprites.add(pos);
    }

    public ArrayList<Position> getSprites(){
        return sprites;
    }
}
