/**
 * Created by jared on 2/9/16.
 */

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.opengl.*;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;

import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.nio.ByteBuffer;

import javax.sound.sampled.*;

public class JavaFramework {

	static GL2 gl;
	static String theHealth;

	public static void main(String[] args) {


		GLProfile gl2Profile;

		try {
			// Make sure we have a recent version of OpenGL
			gl2Profile = GLProfile.get(GLProfile.GL2);
		}
		catch (GLException ex) {
			System.out.println("OpenGL max supported version is too low.");
			System.exit(1);
			return;
		}

		// Create the window and OpenGL context.
		GLWindow window = GLWindow.create(new GLCapabilities(gl2Profile));
		window.setSize(VarGeneral.camWidth, VarGeneral.camHeight);
		window.setTitle("Java Framework");
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if (keyEvent.isAutoRepeat()) {
					return;
				}
				VarGeneral.kbState[keyEvent.getKeyCode()] = true;
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
				if (keyEvent.isAutoRepeat()) {
					return;
				}
				VarGeneral.kbState[keyEvent.getKeyCode()] = false;
			}
		});
		
		Sound zombieSound = Sound.loadFromFile("zombieSound.wav");
		Clip bgClip = zombieSound.playLooping();

		// Setup OpenGL state.
		window.getContext().makeCurrent();
		gl = window.getGL().getGL2();
		//multiplying by 2 solve my screen resolution problem
		gl.glViewport(0, 0, VarGeneral.camWidth*2, VarGeneral.camHeight*2); 
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glOrtho(0, VarGeneral.camWidth, VarGeneral.camHeight, 0, 0, 100);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		new VarGeneral();
		new VarBackground1();
		new VarBackground2();
		new VarHero();
		new VarEnemy1();
		new VarEnemy2();
		new VarBoss1();
		new VarBoss2();
		new VarBlood();

		VarEnemy1 enemy11 = new VarEnemy1();
		VarEnemy1 enemy12 = new VarEnemy1();
		VarEnemy1 enemy13 = new VarEnemy1();
		enemy11.setEnemyPosition(VarGeneral.camWidth-53/2);
		enemy12.setEnemyPosition(VarGeneral.camWidth-53/2+50);
		enemy13.setEnemyPosition(VarGeneral.camWidth-53/2+100);
		VarBlood blood11 = new VarBlood();
		VarBlood blood12 = new VarBlood();
		VarBlood blood13 = new VarBlood();

		VarEnemy2 enemy21 = new VarEnemy2();
		VarEnemy2 enemy22 = new VarEnemy2();
		VarEnemy2 enemy23 = new VarEnemy2();
		enemy21.setEnemyPosition(VarGeneral.camWidth-53/2);
		enemy22.setEnemyPosition(VarGeneral.camWidth-53/2+50);
		enemy23.setEnemyPosition(VarGeneral.camWidth-53/2+100);
		VarBlood blood21 = new VarBlood();
		VarBlood blood22 = new VarBlood();
		VarBlood blood23 = new VarBlood();

		VarBlood bloodBoss = new VarBlood();

		// Timing variables
		long lastFrameNS;
		long currentFrameNS = System.nanoTime();
		int currentFrameMS = (int) currentFrameNS / 1000000;
		// Physics runs at 100fps, or 10ms / physics frame
		int physicsDeltaMS = 10;
		int lastPhysicsFrameMS;


		// Set each animation with corresponding frames
		FrameDef[] frameWalk = {VarHero.frameWalk1,VarHero.frameWalk2,VarHero.frameWalk3,
				VarHero.frameWalk4,VarHero.frameWalk5,VarHero.frameWalk6,VarHero.frameWalk7,
				VarHero.frameWalk8,VarHero.frameWalk9,VarHero.frameWalk10};
		FrameDef[] frameStanding = {VarHero.frameWalk1};
		FrameDef[] frameFiring = {VarHero.frameFire1,VarHero.frameFire2,VarHero.frameFire3,
				VarHero.frameFire4,VarHero.frameFire5};
		FrameDef[] frameDead = {VarHero.frameDead1,VarHero.frameDead2,VarHero.frameDead3,
				VarHero.frameDead4,VarHero.frameDead5,VarHero.frameDead6};
		FrameDef[] frameEnemy1 = {VarEnemy1.frameEnemy11,VarEnemy1.frameEnemy12, 
				VarEnemy1.frameEnemy13, VarEnemy1.frameEnemy14};
		FrameDef[] frameEnemy2 = {VarEnemy2.frameEnemy21,VarEnemy2.frameEnemy22,VarEnemy2.frameEnemy23, 
				VarEnemy2.frameEnemy24, VarEnemy2.frameEnemy25,VarEnemy2.frameEnemy26,VarEnemy2.frameEnemy27,
				VarEnemy2.frameEnemy28,VarEnemy2.frameEnemy29};
		FrameDef[] frameBoss1Wonder = {VarBoss1.frameBoss1Wonder1,VarBoss1.frameBoss1Wonder2,VarBoss1.frameBoss1Wonder2,
				VarBoss1.frameBoss1Wonder2,VarBoss1.frameBoss1Wonder2,VarBoss1.frameBoss1Wonder3,
				VarBoss1.frameBoss1Wonder2,VarBoss1.frameBoss1Wonder2};
		FrameDef[] frameBoss1Hit = {VarBoss1.frameBoss1Hit1,VarBoss1.frameBoss1Hit2,VarBoss1.frameBoss1Hit3,
				VarBoss1.frameBoss1Hit4,VarBoss1.frameBoss1Hit5,VarBoss1.frameBoss1Hit6,VarBoss1.frameBoss1Hit7,
				VarBoss1.frameBoss1Hit8};
		FrameDef[] frameBoss1Dead = {VarBoss1.frameBoss1Dead1,VarBoss1.frameBoss1Dead2,VarBoss1.frameBoss1Dead3};
		FrameDef[] frameFlame = {VarHero.frameFlame1, VarHero.frameFlame2, VarHero.frameFlame3, VarHero.frameFlame4,
				VarHero.frameFlame5,VarHero.frameFlame6,VarHero.frameFlame7,VarHero.frameFlame8,VarHero.frameFlame9,
				VarHero.frameFlame10,VarHero.frameFlame11,VarHero.frameFlame12};
		FrameDef[] frameBlood = {VarBlood.frameBlood1,VarBlood.frameBlood2,VarBlood.frameBlood3,VarBlood.frameBlood4,
				VarBlood.frameBlood5,VarBlood.frameBlood6,VarBlood.frameBlood7,VarBlood.frameBlood8,
				VarBlood.frameBlood9,VarBlood.frameBlood10,VarBlood.frameBlood11,VarBlood.frameBlood12};

		// Set animation definition
		VarHero.animWalkLeft = new AnimationDef("WalkLeftAnim", frameWalk);
		VarHero.animDataWalkLeft = new AnimationData(VarHero.animWalkLeft, 0, currentFrameNS);
		VarHero.animWalkRight = new AnimationDef("WalkLeftAnim", frameWalk);
		VarHero.animDataWalkRight = new AnimationData(VarHero.animWalkRight, 0, currentFrameNS);
		VarHero.animStanding = new AnimationDef("Standing", frameStanding);
		VarHero.animDataStanding = new AnimationData(VarHero.animStanding, 0, currentFrameNS);
		VarHero.animFire = new AnimationDef("Fire", frameFiring);
		VarHero.animDataFire = new AnimationData(VarHero.animFire, 0, currentFrameNS);
		VarHero.animDead = new AnimationDef("Dead", frameDead);
		VarHero.animDataDead = new AnimationData(VarHero.animDead, 0, currentFrameNS);

		VarHero.animFlame = new AnimationDef("LeftAnim", frameFlame);
		VarHero.animDataFlame = new AnimationData(VarHero.animFlame, 0, currentFrameNS);

		VarBlood.animBlood = new AnimationDef("BloodAnim", frameBlood);
		VarBlood.animDataBlood = new AnimationData(VarBlood.animBlood, 0, currentFrameNS);

		VarBoss1.animBoss1Wonder = new AnimationDef("Standing", frameBoss1Wonder);
		VarBoss1.animDataBoss1Wonder = new AnimationData(VarBoss1.animBoss1Wonder, 0, currentFrameNS);
		VarBoss1.animBoss1Hit = new AnimationDef("Fire", frameBoss1Hit);
		VarBoss1.animDataBoss1Hit = new AnimationData(VarBoss1.animBoss1Hit, 0, currentFrameNS);
		VarBoss1.animBoss1Dead = new AnimationDef("Boss1Dead", frameBoss1Dead);
		VarBoss1.animDataBoss1Dead = new AnimationData(VarBoss1.animBoss1Dead, 0, currentFrameNS);

		VarEnemy1.animEnemy1 = new AnimationDef("Enemy1Anim", frameEnemy1);
		VarEnemy1.animDataEnemy1 = new AnimationData(VarEnemy1.animEnemy1, 0, currentFrameNS);

		VarEnemy2.animEnemy2 = new AnimationDef("Enemy2Anim", frameEnemy2);
		VarEnemy2.animDataEnemy2 = new AnimationData(VarEnemy2.animEnemy2, 0, currentFrameNS);

		/**********************************************************
		 *Set different backgrounds
		 **********************************************************/

		// Set sky background
		Tile tileBackground[] = new Tile[1600];
		for(int i=0; i<800; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.sky);
		}

		// Set floor background
		for(int i=800; i<900; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube1);
			tileBackground[i].setColl(true);
			tileBackground[++i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube2);
			tileBackground[i].setColl(true);
		}
		for(int i=900; i<1000; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube2);
			tileBackground[++i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube1);
		}
		for(int i=1000; i<1100; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube1);
			tileBackground[++i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube3);
		}
		for(int i=1100; i<1200; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube3);
			tileBackground[++i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube4);
		}
		for(int i=1200; i<1300; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube4);
			tileBackground[++i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube5);
		}
		for(int i=1300; i<1600; i++){
			tileBackground[i] = new Tile();
			tileBackground[i].setImage(VarBackground1.cube5);
		}
		VarBackground1.background.setTiles(tileBackground);

		// Set cloud background
		int tileCloud[] = new int[2];
		tileCloud[0] = VarBackground1.cloud1;
		tileCloud[1] = VarBackground1.cloud2;

		//		// Set tree background
		//		int tileTree[] = new int[4];
		//		tileTree[0] = VarBackground1.tree1;
		//		tileTree[1] = VarBackground1.tree2;
		//		tileTree[2] = VarBackground1.tree3;
		//		tileTree[3] = VarBackground1.tree4;

		// Set moon background
		int tileMoon[] = new int[4];
		tileMoon[0] = VarBackground1.moon1;
		tileMoon[1] = VarBackground1.moon2;
		tileMoon[2] = VarBackground1.moon3;
		tileMoon[3] = VarBackground1.moon4;

		// Set moon background
		int tileBuild[] = new int[24];
		tileBuild[0] = VarBackground1.build1;
		tileBuild[1] = VarBackground1.build2;
		tileBuild[2] = VarBackground1.build3;
		tileBuild[3] = VarBackground1.build4;
		tileBuild[4] = VarBackground1.build5;
		tileBuild[5] = VarBackground1.build6;
		tileBuild[6] = VarBackground1.build7;
		tileBuild[7] = VarBackground1.build8;
		tileBuild[8] = VarBackground1.build9;
		tileBuild[9] = VarBackground1.build10;
		tileBuild[10] = VarBackground1.build11;
		tileBuild[11] = VarBackground1.build12;
		tileBuild[12] = VarBackground1.build13;
		tileBuild[13] = VarBackground1.build14;
		tileBuild[14] = VarBackground1.build15;
		tileBuild[15] = VarBackground1.build16;
		tileBuild[16] = VarBackground1.build17;
		tileBuild[17] = VarBackground1.build18;
		tileBuild[18] = VarBackground1.build19;
		tileBuild[19] = VarBackground1.build20;
		tileBuild[20] = VarBackground1.build21;
		tileBuild[21] = VarBackground1.build22;
		tileBuild[22] = VarBackground1.build23;
		tileBuild[23] = VarBackground1.build24;

		//**********************************************************

		// The game loop
		while (!VarGeneral.shouldExit) {
			System.arraycopy(VarGeneral.kbState, 0, VarGeneral.kbPrevState, 0, VarGeneral.kbState.length);
			lastFrameNS = currentFrameNS;

			// Actually, this runs the entire OS message pump.
			window.display();
			if (!window.isVisible()) {
				VarGeneral.shouldExit = true;
				break;
			}
			
			bgClip.start();

			lastPhysicsFrameMS = (int) System.nanoTime() / 1000000;
			currentFrameNS = System.nanoTime();
			currentFrameMS = (int) currentFrameNS / 1000000;
			float deltaTimeMS = (currentFrameNS - lastFrameNS) / 1000000;
			VarHero.animDataStanding.update(deltaTimeMS);
			VarHero.animDataWalkLeft.update(deltaTimeMS);
			VarHero.animDataWalkRight.update(deltaTimeMS);
			VarHero.animDataDead.update(deltaTimeMS);
			VarHero.animDataFire.update(deltaTimeMS);
			VarEnemy1.animDataEnemy1.update(deltaTimeMS);
			VarEnemy2.animDataEnemy2.update(deltaTimeMS);
			VarHero.animDataFlame.update(deltaTimeMS);
			VarBoss1.animDataBoss1Wonder.update(deltaTimeMS);
			VarBoss1.animDataBoss1Dead.update(deltaTimeMS);
			VarBoss1.animDataBoss1Hit.update(deltaTimeMS);
			VarBlood.animDataBlood.update(deltaTimeMS);
			VarBoss1.animDataBoss1Dead.update(deltaTimeMS);
			VarBoss1.animDataBoss1Wonder.update(deltaTimeMS);
			VarBoss1.animDataBoss1Hit.update(deltaTimeMS);

			// Check keyboard input for player
			// Game logic.
			if (VarGeneral.kbState[KeyEvent.VK_ESCAPE]) {
				VarGeneral.shouldExit = true;
			}

			if (VarGeneral.kbState[KeyEvent.VK_LEFT]) {
				VarHero.heroState = Status.WALKING_LEFT;
				VarHero.spritePrevPos[0] = VarHero.spritePos[0];
				VarHero.spritePos[0] -= 2;
			}

			if (VarGeneral.kbState[KeyEvent.VK_RIGHT]) {
				VarHero.heroState = Status.WALKING_RIGHT;
				VarHero.spritePrevPos[0] = VarHero.spritePos[0];
				VarHero.spritePos[0] += 2;
			}

			if (!VarGeneral.kbState[KeyEvent.VK_LEFT] && !VarGeneral.kbState[KeyEvent.VK_RIGHT]) {
				VarHero.heroState = Status.STANDING;
			}

			if (VarGeneral.kbState[KeyEvent.VK_SPACE] && (VarHero.heroState == Status.WALKING_RIGHT 
					|| VarHero.heroState == Status.STANDING) && VarHero.flameState == Status.DISAPPEAR) {
				VarHero.heroState = Status.FIRING;
				VarHero.flameState = Status.APPEAR_RIGHT;
				VarHero.flameSpritePos[0] = VarHero.spritePos[0]+10;
				VarHero.flameSpritePos[1] = VarHero.spritePos[1]+20;
			}

			if (VarGeneral.kbState[KeyEvent.VK_SPACE] && VarHero.heroState == Status.WALKING_LEFT
					&& VarHero.flameState == Status.DISAPPEAR) {
				VarHero.heroState = Status.FIRING;
				VarHero.flameState = Status.APPEAR_RIGHT;
				VarHero.flameSpritePos[0] = VarHero.spritePos[0]+10;
				VarHero.flameSpritePos[1] = VarHero.spritePos[1]+20;
			}

			// Camera Position
			if (VarGeneral.kbState[KeyEvent.VK_A] && VarGeneral.camPos.getX()>0) {
				VarGeneral.camPos.setX(VarGeneral.camPos.getX()-1);
				VarGeneral.camPosChar.setX(VarGeneral.camPosChar.getX()-1);
			}

			if (VarGeneral.kbState[KeyEvent.VK_D] && VarGeneral.camPos.getX()<VarGeneral.endWorld) {
				VarGeneral.camPos.setX(VarGeneral.camPos.getX()+1);
				VarGeneral.camPosChar.setX(VarGeneral.camPosChar.getX()+1);
			}

			if (VarGeneral.kbState[KeyEvent.VK_W] && VarGeneral.camPos.getY()>0) {
				VarGeneral.camPos.setY(VarGeneral.camPos.getY()-1);
				VarGeneral.camPosChar.setY(VarGeneral.camPosChar.getY()-1);
			}

			if (VarGeneral.kbState[KeyEvent.VK_S] && VarGeneral.camPos.getY()<VarGeneral.originalCamPosY ) {
				VarGeneral.camPos.setY(VarGeneral.camPos.getY()+1);
				VarGeneral.camPosChar.setY(VarGeneral.camPosChar.getY()+1);
			}

			gl.glClearColor(0, 0, 0, 1);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

			do {
//				theHealth = String.valueOf(VarHero.health);
//				Graphics.drawString("anything", 100, 100);
//				Font awfont = new Font("Verdana", Font.BOLD, 20);
//				TrueTypeFont font = new TrueTypeFont(awfont, false);
//				font.drawString((int)100, (int)50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
//				Graphics.setFont(font);

				//				glFontBegin(font);
				//				glScalef(10.0, 10.0, 10.0);
				//				glFontTextOut("Testing", 5, 5, 0);
				//				glFontEnd();

				//				((Graphics) gl).drawString("Hello World!",200,200);

				// 1. Physics movement
				if(VarHero.flameState != Status.DISAPPEAR && VarBoss1.bossState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, VarBoss1.spriteBossPos[0], 
							VarBoss1.spriteBossPos[1], VarBoss1.spriteBossSize[0], VarBoss1.spriteBossSize[0])){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						VarBoss1.bossHealth = VarBoss1.bossHealth-1;
						if(VarBoss1.bossHealth<=0 && VarBoss1.bossState != Status.OVER){
							VarBoss1.bossState = (Status.DEAD);
							bloodBoss.setBloodState(Status.APPEAR);
							bloodBoss.setbloodXPosition(VarBoss1.spriteBossPos[0]);
							bloodBoss.setbloodYPosition(VarBoss1.spriteBossPos[1]);
//							VarHero.gameIsWon = Status.YES;
						}
						if(VarBoss1.bossHealth<=-100 && VarBoss1.bossState != Status.OVER){
							VarBoss1.bossState = (Status.OVER);
							VarHero.gameIsWon = Status.YES;
						}
					}
				}
				

				if(VarHero.flameState != Status.DISAPPEAR && enemy11.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy11.getEnemyXPosition(), 
							enemy11.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy11.setHealthEnemy(enemy11.getHealthEnemy()-1);
						if(enemy11.getHealthEnemy()<=0){
							enemy11.setEnemyState(Status.DISAPPEAR);
							blood11.setBloodState(Status.APPEAR);
							blood11.setbloodXPosition(enemy11.getEnemyXPosition());
							blood11.setbloodYPosition(enemy11.getEnemyYPosition());
							VarEnemy1.nextEnemy1SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				if(VarHero.flameState != Status.DISAPPEAR && enemy12.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy12.getEnemyXPosition(), 
							enemy12.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy12.setHealthEnemy(enemy12.getHealthEnemy()-1);
						if(enemy12.getHealthEnemy()<=0){
							enemy12.setEnemyState(Status.DISAPPEAR);
							blood12.setBloodState(Status.APPEAR);
							blood12.setbloodXPosition(enemy12.getEnemyXPosition());
							blood12.setbloodYPosition(enemy12.getEnemyYPosition());
							VarEnemy1.nextEnemy1SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				if(VarHero.flameState != Status.DISAPPEAR && enemy13.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy13.getEnemyXPosition(), 
							enemy13.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy13.setHealthEnemy(enemy13.getHealthEnemy()-1);
						if(enemy13.getHealthEnemy()<=0){
							enemy13.setEnemyState(Status.DISAPPEAR);
							blood13.setBloodState(Status.APPEAR);
							blood13.setbloodXPosition(enemy13.getEnemyXPosition());
							blood13.setbloodYPosition(enemy13.getEnemyYPosition());
							VarEnemy1.nextEnemy1SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				if(VarHero.flameState != Status.DISAPPEAR && enemy21.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy21.getEnemyXPosition(), 
							enemy21.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy21.setHealthEnemy(enemy21.getHealthEnemy()-1);
						if(enemy21.getHealthEnemy()<=0){
							enemy21.setEnemyState(Status.DISAPPEAR);
							blood21.setBloodState(Status.APPEAR);
							blood21.setbloodXPosition(enemy21.getEnemyXPosition());
							blood21.setbloodYPosition(enemy21.getEnemyYPosition());
							VarEnemy2.nextEnemy2SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				if(VarHero.flameState != Status.DISAPPEAR && enemy22.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy22.getEnemyXPosition(), 
							enemy22.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy22.setHealthEnemy(enemy22.getHealthEnemy()-1);
						if(enemy22.getHealthEnemy()<=0){
							enemy22.setEnemyState(Status.DISAPPEAR);
							blood22.setBloodState(Status.APPEAR);
							blood22.setbloodXPosition(enemy22.getEnemyXPosition());
							blood22.setbloodYPosition(enemy22.getEnemyYPosition());
							VarEnemy2.nextEnemy2SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				if(VarHero.flameState != Status.DISAPPEAR && enemy23.getEnemyState() != Status.DISAPPEAR){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.flameSpritePos[0], VarHero.flameSpritePos[1], 
							VarHero.flameSize, VarHero.flameSize, enemy23.getEnemyXPosition(), 
							enemy23.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.flameState = Status.DISAPPEAR;
						enemy23.setHealthEnemy(enemy23.getHealthEnemy()-1);
						if(enemy23.getHealthEnemy()<=0){
							enemy23.setEnemyState(Status.DISAPPEAR);
							blood23.setBloodState(Status.APPEAR);
							blood23.setbloodXPosition(enemy23.getEnemyXPosition());
							blood23.setbloodYPosition(enemy23.getEnemyYPosition());
							VarEnemy2.nextEnemy2SpritePos = VarGeneral.camPos.getX() + VarGeneral.camWidth/10;
						}
					}
				}

				/****************************************************************************************************/
				if(VarHero.heroState != Status.DEAD && VarBoss1.bossState != Status.OVER
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[1], VarBoss1.spriteBossPos[0], 
							VarBoss1.spriteBossPos[1], VarBoss1.spriteBossSize[0], VarBoss1.spriteBossSize[1])){
						VarBoss1.bossState = Status.HIT;
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-10 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}
				
				if(VarHero.heroState != Status.DEAD && VarBoss1.bossState != Status.OVER
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(!GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[1], VarBoss1.spriteBossPos[0], 
							VarBoss1.spriteBossPos[1], VarBoss1.spriteBossSize[0], VarBoss1.spriteBossSize[1])){
						VarBoss1.bossState = Status.STANDING;
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy11.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy11.getEnemyXPosition(), 
							enemy11.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-3 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy12.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy12.getEnemyXPosition(), 
							enemy12.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-3 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy13.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy13.getEnemyXPosition(), 
							enemy13.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-3 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy21.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy21.getEnemyXPosition(), 
							enemy21.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-10 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy22.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy22.getEnemyXPosition(), 
							enemy22.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-10 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				if(VarHero.heroState != Status.DEAD && enemy23.getEnemyState() != Status.DISAPPEAR
						&& VarHero.heroState != Status.OVER){
					// 2. Physics collision detection
					if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
							VarHero.spriteSize[0], VarHero.spriteSize[0], enemy23.getEnemyXPosition(), 
							enemy23.getEnemyYPosition(), VarEnemy1.enemy1Size, VarEnemy1.enemy1Size)){
						// 3. Physics collision resolution lastPhysicsFrameMs += physicsDeltaMs;
						VarHero.health = VarHero.health-10 ;
						if(VarHero.health<=0 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.DEAD;
						}
						if(VarHero.health<=-100 && VarHero.heroState != Status.OVER){
							VarHero.heroState = Status.OVER;
						}
					}
				}

				/****************************************************************************************************/

				for(int y=VarGeneral.camPos.getY()/VarGeneral.tileSize; 
						y<=((VarGeneral.camPos.getY()+VarGeneral.camHeight)/VarGeneral.tileSize); y++){
					for(int x=VarGeneral.camPos.getX()/VarGeneral.tileSize; 
							x<=((VarGeneral.camPos.getX()+VarGeneral.camWidth)/VarGeneral.tileSize); x++){

						if(VarBackground1.background.getTile(x, y).getColl()){
							if(GeneralMethods.AABBIntersect(VarHero.spritePos[0], VarHero.spritePos[1], 
									VarHero.spriteSize[0], VarHero.spriteSize[1]-3, x*VarGeneral.tileSize, 
									y*VarGeneral.tileSize-VarGeneral.originalCamPosY, VarGeneral.tileSize, 
									VarGeneral.tileSize)){
								VarHero.spritePos[0] = VarHero.spritePrevPos[0];
								VarHero.spritePos[1] = VarHero.spritePrevPos[1];
							}
						}
					}
				}

				lastPhysicsFrameMS += physicsDeltaMS;
			} while (lastPhysicsFrameMS + physicsDeltaMS < currentFrameMS );

			// Draw sky and floor backgrounds;
			// Background tiles optimization
			for(int y=VarGeneral.camPos.getY()/VarGeneral.tileSize; 
					y<=((VarGeneral.camPos.getY()+VarGeneral.camHeight)/VarGeneral.tileSize); y++){
				for(int x=VarGeneral.camPos.getX()/VarGeneral.tileSize; 
						x<=((VarGeneral.camPos.getX()+VarGeneral.camWidth)/VarGeneral.tileSize); x++){
					GeneralMethods.glDrawSprite(gl, VarBackground1.background.getTileImage(x, y), 
							x*VarGeneral.tileSize-VarGeneral.camPos.getX(), 
							y*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
				}
			}

			//			// Draw building backgrounds;
			//			int y10=4;
			//			int y11=5;
			//			int y12=6;
			//			int y13=7;
			//			for(int x=0; x<160; x++){
			//				GeneralMethods.glDrawSprite(gl, tileBuild[0], x*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[1], (x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[2], (x+2)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[6], (x+3)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[7], (x+4)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[8], (x+5)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y10*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				x = x + 5;
			//			} 
			//
			//			for(int x=0; x<160; x++){
			//				GeneralMethods.glDrawSprite(gl, tileBuild[3], x*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[4], (x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[5], (x+2)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[9], (x+3)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[10], (x+4)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[11], (x+5)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y11*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				x = x + 5;
			//			} 
			//
			//			for(int x=0; x<160; x++){
			//				GeneralMethods.glDrawSprite(gl, tileBuild[12], x*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[13], (x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[14], (x+2)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[18], (x+3)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[19], (x+4)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[20], (x+5)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y12*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				x = x + 5;
			//			} 
			//
			//			for(int x=0; x<160; x++){
			//				GeneralMethods.glDrawSprite(gl, tileBuild[15], x*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[16], (x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[17], (x+2)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[21], (x+3)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[22], (x+4)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				GeneralMethods.glDrawSprite(gl, tileBuild[23], (x+5)*VarGeneral.tileSize-VarGeneral.camPos.getX(), y13*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			//				x = x + 5;
			//			}
			
			// Draw Moon Sprites
			int y3 = 1;
			int x3 = 5;
			int slowFactor = 5;
			GeneralMethods.glDrawSprite(gl, tileMoon[0], 
					(int)x3*VarGeneral.tileSize-VarGeneral.camPos.getX()/slowFactor, 
					y3*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			GeneralMethods.glDrawSprite(gl, tileMoon[1], 
					(int)(x3+1)*VarGeneral.tileSize-VarGeneral.camPos.getX()/slowFactor, 
					y3*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			int y4 =2;
			GeneralMethods.glDrawSprite(gl, tileMoon[2], 
					(int)x3*VarGeneral.tileSize-VarGeneral.camPos.getX()/slowFactor, 
					y4*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
			GeneralMethods.glDrawSprite(gl, tileMoon[3], 
					(int)(x3+1)*VarGeneral.tileSize-VarGeneral.camPos.getX()/slowFactor, 
					y4*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);

			// Draw Cloud Sprites 
			int y1 =2;
			for(int x=0; x<1000; x++){
				// Sprites optimization
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, x/4*VarGeneral.tileSize, 
						y1*VarGeneral.tileSize, VarGeneral.tileSize, VarGeneral.tileSize))
					GeneralMethods.glDrawSprite(gl, tileCloud[0], x*VarGeneral.tileSize-VarGeneral.camPos.getX()*4, 
							y1*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
				// Sprites optimization
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, x/4*VarGeneral.tileSize, y1*VarGeneral.tileSize, 
						VarGeneral.tileSize, VarGeneral.tileSize))
					GeneralMethods.glDrawSprite(gl, tileCloud[1], 
							(x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX()*4, 
							y1*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
				x = x + 5;
			} 
			int y2 =4;
			for(int x=2; x<1000; x++){
				// Sprites optimization
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), 
						VarGeneral.camPos.getY(), VarGeneral.camWidth, VarGeneral.camHeight, 
						x/2*VarGeneral.tileSize, y2*VarGeneral.tileSize, VarGeneral.tileSize, VarGeneral.tileSize))
					GeneralMethods.glDrawSprite(gl, tileCloud[0], 
							x*VarGeneral.tileSize-VarGeneral.camPos.getX()*2, 
							y2*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
				// Sprites optimization
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, x/2*VarGeneral.tileSize, y2*VarGeneral.tileSize, 
						VarGeneral.tileSize, VarGeneral.tileSize))
					GeneralMethods.glDrawSprite(gl, tileCloud[1], 
							(x+1)*VarGeneral.tileSize-VarGeneral.camPos.getX()*2, 
							y2*VarGeneral.tileSize-VarGeneral.camPos.getY(), VarGeneral.tileSize, VarGeneral.tileSize);
				x = x + 5;
			}

			// Drawing Hero and Sprites optimization
			if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
					VarGeneral.camWidth, VarGeneral.camHeight, VarHero.spritePos[0], 
					VarGeneral.originalCamPosY+VarHero.spritePos[1], 26, 44)){ // +880 because of camera shift due to the world
				if(VarHero.heroState == Status.WALKING_LEFT)
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animWalkLeft.getFrameDef()[VarHero.animDataWalkLeft.getCurFrame()].getImage(), 
							VarHero.spritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.spritePos[1]-VarGeneral.camPosChar.getY(), VarHero.spriteSize[0], 
							VarHero.spriteSize[1]);
				else if(VarHero.heroState == Status.WALKING_RIGHT)
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animWalkRight.getFrameDef()[VarHero.animDataWalkRight.getCurFrame()].getImage(), 
							VarHero.spritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.spritePos[1]-VarGeneral.camPosChar.getY(), VarHero.spriteSize[0], 
							VarHero.spriteSize[1]);
				else if(VarHero.heroState == Status.FIRING)
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animFire.getFrameDef()[VarHero.animDataFire.getCurFrame()].getImage(), 
							VarHero.spritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.spritePos[1]-VarGeneral.camPosChar.getY(), VarHero.spriteSize[0], 
							VarHero.spriteSize[1]);
				else if(VarHero.heroState == Status.DEAD){
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animDead.getFrameDef()[VarHero.animDataDead.getCurFrame()].getImage(), 
							VarHero.spritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.spritePos[1]-VarGeneral.camPosChar.getY(), VarHero.spriteSize[0], 
							VarHero.spriteSize[1]);
					if(VarHero.animDataDead.getCurFrame()==4){
						VarHero.heroState = Status.OVER;
					}
				}
				else if(VarHero.heroState == Status.OVER){
					VarHero.spritePos[0]=-100;
					VarHero.gameIsOver = Status.YES;
				}
				else if(VarHero.heroState != Status.OVER){
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animStanding.getFrameDef()[VarHero.animDataStanding.getCurFrame()].getImage(), 
							VarHero.spritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.spritePos[1]-VarGeneral.camPosChar.getY(), VarHero.spriteSize[0], 
							VarHero.spriteSize[1]);
				}

			}

			/****************************************************************************************************/

			// Drawing boss1 and Sprites optimization
			if(VarBoss1.bossState != Status.OVER){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, VarBoss1.spriteBossPos[0], 
						VarGeneral.originalCamPosY+VarBoss1.spriteBossPos[1], VarBoss1.spriteBossSize[0], 
						VarBoss1.spriteBossSize[1])){ // +880 because of camera shift due to the world
					if(VarBoss1.bossState == Status.STANDING)
						GeneralMethods.glDrawSprite(gl, 
								VarBoss1.animBoss1Wonder.getFrameDef()[VarBoss1.animDataBoss1Wonder.getCurFrame()].getImage(), 
								VarBoss1.spriteBossPos[0]-VarGeneral.camPosChar.getX(), 
								VarBoss1.spriteBossPos[1]-VarGeneral.camPosChar.getY(), VarBoss1.spriteBossSize[0], 
								VarBoss1.spriteBossSize[1]);
					else if(VarBoss1.bossState == Status.HIT)
						GeneralMethods.glDrawSprite(gl, 
								VarBoss1.animBoss1Hit.getFrameDef()[VarBoss1.animDataBoss1Hit.getCurFrame()].getImage(), 
								VarBoss1.spriteBossPos[0]-VarGeneral.camPosChar.getX(), 
								VarBoss1.spriteBossPos[1]-VarGeneral.camPosChar.getY(), VarBoss1.spriteBossSize[0], 
								VarBoss1.spriteBossSize[1]);
					else if(VarBoss1.bossState == Status.DEAD){
						System.out.println(VarBoss1.bossState);
						GeneralMethods.glDrawSprite(gl, 
								VarBoss1.animBoss1Dead.getFrameDef()[VarBoss1.animDataBoss1Dead.getCurFrame()].getImage(), 
								VarBoss1.spriteBossPos[0]-VarGeneral.camPosChar.getX(), 
								VarBoss1.spriteBossPos[1]-VarGeneral.camPosChar.getY(), VarBoss1.spriteBossSize[0], 
								VarBoss1.spriteBossSize[1]);
						VarHero.gameIsWon = Status.YES;
					}
					else if(VarBoss1.bossState == Status.OVER || VarBoss1.bossState == Status.DEAD){
						VarHero.gameIsWon = Status.YES;
//						VarHero.spritePos[0]=-100;
					}
					
					if(VarBoss1.spriteBossPos[0] < VarHero.spritePos[0] && (VarBoss1.bossState != Status.DEAD 
							&& VarBoss1.bossState != Status.OVER)){
						VarBoss1.spriteBossPos[0] += 1;
					}
					if(VarBoss1.spriteBossPos[0] > VarHero.spritePos[0] && (VarBoss1.bossState != Status.DEAD 
							&& VarBoss1.bossState != Status.OVER))
						VarBoss1.spriteBossPos[0] -= 1;
				}
			}

			// Drawing Enemy1 and Sprites optimization
			if(enemy11.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy11.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy11.getEnemyYPosition(), VarEnemy1.enemy1Size, 
						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy1.animEnemy1.getFrameDef()[VarEnemy1.animDataEnemy1.getCurFrame()].getImage(), 
							enemy11.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy11.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy1.enemy1SpriteSize[0], 
							VarEnemy1.enemy1SpriteSize[1]);

					if(enemy11.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy11.setEnemyPosition(enemy11.getEnemyXPosition() + 2);
					}
					else
						enemy11.setEnemyPosition(enemy11.getEnemyXPosition() - 2);
				}
			}

			if(enemy12.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy12.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy12.getEnemyYPosition(), VarEnemy1.enemy1Size, 
						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy1.animEnemy1.getFrameDef()[VarEnemy1.animDataEnemy1.getCurFrame()].getImage(), 
							enemy12.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy12.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy1.enemy1SpriteSize[0], 
							VarEnemy1.enemy1SpriteSize[1]);

					if(enemy12.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy12.setEnemyPosition(enemy12.getEnemyXPosition() + 1);
					}
					else
						enemy12.setEnemyPosition(enemy12.getEnemyXPosition() - 1);
				}
			}

			if(enemy13.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy13.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy13.getEnemyYPosition(), VarEnemy1.enemy1Size, 
						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy1.animEnemy1.getFrameDef()[VarEnemy1.animDataEnemy1.getCurFrame()].getImage(), 
							enemy13.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy13.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy1.enemy1SpriteSize[0], 
							VarEnemy1.enemy1SpriteSize[1]);

					if(enemy13.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy13.setEnemyPosition(enemy13.getEnemyXPosition() + 3);
					}
					else
						enemy13.setEnemyPosition(enemy13.getEnemyXPosition() - 3);
				}
			}

			// Drawing Enemy1 and Sprites optimization
			if(enemy21.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy21.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy21.getEnemyYPosition(), VarEnemy2.enemy2Size, 
						VarEnemy2.enemy2Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy2.animEnemy2.getFrameDef()[VarEnemy2.animDataEnemy2.getCurFrame()].getImage(), 
							enemy21.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy21.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy2.enemy2SpriteSize[0], 
							VarEnemy2.enemy2SpriteSize[1]);

					if(enemy21.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy21.setEnemyPosition(enemy21.getEnemyXPosition() + 1);
					}
					else
						enemy21.setEnemyPosition(enemy21.getEnemyXPosition() - 1);
				}
			}

			if(enemy22.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy22.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy22.getEnemyYPosition(), VarEnemy1.enemy1Size, 
						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy2.animEnemy2.getFrameDef()[VarEnemy2.animDataEnemy2.getCurFrame()].getImage(), 
							enemy22.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy22.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy2.enemy2SpriteSize[0], 
							VarEnemy2.enemy2SpriteSize[1]);

					if(enemy22.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy22.setEnemyPosition(enemy22.getEnemyXPosition() + 1);
					}
					else
						enemy22.setEnemyPosition(enemy22.getEnemyXPosition() - 1);
				}
			}

			if(enemy23.getEnemyState() != Status.DISAPPEAR){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, enemy23.getEnemyXPosition(), 
						VarGeneral.originalCamPosY+enemy23.getEnemyYPosition(), VarEnemy1.enemy1Size, 
						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarEnemy2.animEnemy2.getFrameDef()[VarEnemy2.animDataEnemy2.getCurFrame()].getImage(), 
							enemy23.getEnemyXPosition()-VarGeneral.camPosChar.getX(), 
							enemy23.getEnemyYPosition()-VarGeneral.camPosChar.getY(), VarEnemy2.enemy2SpriteSize[0], 
							VarEnemy1.enemy1SpriteSize[1]);

					if(enemy23.getEnemyXPosition() < VarHero.spritePos[0]){
						enemy23.setEnemyPosition(enemy23.getEnemyXPosition() + 1);
					}
					else
						enemy23.setEnemyPosition(enemy23.getEnemyXPosition() - 1);
				}
			}

			/****************************************************************************************************/

			//			// Drawing Enemy1 and Sprites optimization
			//			if(VarEnemy1.enemy1State2 != Status.DISAPPEAR){
			//				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), VarGeneral.camWidth, 
			//						VarGeneral.camHeight, VarEnemy1.enemy1SpritePos2[0], 
			//						VarGeneral.originalCamPosY+VarEnemy1.enemy1SpritePos2[1], VarEnemy1.enemy1Size, 
			//						VarEnemy1.enemy1Size)){ // +880 because of camera shift due to the world
			//					GeneralMethods.glDrawSprite(gl, 
			//							VarEnemy1.animEnemy1.getFrameDef()[VarEnemy1.animDataEnemy1.getCurFrame()].getImage(), 
			//							VarEnemy1.enemy1SpritePos2[0]-VarGeneral.camPosChar.getX(), 
			//							VarEnemy1.enemy1SpritePos2[1]-VarGeneral.camPosChar.getY(), VarEnemy1.enemy1SpriteSize[0], 
			//							VarEnemy1.enemy1SpriteSize[1]);
			//
			//					if(VarEnemy1.enemy1SpritePos2[0] < VarHero.spritePos[0]-100){
			//						VarEnemy1.enemy1SpritePos2[0] += 1;
			//						VarHero.flameState2 = Status.APPEAR_RIGHT;
			//						VarHero.flameSpritePos2[0] = VarEnemy1.enemy1SpritePos2[0]+10;
			//						VarHero.flameSpritePos2[1] = VarEnemy1.enemy1SpritePos2[1];
			//					}
			//					else if(VarEnemy1.enemy1SpritePos2[0] < VarHero.spritePos[0]+100){
			//						VarEnemy1.enemy1SpritePos2[0] -= 1;
			//						//						flameState2 = Status.APPEAR_LEFT;
			//						//						flameSpritePos2[0] = enemy1SpritePos2[0]+10;
			//						//						flameSpritePos2[1] = enemy1SpritePos2[1];
			//					}
			//					if(VarEnemy1.enemy1SpritePos[0]<=VarGeneral.camPos.getX())
			//						VarEnemy1.enemy1SpritePos2[0] += 1;
			//
			//					if(VarEnemy1.enemy1SpritePos2[1] < VarHero.spritePos[1]+23){
			//						VarEnemy1.enemy1SpritePos2[1] += 1;
			//					}
			//					else
			//						VarEnemy1.enemy1SpritePos2[1] -= 1;
			//				}
			//			}

			/****************************************************************************************************/

			// Create blood for boss1 when he's dead
			if(bloodBoss.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						bloodBoss.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						bloodBoss.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					bloodBoss.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy11 when he's dead
			if(blood11.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood11.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood11.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood11.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy12 when he's dead
			if(blood12.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood12.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood12.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood12.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy13 when he's dead
			if(blood13.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood13.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood13.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood13.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy21 when he's dead
			if(blood21.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood21.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood21.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood21.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy22 when he's dead
			if(blood22.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood22.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood22.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood22.setBloodState(Status.DISAPPEAR);
			}

			// Create blood for Enemy23 when he's dead
			if(blood23.getBloodState() == Status.APPEAR){
				GeneralMethods.glDrawSprite(gl, 
						VarBlood.animBlood.getFrameDef()[VarBlood.animDataBlood.getCurFrame()].getImage(), 
						blood23.getBloodXPosition()-VarGeneral.camPosChar.getX(), 
						blood23.getBloodYPosition()-VarGeneral.camPosChar.getY(), VarBlood.bloodSpriteSize[0], 
						VarBlood.bloodSpriteSize[1]);
				if(VarBlood.animDataBlood.getCurFrame()==11)
					blood23.setBloodState(Status.DISAPPEAR);
			}

			/****************************************************************************************************/

			// Create a new Enemy11
			if(enemy11.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy1.nextEnemy1SpritePos){
				enemy11.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy11.setEnemyState(Status.WALKING_LEFT);
				enemy11.setHealthEnemy(2);
			}

			// Create a new Enemy12
			if(enemy12.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy1.nextEnemy1SpritePos){
				enemy12.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy12.setEnemyState(Status.WALKING_LEFT);
				enemy12.setHealthEnemy(2);
			}

			// Create a new Enemy13
			if(enemy13.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy1.nextEnemy1SpritePos){
				enemy13.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy13.setEnemyState(Status.WALKING_LEFT);
				enemy13.setHealthEnemy(2);
			}

			// Create a new Enemy21
			if(enemy21.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy2.nextEnemy2SpritePos){
				enemy21.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy21.setEnemyState(Status.WALKING_LEFT);
				enemy21.setHealthEnemy(4);
			}

			// Create a new Enemy22
			if(enemy22.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy2.nextEnemy2SpritePos){
				enemy22.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy22.setEnemyState(Status.WALKING_LEFT);
				enemy22.setHealthEnemy(4);
			}

			// Create a new Enemy23
			if(enemy23.getEnemyState() == Status.DISAPPEAR && VarGeneral.camPos.getX() >= VarEnemy2.nextEnemy2SpritePos){
				enemy23.setEnemyPosition(VarGeneral.camPos.getX()+VarGeneral.camWidth-VarGeneral.tileSize);
				enemy23.setEnemyState(Status.WALKING_LEFT);
				enemy23.setHealthEnemy(4);
			}

			/****************************************************************************************************/

			// Drawing Mario's projectiles Flame and Sprites optimization
			if(VarHero.flameState == Status.APPEAR_LEFT || VarHero.flameState == Status.APPEAR_RIGHT){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), VarGeneral.camWidth, 
						VarGeneral.camHeight, VarHero.flameSpritePos[0], 
						VarGeneral.originalCamPosY+VarHero.flameSpritePos[1], VarHero.flameSize, VarHero.flameSize)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animFlame.getFrameDef()[VarHero.animDataFlame.getCurFrame()].getImage(), 
							VarHero.flameSpritePos[0]-VarGeneral.camPosChar.getX(), 
							VarHero.flameSpritePos[1]-VarGeneral.camPosChar.getY(), VarHero.flameSpriteSize[0], 
							VarHero.flameSpriteSize[1]);

					if(VarHero.flameState == Status.APPEAR_LEFT){
						VarHero.flameSpritePos[0] -= 3;
						if(VarHero.flameSpritePos[0]<=VarGeneral.camPos.getX()-VarHero.flameSize)
							VarHero.flameState = Status.DISAPPEAR;
					}

					else if(VarHero.flameState == Status.APPEAR_RIGHT)
						VarHero.flameSpritePos[0] += 3;
					if(VarHero.flameSpritePos[0]>=VarGeneral.camPos.getX()+VarGeneral.camWidth)
						VarHero.flameState = Status.DISAPPEAR;
				}
			}

			if(VarHero.flameState2 == Status.APPEAR_LEFT || VarHero.flameState2 == Status.APPEAR_RIGHT){
				if(GeneralMethods.AABBIntersect(VarGeneral.camPos.getX(), VarGeneral.camPos.getY(), 
						VarGeneral.camWidth, VarGeneral.camHeight, VarHero.flameSpritePos2[0], 
						VarGeneral.originalCamPosY+VarHero.flameSpritePos2[1], VarHero.flameSize, VarHero.flameSize)){ // +880 because of camera shift due to the world
					GeneralMethods.glDrawSprite(gl, 
							VarHero.animFlame.getFrameDef()[VarHero.animDataFlame.getCurFrame()].getImage(), 
							VarHero.flameSpritePos2[0]-VarGeneral.camPosChar.getX(), 
							VarHero.flameSpritePos2[1]-VarGeneral.camPosChar.getY(), VarHero.flameSpriteSize[0], 
							VarHero.flameSpriteSize[1]);

					if(VarHero.flameState2 == Status.APPEAR_LEFT){
						VarHero.flameSpritePos2[0] -= 3;
						if(VarHero.flameSpritePos2[0]<=VarGeneral.camPos.getX()-VarHero.flameSize)
							VarHero.flameState2 = Status.DISAPPEAR;
					}

					else if(VarHero.flameState2 == Status.APPEAR_RIGHT)
						VarHero.flameSpritePos2[0] += 3;
					if(VarHero.flameSpritePos2[0]>=VarGeneral.camPos.getX()+VarGeneral.camWidth)
						VarHero.flameState2 = Status.DISAPPEAR;
				}
			}
			
			if(VarHero.gameIsOver == Status.YES)
				GeneralMethods.glDrawSprite(gl,VarHero.gameOver,0, 
						0, 640,400);
			
			if(VarHero.gameIsWon == Status.YES)
				GeneralMethods.glDrawSprite(gl,VarHero.gameWon,0, 
						0, 640,400);

		}
		System.exit(0);
	}
}
