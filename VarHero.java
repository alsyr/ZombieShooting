
public class VarHero {

	// Constant tile size
	public static final int flameSize = 36;
	
	// Health Hero
	public static float health;

	// Store character's state
	public static Status heroState;
	public static Status flameState;
	public static Status flameState2;
	public static Status gameIsOver;
	public static Status gameIsWon;

	// Position of the sprite.
	public static int[] spritePos = new int[] { VarGeneral.camWidth/2-77/2, VarGeneral.camHeight/2-66 };
	public static int[] spritePrevPos = new int[] { VarGeneral.camWidth/2-77/2, VarGeneral.camHeight/2-66 };
	public static int[] flameSpritePos = new int[2];
	public static int[] flameSpritePos2 = new int[2];

	// Size of the sprite.
	public static int[] spriteSize = new int[2];
	public static int[] flameSpriteSize = new int[2];

	// Texture for the game.
	public static int gameOver;
	public static int gameWon;
	
	// Texture for the sprite.
	public static int heroWalk1;
	public static int heroWalk2;
	public static int heroWalk3;
	public static int heroWalk4;
	public static int heroWalk5;
	public static int heroWalk6;
	public static int heroWalk7;
	public static int heroWalk8;
	public static int heroWalk9;
	public static int heroWalk10;

	public static int heroFiring1;
	public static int heroFiring2;
	public static int heroFiring3;
	public static int heroFiring4;
	public static int heroFiring5;

	public static int heroDead1;
	public static int heroDead2;
	public static int heroDead3;
	public static int heroDead4;
	public static int heroDead5;
	public static int heroDead6;

	public static int flame1;
	public static int flame2;
	public static int flame3;
	public static int flame4;
	public static int flame5;
	public static int flame6;
	public static int flame7;
	public static int flame8;
	public static int flame9;
	public static int flame10;
	public static int flame11;
	public static int flame12;

	// Frame Definition for walking right
	public static FrameDef frameWalk1;
	public static FrameDef frameWalk2;
	public static FrameDef frameWalk3;
	public static FrameDef frameWalk4;
	public static FrameDef frameWalk5;
	public static FrameDef frameWalk6;
	public static FrameDef frameWalk7;
	public static FrameDef frameWalk8;
	public static FrameDef frameWalk9;
	public static FrameDef frameWalk10;

	// Frame Definition for death
	public static FrameDef frameDead1;
	public static FrameDef frameDead2;
	public static FrameDef frameDead3;
	public static FrameDef frameDead4;
	public static FrameDef frameDead5;
	public static FrameDef frameDead6;

	// Frame Definition for firing
	public static FrameDef frameFire1;
	public static FrameDef frameFire2;
	public static FrameDef frameFire3;
	public static FrameDef frameFire4;
	public static FrameDef frameFire5;

	// Frame Definition for flame motion
	public static FrameDef frameFlame1;
	public static FrameDef frameFlame2;
	public static FrameDef frameFlame3;
	public static FrameDef frameFlame4;
	public static FrameDef frameFlame5;
	public static FrameDef frameFlame6;
	public static FrameDef frameFlame7;
	public static FrameDef frameFlame8;
	public static FrameDef frameFlame9;
	public static FrameDef frameFlame10;
	public static FrameDef frameFlame11;
	public static FrameDef frameFlame12;

	// Animation Definition and Data
	public static AnimationDef animStanding;
	public static AnimationData animDataStanding;
	public static AnimationDef animWalkRight;
	public static AnimationData animDataWalkRight;
	public static AnimationDef animWalkLeft;
	public static AnimationData animDataWalkLeft;
	public static AnimationDef animDead;
	public static AnimationData animDataDead;
	public static AnimationDef animFire;
	public static AnimationData animDataFire;
	public static AnimationDef animFlame;
	public static AnimationData animDataFlame;


	public VarHero(){

		// Initialize character's state
		heroState = Status.STANDING;
		flameState = Status.DISAPPEAR;
		flameState2 = Status.DISAPPEAR;
		gameIsOver = Status.NO;
		gameIsWon = Status.NO;
		health = 10000;
		
		gameOver = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "gameOver.tga", spriteSize);
		gameWon = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "gameIsWon.tga", spriteSize);

		// Load the texture for character animation on standing
		heroWalk1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps1.tga", spriteSize);
		heroWalk2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps2.tga", spriteSize);
		heroWalk3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps3.tga", spriteSize);
		heroWalk4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps4.tga", spriteSize);
		heroWalk5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps5.tga", spriteSize);
		heroWalk6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps6.tga", spriteSize);
		heroWalk7 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps7.tga", spriteSize);
		heroWalk8 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps8.tga", spriteSize);
		heroWalk9 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps9.tga", spriteSize);
		heroWalk10 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "walk-steps10.tga", spriteSize);

		// Load the texture for character animation on walking
		heroDead1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps1.tga", spriteSize);
		heroDead2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps2.tga", spriteSize);
		heroDead3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps3.tga", spriteSize);
		heroDead4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps4.tga", spriteSize);
		heroDead5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps5.tga", spriteSize);
		heroDead6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "dead-steps6.tga", spriteSize);

		// Load the texture for character animation on firing
		heroFiring1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "firing-steps1.tga", spriteSize);
		heroFiring2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "firing-steps2.tga", spriteSize);
		heroFiring3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "firing-steps3.tga", spriteSize);
		heroFiring4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "firing-steps4.tga", spriteSize);
		heroFiring5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "firing-steps5.tga", spriteSize);

		// Load the texture for character animation on left
		flame1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps1.tga", flameSpriteSize);
		flame2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps2.tga", flameSpriteSize);
		flame3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps3.tga", flameSpriteSize);
		flame4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps4.tga", flameSpriteSize);
		flame5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps5.tga", flameSpriteSize);
		flame6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps6.tga", flameSpriteSize);
		flame7 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps7.tga", flameSpriteSize);
		flame8 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps8.tga", flameSpriteSize);
		flame9 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps9.tga", flameSpriteSize);
		flame10 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps10.tga", flameSpriteSize);
		flame11 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps11.tga", flameSpriteSize);
		flame12 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "Flame-steps12.tga", flameSpriteSize);

		// Set each frame
		frameWalk1 = new FrameDef(heroWalk1, VarGeneral.timeFrame);
		frameWalk2 = new FrameDef(heroWalk2, VarGeneral.timeFrame);
		frameWalk3 = new FrameDef(heroWalk3, VarGeneral.timeFrame);
		frameWalk4 = new FrameDef(heroWalk4, VarGeneral.timeFrame);
		frameWalk5 = new FrameDef(heroWalk5, VarGeneral.timeFrame);
		frameWalk6 = new FrameDef(heroWalk6, VarGeneral.timeFrame);
		frameWalk7 = new FrameDef(heroWalk7, VarGeneral.timeFrame);
		frameWalk8 = new FrameDef(heroWalk8, VarGeneral.timeFrame);
		frameWalk9 = new FrameDef(heroWalk9, VarGeneral.timeFrame);
		frameWalk10 = new FrameDef(heroWalk10, VarGeneral.timeFrame);

		frameDead1 = new FrameDef(heroDead1, VarGeneral.timeFrame*2);
		frameDead2 = new FrameDef(heroDead2, VarGeneral.timeFrame*2);
		frameDead3 = new FrameDef(heroDead3, VarGeneral.timeFrame*2);
		frameDead4 = new FrameDef(heroDead4, VarGeneral.timeFrame*2);
		frameDead5 = new FrameDef(heroDead5, VarGeneral.timeFrame*2);
		frameDead6 = new FrameDef(heroDead6, VarGeneral.timeFrame*2);

		frameFire1 = new FrameDef(heroFiring1, VarGeneral.timeFrame*2);
		frameFire2 = new FrameDef(heroFiring2, VarGeneral.timeFrame*2);
		frameFire3 = new FrameDef(heroFiring3, VarGeneral.timeFrame*2);
		frameFire4 = new FrameDef(heroFiring4, VarGeneral.timeFrame*2);
		frameFire5 = new FrameDef(heroFiring5, VarGeneral.timeFrame*2);

		frameFlame1 = new FrameDef(flame1, VarGeneral.timeFrame);
		frameFlame2 = new FrameDef(flame2, VarGeneral.timeFrame);
		frameFlame3 = new FrameDef(flame3, VarGeneral.timeFrame);
		frameFlame4 = new FrameDef(flame4, VarGeneral.timeFrame);
		frameFlame5 = new FrameDef(flame5, VarGeneral.timeFrame);
		frameFlame6 = new FrameDef(flame6, VarGeneral.timeFrame);
		frameFlame7 = new FrameDef(flame7, VarGeneral.timeFrame);
		frameFlame8 = new FrameDef(flame8, VarGeneral.timeFrame);
		frameFlame9 = new FrameDef(flame9, VarGeneral.timeFrame);
		frameFlame10 = new FrameDef(flame10, VarGeneral.timeFrame);
		frameFlame11 = new FrameDef(flame11, VarGeneral.timeFrame);
		frameFlame12 = new FrameDef(flame12, VarGeneral.timeFrame);
	}
}
