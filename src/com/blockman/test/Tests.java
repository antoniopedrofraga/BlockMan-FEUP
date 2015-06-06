package com.blockman.test;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.blockman.data.Map;

public class Tests {
	
	//Constants used by game scene
	private static final int MAP_START_X = 300;
	private static final int MAP_START_Y = 7 * 800 / 12 - 100;
	private Sprite player;
	//--------------------------------------------------------
	
	public void generateTestEnvironement(Map map){
		map = new Map(18, 7);
		map.generateTeste();
	}
	
	@Test
	//Teste de caminhar para a esquerda
	public void testGenerateMap() {
		
	}
}
