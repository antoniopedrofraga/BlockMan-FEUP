package blockman.logic;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.blockman.data.Map;

/**
 * Classe Logic que está encarregue da lógica de todo o jogo, exceto a física
 * @author Miguel
 *
 */
public class Logic {
	
	/**
	 * Mapa do jogo
	 */
	private Map map;
	
	/**
	 * Física do jogo
	 */
	private PhysicsWorld physicsWorld;
	
	/**
	 * Boolean que verifica se o jogador está a carregar uma caixa
	 */
	private boolean carringBox = false;
	
	/**
	 * Boolean que verifica se o jogador passou o nível
	 */
	private boolean win = false;
	
	/**
	 * O construtor da lógica de jogo
	 * 
	 * @param map mapa de jogo
	 * @param physicsWorld motor das físicas do jogo
	 */
	public Logic(Map map,PhysicsWorld physicsWorld){
		this.map = map;
		this.physicsWorld = physicsWorld;
	}
	
	/**
	 * Construtor vazio da lógica
	 */
	public Logic() {
	}
	
	/**
	 * Função que remove a caixa da esquerda, tendo em conta a posição do jogador, as coordenadas do mapa e o seu espacamento
	 * @param player_pos_x Coordenada X do Jogador
	 * @param player_pos_y Coordenada Y do Jogador
	 * @param mapStartX Coordenada X do início do mapa
	 * @param mapStartY Coordenada Y do início do mapa
	 * @param spacing Espacamento pretendido
	 * @param scene A cena onde se altera os desenhos
	 * @return verdadeiro se foi possível remover a caixa, falso se não
	 */
	public boolean remove_box_left(float player_pos_x, float player_pos_y, int mapStartX, int mapStartY, int spacing, Scene scene) {
		for(int i = 0; i < map.getHeight(); i++)
			for(int a = 0; a < map.getWidth(); a++){
				int pos_x = mapStartX + a * spacing;
				int pos_y = mapStartY - i * spacing;
				if(pos_x < player_pos_x && pos_x + 100 > player_pos_x)
					if(pos_y < player_pos_y && pos_y + 100 > player_pos_y){
						System.out.println(map.getMap()[a][i].getKind());
						System.out.println("A = " + a);
						if(!map.isSomethingUp(a,i))
							if(map.getMap()[a][i].getKind() != "blank"){
								if(map.getMap()[a][i].getSprite() != null)
									scene.detachChild(map.getMap()[a][i].getSprite());
								if(physicsWorld != null){
									physicsWorld.destroyBody(map.getMap()[a][i].getBody());
									map.getMap()[a][i].getSprite().detachSelf();
									map.getMap()[a][i].getSprite().dispose();
								}
								
								map.getMap()[a][i].setBlank();
								return true;
							}
					}
			}
		return false;
	}

	/**
	 * Função que remove a caixa da direita, tendo em conta a posição do jogador, as coordenadas do mapa e o seu espacamento
	 * @param player_pos_x Coordenada X do Jogador
	 * @param player_pos_y Coordenada Y do Jogador
	 * @param mapStartX Coordenada X do início do mapa
	 * @param mapStartY Coordenada Y do início do mapa
	 * @param spacing Espacamento pretendido
	 * @param scene A cena onde se altera os desenhos
	 * @return verdadeiro se foi possível remover a caixa, falso se não
	 */
	public boolean remove_box_right(float player_pos_x, float player_pos_y, int mapStartX, int mapStartY, int spacing, Scene scene) {
		for(int i = 0; i < map.getHeight(); i++)
			for(int a = 0; a < map.getWidth(); a++){
				int pos_x = mapStartX + a * spacing;
				int pos_y = mapStartY - i * spacing;
				if(pos_x - 100 < player_pos_x && pos_x > player_pos_x)
					if(pos_y < player_pos_y && pos_y + 100 > player_pos_y){
						if(!map.isSomethingUp(a,i))
							if(map.getMap()[a][i].getKind() != "blank"){
								if(physicsWorld != null){
									scene.detachChild(map.getMap()[a][i].getSprite());
									physicsWorld.destroyBody(map.getMap()[a][i].getBody());
								}
								if(map.getMap()[a][i].getSprite() != null){
									map.getMap()[a][i].getSprite().detachSelf();
									map.getMap()[a][i].getSprite().dispose();
								}
								map.getMap()[a][i].setBlank();
								return true;
							}
					}
			}
		return false;
	}
	
	/**
	 * Função que "atira" a caixa para o lado esquerdo, pousando-a assim no chão
	 * @param player_pos_x Coordenada X do Jogador
	 * @param player_pos_y Coordenada Y do Jogador
	 * @param mapStartX Coordenada X do início do mapa
	 * @param mapStartY Coordenada Y do início do mapa
	 * @param spacing Espacamento pretendido entre os objetos
	 * @param scene Cena que é alterada
	 * @param box_layer Textura da caixa
	 * @param vertexBufferObjectManager necessário para criar sprites
	 * @param vis visibilidade do objeto
	 * @return verdadeiro se for possível "atirar a caixa", falso se não
	 */
	public boolean leave_box_left(float player_pos_x, float player_pos_y, int mapStartX,
			int mapStartY, int spacing, Scene scene, ITextureRegion box_layer, VertexBufferObjectManager vertexBufferObjectManager, boolean vis) {
		for(int i = 0; i < map.getHeight(); i++)
			for(int a = 0; a < map.getWidth(); a++){
				int pos_x = mapStartX + a * spacing;
				int pos_y = mapStartY - i * spacing;
				if(pos_x - 100 < player_pos_x && pos_x > player_pos_x)
					if(pos_y < player_pos_y && pos_y + 100 > player_pos_y){
						int box_to_change = a - 1;
						int space = 100;
						if(player_pos_x - (pos_x - 100) <= 60){
							box_to_change = a - 2;
							space = 200;
						}
							if(map.getMap()[a - 1][i].getKind() != "rock" && map.getMap()[a - 1][i].getKind() != "box" )	
								if(map.getMap()[box_to_change][i].getKind() == "blank"){
									int y = map.getLowerY(box_to_change, i);
									int new_pos_y = mapStartY - y * spacing;
									System.out.println("Box to change " + box_to_change);
									map.getMap()[box_to_change][y].setKind("box");
									if(box_layer != null){
										map.getMap()[box_to_change][y].setSprite(new Sprite(pos_x-space, new_pos_y ,box_layer, vertexBufferObjectManager));
										scene.attachChild(map.getMap()[box_to_change][y].getSprite());
									}
									//Placing physics Body
									if(physicsWorld != null){
										IShape box = new Rectangle(pos_x - space, new_pos_y, 100, 100, vertexBufferObjectManager);
										box.setVisible(vis);
										FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(10, 0, 0f);
										Body b = PhysicsFactory.createBoxBody(physicsWorld, (IAreaShape) box, BodyType.StaticBody, wallFixtureDef);
										b.getFixtureList().get(0).setUserData("box body");
										map.getMap()[box_to_change][y].setBody(b);
									}
									return true;
								}
					}
			}
		return false;
	}
	/**
	 * Função que "atira" a caixa para o lado direito, pousando-a assim no chão
	 * @param player_pos_x Coordenada X do Jogador
	 * @param player_pos_y Coordenada Y do Jogador
	 * @param mapStartX Coordenada X do início do mapa
	 * @param mapStartY Coordenada Y do início do mapa
	 * @param spacing Espacamento pretendido entre os objetos
	 * @param scene Cena que é alterada
	 * @param box_layer Textura da caixa
	 * @param vertexBufferObjectManager necessário para criar sprites
	 * @param vis visibilidade do objeto
	 * @return verdadeiro se for possível "atirar a caixa", falso se não
	 */
	public boolean leave_box_right(float player_pos_x, float player_pos_y, int mapStartX,
			int mapStartY, int spacing, Scene scene, ITextureRegion box_layer, VertexBufferObjectManager vertexBufferObjectManager, boolean vis) {
		for(int i = 0; i < map.getHeight(); i++)
			for(int a = 0; a < map.getWidth(); a++){
				int pos_x = mapStartX + a * spacing;
				int pos_y = mapStartY - i * spacing;
				if(pos_x - 100 < player_pos_x && pos_x > player_pos_x)
					if(pos_y < player_pos_y && pos_y + 100 > player_pos_y){
						if(map.getMap()[a][i].getKind() != "rock" && map.getMap()[a][i].getKind() != "box" )
						if(map.getMap()[a + 1][i].getKind() == "blank"){
							//Placing sprites
							int y = map.getLowerY(a + 1, i);
							int new_pos_y = mapStartY - y * spacing;
							map.getMap()[a + 1][y].setKind("box");
							if(box_layer != null){
								map.getMap()[a + 1][y].setSprite(new Sprite(pos_x + 100, new_pos_y ,box_layer, vertexBufferObjectManager));
								scene.attachChild(map.getMap()[a + 1][y].getSprite());
							}
							//Placing physics Body
							if(physicsWorld != null){
								IShape box = new Rectangle(pos_x + 100, new_pos_y, 100, 100, vertexBufferObjectManager);
								box.setVisible(vis);
								FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 1f);
								Body b = PhysicsFactory.createBoxBody(physicsWorld, (IAreaShape) box, BodyType.StaticBody, wallFixtureDef);
								b.getFixtureList().get(0).setUserData("box body");
								map.getMap()[a + 1][y].setBody(b);
							}
							return true;
						}
					}
			}
		return false;
	}
	
	/**
	 * Retorna se o jogo está ganho ou não
	 * @return win verdadeiro se for ganho, falso se não
	 */
	public boolean getWin(){
		return win;
	}
	
	/**
	 * Altera a vitória do jogo
	 * @param a (true ou false)
	 */
	public void setWin(boolean a){
		win = a;
	}
	
	/**
	 * Retorna se o jogador está a carregar uma caixa, ou não
	 * @return carringBox verdadeiro se estiver a carregar, falso se não
	 */
	public boolean getCarringBox(){
		return carringBox;
	}
	
	/**
	 * Altera o carregamento da caixa
	 * @param a (true ou false)
	 */
	public void setCarringBox(boolean a){
		carringBox = a;
	}

	/**
	 * Altera o mapa do jogo
	 * @param map2
	 */
	public void setMap(Map map2) {
		this.map = map2;
	}
	
	/**
	 * Altera a física do jogo
	 * @param physicsWorld2
	 */
	public void setPhysics(PhysicsWorld physicsWorld2) {
		this.physicsWorld = physicsWorld2;
	}
}
