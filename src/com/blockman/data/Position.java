package com.blockman.data;

/**
 * Classe Position que gera posi��es para todos os objetos do mapa
 * Created by Pedro on 01/06/2015.
 */
public class Position {
	
	/**
	 * Coordenada X da posi��o
	 */
    private int x;
    
    /**
     * Coordenada Y da posi��o
     */
    private int y;

    /**
     * Construtor da classe Position
     * @param x coordenada X
     * @param y coordenada Y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Altera a posi��o existente
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Altera a coordenada X da posi��o
     * @param x coordenada X
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Altera a coordenada Y da posi��o
     * @param y coordenada Y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Retorna a coordenada X da posi��o
     * @return x coordenada X
     */
    public int getX(){
        return x;
    }

    /**
     * Retorna a coordenada Y da posi��o
     * @return y coordenada Y
     */
    public int getY(){
        return y;
    }
}
