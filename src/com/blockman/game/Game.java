package com.blockman.game;

import android.content.Intent;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;















import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.EntityModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.blockman.data.Map;
import com.blockman.data.Position;


public class Game extends SimpleBaseGameActivity {
    //COSNTANTS-----------------
    private static final int CAMERA_WIDTH = 1270;
    private static final int CAMERA_HEIGHT = 800;

    private static final String TAG = "Block Man";

    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String LEFT_W_COLLISION = "left collision";
    private static final String RIGHT_W_COLLISION = "right collision";
    private static final String STOP_RIGHT = "stop right";
    private static final String STOP_LEFT = "stop left";
    private static final String STOP = "STOP";

    private static final int MAP_START_X = 300;
    private static final int MAP_START_Y = 7 * CAMERA_HEIGHT / 12 - 100;
    private static final int SPACING = 100;

    final float PLAYER_START_X = 1500;
    final float PLAYER_START_Y = 7 * CAMERA_HEIGHT / 12 - 60;

    private String state = "not jumping";
    //---------------------------
    private VertexBufferObjectManager vertexBufferObjectManager;
    private Sprite back;
    //--------------------------
    //Camera--------------------
    private BoundCamera myChaseCamera;
    private HUD mHUD;
    //--------------------------
    //Physics-------------------
    private PhysicsWorld physicsWorld;
    
    private PhysicsHandler playerPhysics;
    
    //--------------------------
    //Add Scene-----------------
    private Scene scene;
    //--------------------------
    //Buttons-------------------
    private BitmapTextureAtlas go_back_bmp;
    private ITextureRegion go_back_texture;
    private ButtonSprite go_back;
    //--------------------------
    String direction = "";
    //--------------------------
    //Sprites-------------------
    private ITextureRegion myLayerMid;
    private ITextureRegion myLayerFront;
    private BitmapTextureAtlas myBackgroundTexture;

    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mPlayerTextureRegion;

    private AnimatedSprite player;
    //-----------------------------
    //Map Sprites-----------------
    Map map;
    private Sprite rock;
    private ITextureRegion rock_layer;
    private BitmapTextureAtlas rock_bmp;
    Sprite box;
    //----------------------------
    //Controls--------------------
    private long tap_time;
    //---------------------------
    //Text------------------------
    private Font title_font;
    private Text title;


    @Override
    public EngineOptions onCreateEngineOptions() {
        this.myChaseCamera = new BoundCamera(CAMERA_WIDTH, CAMERA_HEIGHT , CAMERA_WIDTH ,CAMERA_HEIGHT);
        //myChaseCamera.setCenter(CAMERA_WIDTH/3, 7 * CAMERA_HEIGHT / 9);
        myChaseCamera.setBoundsEnabled(true);
        myChaseCamera.setBounds(0 , 0, 3000 , 1708);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), myChaseCamera);
    }

    @Override
    protected void onCreateResources() {
        this.myBackgroundTexture = new BitmapTextureAtlas(this.getTextureManager(), 3000, 1708);
        this.myLayerFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.myBackgroundTexture, this, "ingame_back.jpg", 0, 0);
        this.myLayerMid = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.myBackgroundTexture, this, "clouds.png", 0, 188);
        this.myBackgroundTexture.load();

        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 192, 256, TextureOptions.BILINEAR);
        this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "man.png", 0, 0, 6, 4);
        this.mBitmapTextureAtlas.load();

        this.go_back_bmp = new BitmapTextureAtlas(this.getTextureManager(), 144, 144, TextureOptions.BILINEAR);
        this.go_back_texture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.go_back_bmp, this, "go_back.png", 0, 0);
        this.go_back_bmp.load();

        title_font = FontFactory.createFromAsset(mEngine.getFontManager(),
                mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR,
                this.getAssets(), "fonts/3Dumb.ttf", 90f, true,
                Color.BLACK_ABGR_PACKED_INT);
        title_font.load();

        this.rock_bmp = new BitmapTextureAtlas(this.getTextureManager(), 100, 100, TextureOptions.BILINEAR);
        this.rock_layer = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.rock_bmp, this, "img/rock.png", 0, 0);
        this.rock_bmp.load();



        this.title = new Text(1500 - 150, CAMERA_HEIGHT / 2 - 300, title_font, "LEVEL 1",getVertexBufferObjectManager());
    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        scene = new Scene();
        final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
        vertexBufferObjectManager = this.getVertexBufferObjectManager();
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0f, new Sprite(0, CAMERA_HEIGHT - this.myLayerFront.getHeight(), this.myLayerFront, vertexBufferObjectManager)));
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0f, new Sprite(0, 0, this.myLayerMid, vertexBufferObjectManager)));
        //scene.setBackground(autoParallaxBackground);

        back = new Sprite(0, CAMERA_HEIGHT - this.myLayerFront.getHeight(), this.myLayerFront, vertexBufferObjectManager);
        scene.attachChild(back);

        //---------------------
        //add physics
        initPhysics();
        //------------------



        player = new AnimatedSprite(PLAYER_START_X, PLAYER_START_Y, this.mPlayerTextureRegion, vertexBufferObjectManager);
        player.setScaleCenterY(this.mPlayerTextureRegion.getHeight());
        player.setScale(2);
        player_stop();

        scene.attachChild(player);
        generateMap();
        scene.attachChild(title);

        Log.d(TAG, "Player height :" + player.getHeight());
        Log.d(TAG, "Player width :" + player.getWidth());

        FadeOutModifier mModifier = new FadeOutModifier(5.0f);
        title.registerEntityModifier(mModifier);

        scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
            @Override
            public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent) {
                if (pSceneTouchEvent.isActionDown()) {
                    tap_time = System.currentTimeMillis();
                    if(pSceneTouchEvent.getX() > player.getX()) {
                        if(direction != RIGHT) {
                            if(direction != RIGHT_W_COLLISION) {
                                player_walk_right();
                                direction = RIGHT;
                            }
                        }
                    }else{
                        if(direction != LEFT) {
                            if(direction != LEFT_W_COLLISION) {
                                player_walk_left();
                                direction = LEFT;
                            }
                        }
                    }
                    return true;
                }

                if (pSceneTouchEvent.isActionMove()) {
                    if(pSceneTouchEvent.getX() > player.getX()) {
                        if(direction != RIGHT) {
                            if(direction != RIGHT_W_COLLISION) {
                            player_walk_right();
                            direction = RIGHT;
                            }
                        }
                    }else{
                        if(direction != LEFT) {
                            if(direction != LEFT_W_COLLISION) {
                                player_walk_left();
                                direction = LEFT;
                            }
                        }
                    }
                    return true;
                }

                if (pSceneTouchEvent.isActionUp()) {
                    //JUMP-------------------------
                    if(System.currentTimeMillis() - tap_time < 100){
                      Log.d(TAG, "jumping");
                    }
                    //------------------------------
                    if(direction == "right"){
                            player_stop_right();
                            direction = STOP_RIGHT;
                    }else if(direction == "left"){
                            player_stop_left();
                            direction = STOP_LEFT;
                    }else{
                            direction = STOP;
                           
                     }
                    return true;
                }
                return false;
            }


        });

        myChaseCamera.setChaseEntity(player);

        //Botao em hud
        HUD hud = new HUD();
        go_back =  new ButtonSprite(25, 25 , go_back_texture,  vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
                                         float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    go_back.registerEntityModifier(click);
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }};

        hud.attachChild(go_back);
        hud.registerTouchArea(go_back);
        go_back.setScale((float)0.8);
        myChaseCamera.setHUD(hud);

        //Generate map


        return scene;
    }

    private void generateMap() {
        map = new Map();
        map.generateMap();
        for(int i = 0; i < map.getHeight(); i++){
            for(int a = 0; a < map.getWidth(); a++){
                if(map.getMap()[a][i].getKind() == "rock"){
                    map.getMap()[a][i].setSprite(new Sprite(MAP_START_X + SPACING * a, MAP_START_Y - SPACING * i,rock_layer, vertexBufferObjectManager));
                    map.pushPos(new Position(a,i));
                    scene.attachChild(map.getMap()[a][i].getSprite());
                }
            }
        }
    }


    private void player_stop() {
        player.animate(new long[]{100, 100}, 0, 1, true);
    }

    private void player_stop_left() {
        player.animate(new long[]{100, 100}, 12, 13, true);
    }

    private void player_walk_left() {
        player.animate(new long[]{200, 200, 200, 200}, 14, 17, true);
    }

    private void player_walk_right() {
        player.animate(new long[]{200, 200, 200, 200}, 20, 23, true);
    }

    private void player_stop_right() {
        player.animate(new long[]{100, 100}, 18, 19, true);
    }

    private String getCollision(){

        return "";
    }

    
    private void initPhysics()
    {
        physicsWorld = new PhysicsWorld(new Vector2(0, -9.81f), false);
        playerPhysics = new PhysicsHandler(player);
        
        final IShape bottom = new Rectangle(0, PLAYER_START_Y + 60, 3000, 20, getVertexBufferObjectManager());
        bottom.setVisible(true);
        
        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 1f);
        PhysicsFactory.createBoxBody(physicsWorld, (IAreaShape) bottom, BodyType.StaticBody, wallFixtureDef);
        scene.attachChild(bottom);
        
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1, 1);
        
        
        scene.registerUpdateHandler(physicsWorld);
        
    }
   

 




  


    final SequenceEntityModifier click = new SequenceEntityModifier(new FadeOutModifier(0.10f), new FadeInModifier(0.10f){
        @Override
        protected void onModifierStarted(IEntity pItem)
        {
            super.onModifierStarted(pItem);
            // Your action after starting modifier
        }

        @Override
        protected void onModifierFinished(IEntity pItem)
        {
            super.onModifierFinished(pItem);
            Intent back = new Intent(getBaseContext(), MainMenu.class);
            back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(back);
            direction = "finish";
            scene.clearChildScene();
            finish();
        }
    });


}