package com.blockman.data;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Classe Box que está encarregue de criar caixas de todo o tipo para serem usadas no mapa
 * Created by Pedro on 01/06/2015.
 */
public class Box {
	
	/**
	 * String que diferencia o tipo de caixa
	 */
    private String kind;
    
    /**
     * Sprite usada na caixa
     */
    private Sprite sprite;
    
    /**
     * O corpo da caixa
     */
    private Body body = null;

    /**
     * Construtor da classe Box, cria uma caixa
     * @param kind diz o tipo da caixa, pode ser uma parede, caixa transportada, ou vazio
     * @param sprite sprite usada
     */
    Box(String kind, Sprite sprite){
        this.kind = kind;
        this.sprite = sprite;
    }
    
    /**
     * Retorna o tipo de caixa
     * @return kind
     */
    public String getKind(){
        return kind;
    }
    
    /**
     * Altera o tipo de caixa
     * @param new_kind
     */
    public void setKind(String new_kind){
        this.kind = new_kind;
    }

    /**
     * Altera o corpo da caixa
     * @param b
     */
    public void setBody(Body b){
    	this.body = b;
    }
    
    /**
     * Retorna o corpo da caixa
     * @return body
     */
    public Body getBody(){
    	return body;
    }
    
    /**
     * Altera o sprite da caixa
     * @param sprite
     */
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    /**
     * Retorna a sprite usada
     * @return sprite
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     * Altera a caixa para vazio ("blank")
     */
	public void setBlank() {
		kind = "blank";
		sprite = null;
	}
}
