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
	private int exit_x;
	private int exit_y;
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

		map[7][0] = new Box("rock", 6, 0, null);
		map[3][0] = new Box("rock", 6, 0, null);
		map[3][1] = new Box("rock", 6, 0, null);
		map[11][1] = new Box("rock", 6, 0, null);
		map[11][0] = new Box("rock", 6, 0, null);

		map[13][0] = new Box("box", 6, 0, null);
		map[9][0] = new Box("box", 6, 0, null);

		exit_x = 1;
		exit_y = 0;
		map[1][0] = new Box("exit", 6, 0, null);

		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock",0,i, null);
			map[width - 1][i] = new Box("rock", width - 1, i, null);
		}
	}

	public void generateTeste(){

		width = 18;
		height = 7;
		map = new Box[width][height];
		sprites = new ArrayList<Position>();

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", a, i, null);
			}
		}
		exit_x = 12;
		exit_y = 0;
		map[12][0] = new Box("exit", 1, 0, null);
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock",0,i, null);
			map[width - 1][i] = new Box("rock", width - 1, i, null);
		}
	}

	public void generateLevel2(){

		width = 18;
		height = 7;
		map = new Box[width][height];
		sprites = new ArrayList<Position>();

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", a, i, null);
			}
		}
		
		map[15][0] = new Box("box", 6, 0, null);
		map[14][0] = new Box("box", 6, 0, null);
		map[14][1] = new Box("box", 6, 0, null);
		map[12][0] = new Box("box", 6, 0, null);
		
		map[11][0] = new Box("rock", 6, 0, null);
		map[11][1] = new Box("rock", 6, 0, null);
		
		map[6][0] = new Box("box", 6, 0, null);
		
		exit_x = 0;
		exit_y = 2;
		
		map[10][0] = new Box("exit", 1, 0, null);
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock",0,i, null);
			map[width - 1][i] = new Box("rock", width - 1, i, null);
		}
	}


	public void generateTutorial(){
		width = 18;
		height = 7;
		map = new Box[width][height];
		sprites = new ArrayList<Position>();

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", a, i, null);
			}
		}

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

	public int getExitX(){
		return exit_x;
	}

	public int getExitY(){
		return exit_y;
	}
	public int getLowerY(int x, int y) {
		for(int i = y ; i > 0; i-- ){
			if(map[x][i].getKind() == "rock" || map[x][i].getKind() == "box"){
				return i + 1;
			}
		}
		return 0;
	}
}
