package com.blockman.test;

import static org.junit.Assert.*;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.junit.Test;

import blockman.logic.Logic;

import com.blockman.data.Map;
import com.blockman.game.Game;

public class Testing {
	
	//Constants used by game scene
	private static final int MAP_START_X = 300;
	private static final int MAP_START_Y = 7 * 800 / 12 - 100;
	
	final float PLAYER_START_X = 300 + 245;
	final float PLAYER_START_Y = 7 * 800 / 12 - 60;
	
	private Sprite player;
	//--------------------------------------------------------
	
	public Map generateTestEnvironement(){
		Map map = new Map();
		map.generateTeste();
		return map;
	}
	
	@Test
	//Teste de gerar mapa de testes
	public void testGenerateMap() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic(null, null);
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null); 
		assertEquals(18, test_map.getWidth());
		assertEquals(7, test_map.getHeight());
	}
	
	@Test
	//Teste de largar caixa à esquerda
	public void testLeaveBoxLeft() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertTrue(gameLogic.leave_box_left(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertFalse(gameLogic.leave_box_left(PLAYER_START_X, MAP_START_Y + 120, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
	}
	
	@Test
	//Teste de largar caixa à direita
	public void testLeaveBoxRight() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertTrue(gameLogic.leave_box_right(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertFalse(gameLogic.leave_box_right(PLAYER_START_X, MAP_START_Y + 100 * test_map.getWidth() - 120, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
	}
	
	@Test
	//Teste de apanhar caixa à esquerda
	public void removeBoxLeft() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertFalse(gameLogic.remove_box_left(PLAYER_START_X - 50, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null));
		assertTrue(gameLogic.leave_box_left(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertTrue(gameLogic.remove_box_left(PLAYER_START_X - 50, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null));
	}
	
	@Test
	//Teste de apanhar caixa à direita
	public void removeBoxRight() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertFalse(gameLogic.remove_box_right(PLAYER_START_X + 60 , PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null));
		assertTrue(gameLogic.leave_box_right(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertTrue(gameLogic.remove_box_right(PLAYER_START_X + 60 , PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null));
	}
	
	@Test
	//Teste da flag winning 
	public void	winningFlag() {
		Logic gameLogic = new Logic();
		assertFalse(gameLogic.getWin());
		gameLogic.setWin(true);
		assertTrue(gameLogic.getWin());
	}
	
	@Test
	//Teste da flag carringBox
	public void	carringBoxFlag() {
		Logic gameLogic = new Logic();
		assertFalse(gameLogic.getCarringBox());
		gameLogic.setCarringBox(true);
		assertTrue(gameLogic.getCarringBox());
	}
	
}
