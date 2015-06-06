package com.blockman.data;


/**
 * Created by Pedro on 01/06/2015.
 */
public class Map {
	private int width;
	private int height;
	private int exit_x;
	private int exit_y;
	private Box map[][];

	public Map(int width, int height){
		this.width = width;
		this.height = height;
		map = new Box[width][height];
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

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}

		map[7][0] = new Box("rock", null);
		map[3][0] = new Box("rock", null);
		map[3][1] = new Box("rock", null);
		map[11][1] = new Box("rock", null);
		map[11][0] = new Box("rock", null);

		map[13][0] = new Box("box", null);
		map[9][0] = new Box("box", null);

		exit_x = 1;
		exit_y = 0;
		map[1][0] = new Box("exit", null);

		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}

	public void generateTeste(){

		width = 18;
		height = 7;
		map = new Box[width][height];

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}
		exit_x = 12;
		exit_y = 0;
		map[12][0] = new Box("exit", null);
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}

	public void generateLevel2(){

		width = 20;
		height = 9;
		map = new Box[width][height];
		
		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}
		
		map[5][0] = new Box("box", null);
		map[8][0] = new Box("rock", null);
		map[8][1] = new Box("rock", null);
		map[9][0] = new Box("box", null);
		map[11][0] = new Box("box", null);
		map[11][1] = new Box("box", null);
		map[12][0] = new Box("box", null);
		
		exit_x = 1;
		exit_y = 3;
		map[1][1] = new Box("rock", null);
		map[1][0] = new Box("rock", null);
		map[1][2] = new Box("rock", null);
		map[1][3] = new Box("exit", null);
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}
	
	public void generateLevel3(){

		width = 20;
		height = 9;
		map = new Box[width][height];
		
		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}
		
		map[2][0] = new Box("rock", null);
		map[2][1] = new Box("rock", null);
		map[2][2] = new Box("rock", null);
		map[2][3] = new Box("rock", null);
		
		map[3][0] = new Box("rock", null);
		map[3][1] = new Box("rock", null);
		map[3][2] = new Box("rock", null);
		map[3][3] = new Box("rock", null);
		
		map[4][0] = new Box("rock", null);
		map[4][1] = new Box("rock", null);
		map[4][2] = new Box("rock", null);
		map[4][3] = new Box("rock", null);
		
		map[15][0] = new Box("box", null);
		map[14][0] = new Box("box", null);
		map[13][0] = new Box("box", null);
		
		map[12][0] = new Box("rock", null);
		map[12][1] = new Box("rock", null);
		map[12][2] = new Box("rock", null);
		
		map[10][0] = new Box("box", null);
		map[9][0] = new Box("box", null);
		map[9][1] = new Box("box", null);
		map[8][0] = new Box("box", null);
		map[8][1] = new Box("box", null);
		map[8][2] = new Box("box", null);
		
		exit_x = 1;
		exit_y = 0;
		
		map[1][0] = new Box("exit", null);
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}
	
	public void generateLevel4(){

		width = 20;
		height = 9;
		map = new Box[width][height];
		
		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}
		map[13][0] = new Box("rock", null);
		map[14][0] = new Box("box", null);
		map[15][0] = new Box("box", null);
		
		map[12][0] = new Box("rock", null);
		map[12][1] = new Box("rock", null);
		
		map[11][0] = new Box("rock", null);
		map[11][1] = new Box("rock", null);
		map[11][2] = new Box("rock", null);
		
		map[10][0] = new Box("rock", null);
		map[10][1] = new Box("rock", null);
		map[10][2] = new Box("rock", null);
		map[10][3] = new Box("rock", null);
		
		map[9][0] = new Box("rock", null);
		map[9][1] = new Box("rock", null);
		map[9][2] = new Box("rock", null);
		map[9][3] = new Box("rock", null);
		map[9][4] = new Box("rock", null);
		
		map[8][0] = new Box("rock", null);
		map[8][1] = new Box("rock", null);
		map[8][2] = new Box("rock", null);
		map[8][3] = new Box("rock", null);
		map[8][4] = new Box("rock", null);
		map[8][5] = new Box("box", null);
		
		map[7][0] = new Box("rock", null);
		map[7][1] = new Box("rock", null);
		map[7][2] = new Box("rock", null);
		map[7][3] = new Box("rock", null);
		map[7][4] = new Box("rock", null);
		
		map[6][0] = new Box("rock", null);
		map[6][1] = new Box("rock", null);
		map[6][2] = new Box("rock", null);
		map[6][3] = new Box("rock", null);
		
		map[5][0] = new Box("rock", null);
		map[5][1] = new Box("rock", null);
		map[5][2] = new Box("rock", null);
		
		map[4][0] = new Box("rock", null);
		map[4][1] = new Box("rock", null);
		
		map[3][0] = new Box("rock", null);
		
		map[2][0] = new Box("box", null);

		exit_x = 1;
		exit_y = 3;
		
		map[1][1] = new Box("rock", null);
		map[1][2] = new Box("rock", null);
		map[1][0] = new Box("rock", null);
		map[1][3] = new Box("exit", null);
	
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}
	
	
	public void generateLevel5(){

		width = 24;
		height = 9;
		map = new Box[width][height];
		
		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}
		
		exit_x = 1;
		exit_y = 3;
		
		map[11][0] = new Box("rock", null);
		map[11][1] = new Box("rock", null);
		
		map[7][0] = new Box("rock", null);
		map[7][1] = new Box("rock", null);
		
		map[19][0] = new Box("rock", null);
		map[19][1] = new Box("rock", null);
		map[19][2] = new Box("rock", null);
		map[19][3] = new Box("rock", null);
		
		map[2][0] = new Box("rock", null);
		map[2][1] = new Box("rock", null);
		
		map[1][0] = new Box("rock", null);
		map[1][1] = new Box("rock", null);

		map[20][0] = new Box("exit", null);
		
		map[18][0] = new Box("box", null);
		map[17][0] = new Box("box", null);
		map[12][0] = new Box("box", null);
		
		map[10][0] = new Box("box", null);
		
		map[3][0] = new Box("box", null);
		map[3][1] = new Box("box", null);
		map[2][2] = new Box("box", null);
		map[1][2] = new Box("box", null);
		map[1][3] = new Box("box", null);
		
		
		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}

	public void generateTutorial(){
		width = 18;
		height = 7;
		map = new Box[width][height];

		for(int i = 0; i < height; i++){
			for(int a = 0; a < width; a++){
				map[a][i] = new Box("blank", null);
			}
		}

		for(int i = 0; i < height; i++){
			map[0][i] = new Box("rock", null);
			map[width - 1][i] = new Box("rock", null);
		}
	}


	public int getExitX(){
		return exit_x;
	}

	public int getExitY(){
		return exit_y;
	}
	public int getLowerY(int x, int y) {
		for(int i = y ; i >= 0; i-- ){
			if(map[x][i].getKind() == "rock" || map[x][i].getKind() == "box"){
				return i + 1;
			}
		}
		return 0;
	}
	
	public boolean isSomethingUp(int x, int y) {
		if(map[x][y+1].getKind() == "blank") return false;
		return true;
	}
}
