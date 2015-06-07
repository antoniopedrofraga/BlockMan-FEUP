package com.blockman.test;

import static org.junit.Assert.*;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.junit.Test;

import blockman.logic.Logic;

import com.blockman.data.Map;
import com.blockman.game.Game;
/**
 * Classe de testes do jogo
 * @author Miguel
 *
 */
public class Testing {
	
	//Constants used by game scene
	/**
	 * Início do Mapa da coordenada X
	 */
	private static final int MAP_START_X = 300;
	
	/**
	 * Início do Mapa da coordenada Y
	 */
	private static final int MAP_START_Y = 7 * 800 / 12 - 100;
	
	/**
	 * Coordenada X de onde o jogador começa
	 */
	final float PLAYER_START_X = 300 + 245;
	
	/**
	 * Coordenada Y de onde o jogador começa
	 */
	final float PLAYER_START_Y = 7 * 800 / 12 - 60;
	
	/**
	 * Sprite do jogador
	 */
	private Sprite player;
	//--------------------------------------------------------
	/**
	 * Gera o ambiente de testes (o mapa)
	 * @return map mapa gerado
	 */
	public Map generateTestEnvironement(){
		Map map = new Map();
		map.generateTeste();
		return map;
	}
	
	@Test
	/**
	 * Testa se o mapa é bem gerado
	 */
	public void testGenerateMap() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic(null, null);
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null); 
		assertEquals(18, test_map.getWidth());
		assertEquals(7, test_map.getHeight());
	}
	
	@Test
	/**
	 * Testa se o jogador consegue largar caixas à esquerda
	 */
	public void testLeaveBoxLeft() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertTrue(gameLogic.leave_box_left(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertFalse(gameLogic.leave_box_left(PLAYER_START_X, MAP_START_Y + 120, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
	}
	
	@Test
	/**
	 * Testa se o jogador consegue largar caixas à direita
	 */
	public void testLeaveBoxRight() {
		Map test_map = generateTestEnvironement();
		Logic gameLogic = new Logic();
		gameLogic.setMap(test_map);
		gameLogic.setPhysics(null);
		assertTrue(gameLogic.leave_box_right(PLAYER_START_X, PLAYER_START_Y, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
		assertFalse(gameLogic.leave_box_right(PLAYER_START_X, MAP_START_Y + 100 * test_map.getWidth() - 120, MAP_START_X, MAP_START_Y, 100, null, null, null, false));
	}
	
	@Test
	/**
	 * Testa se o jogador consegue apanhar caixas à esquerda
	 */
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
	/**
	 * Testa se o jogador consegue apanhar caixas à direita
	 */
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
	/**
	 * Testa se o jogador consegue chegar ao final e se altera o boolean win da classe Logic
	 */
	public void	winningFlag() {
		Logic gameLogic = new Logic();
		assertFalse(gameLogic.getWin());
		gameLogic.setWin(true);
		assertTrue(gameLogic.getWin());
	}
	
	@Test
	/**
	 * Testa se, quando o jogador apanha a caixa, o boolean carringBox é alterado na classe Logic
	 */
	public void	carringBoxFlag() {
		Logic gameLogic = new Logic();
		assertFalse(gameLogic.getCarringBox());
		gameLogic.setCarringBox(true);
		assertTrue(gameLogic.getCarringBox());
	}
	
}
