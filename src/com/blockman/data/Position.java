package com.blockman.data;

/**
 * Classe Position que gera posições para todos os objetos do mapa
 * Created by Pedro on 01/06/2015.
 */
public class Position {
	
	/**
	 * Coordenada X da posição
	 */
    private int x;
    
    /**
     * Coordenada Y da posição
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
     * Altera a posição existente
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Altera a coordenada X da posição
     * @param x coordenada X
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Altera a coordenada Y da posição
     * @param y coordenada Y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Retorna a coordenada X da posição
     * @return x coordenada X
     */
    public int getX(){
        return x;
    }

    /**
     * Retorna a coordenada Y da posição
     * @return y coordenada Y
     */
    public int getY(){
        return y;
    }
}
