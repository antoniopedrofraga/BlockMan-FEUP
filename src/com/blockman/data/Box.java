package com.blockman.data;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Pedro on 01/06/2015.
 */
public class Box {
    private String kind;
    private Sprite sprite;
    private Body body = null;

    Box(String kind, Sprite sprite){
        this.kind = kind;
        this.sprite = sprite;
    }

    public String getKind(){
        return kind;
    }

    public void setKind(String new_kind){
        this.kind = new_kind;
    }

    public void setBody(Body b){
    	this.body = b;
    }
    
    public Body getBody(){
    	return body;
    }
    
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite(){
        return sprite;
    }

	public void setBlank() {
		kind = "blank";
		sprite = null;
	}
}
