
public class VarBoss1 {

	// Constant tile size
	public static final int bossSize = 50;

	// Store character's state
	public static Status bossState;
	
	// Health Hero
	public static float bossHealth;

	// Position of the sprite.
	public static int[] spriteBossPos = new int[] { 3400-140, VarGeneral.camHeight/2-125 };
//	public static int[] spriteBossPos = new int[] { VarGeneral.camHeight-140, VarGeneral.camHeight/2-125 };

	// Size of the sprite.
	public static int[] spriteBossSize = new int[2];

	// Texture for the sprite.
	public static int boss1Wonder1;
	public static int boss1Wonder2;
	public static int boss1Wonder3;

	public static int boss1Hit1;
	public static int boss1Hit2;
	public static int boss1Hit3;
	public static int boss1Hit4;
	public static int boss1Hit5;
	public static int boss1Hit6;
	public static int boss1Hit7;
	public static int boss1Hit8;

	public static int boss1Dead1;
	public static int boss1Dead2;
	public static int boss1Dead3;

	// Frame Definition for walking right
	public static FrameDef frameBoss1Wonder1;
	public static FrameDef frameBoss1Wonder2;
	public static FrameDef frameBoss1Wonder3;
	
	// Frame Definition for death
	public static FrameDef frameBoss1Dead1;
	public static FrameDef frameBoss1Dead2;
	public static FrameDef frameBoss1Dead3;

	// Frame Definition for firing
	public static FrameDef frameBoss1Hit1;
	public static FrameDef frameBoss1Hit2;
	public static FrameDef frameBoss1Hit3;
	public static FrameDef frameBoss1Hit4;
	public static FrameDef frameBoss1Hit5;
	public static FrameDef frameBoss1Hit6;
	public static FrameDef frameBoss1Hit7;
	public static FrameDef frameBoss1Hit8;

	// Animation Definition and Data
	public static AnimationDef animBoss1Wonder;
	public static AnimationData animDataBoss1Wonder;
	public static AnimationDef animBoss1Hit;
	public static AnimationData animDataBoss1Hit;
	public static AnimationDef animBoss1Dead;
	public static AnimationData animDataBoss1Dead;


	public VarBoss1(){

		// Initialize character's state
		bossState = Status.STANDING;
		bossHealth = 25;

		// Load the texture for character animation on standing
		boss1Wonder1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Wonder-steps1.tga", spriteBossSize);
		boss1Wonder2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Wonder-steps2.tga", spriteBossSize);
		boss1Wonder3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Wonder-steps3.tga", spriteBossSize);

		// Load the texture for character animation on walking
		boss1Dead1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Dead-steps1.tga", spriteBossSize);
		boss1Dead2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Dead-steps2.tga", spriteBossSize);
		boss1Dead3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Dead-steps3.tga", spriteBossSize);

		// Load the texture for character animation on firing
		boss1Hit1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps1.tga", spriteBossSize);
		boss1Hit2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps2.tga", spriteBossSize);
		boss1Hit3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps3.tga", spriteBossSize);
		boss1Hit4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps4.tga", spriteBossSize);
		boss1Hit5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps5.tga", spriteBossSize);
		boss1Hit6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps6.tga", spriteBossSize);
		boss1Hit7 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps7.tga", spriteBossSize);
		boss1Hit8 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "boss-Hit-steps8.tga", spriteBossSize);

		// Set each frame
		frameBoss1Wonder1 = new FrameDef(boss1Wonder1, VarGeneral.timeFrame);
		frameBoss1Wonder2 = new FrameDef(boss1Wonder2, VarGeneral.timeFrame);
		frameBoss1Wonder3 = new FrameDef(boss1Wonder3, VarGeneral.timeFrame);

		frameBoss1Dead1 = new FrameDef(boss1Dead1, VarGeneral.timeFrame);
		frameBoss1Dead2 = new FrameDef(boss1Dead2, VarGeneral.timeFrame);
		frameBoss1Dead3 = new FrameDef(boss1Dead3, VarGeneral.timeFrame);

		frameBoss1Hit1 = new FrameDef(boss1Hit1, VarGeneral.timeFrame);
		frameBoss1Hit2 = new FrameDef(boss1Hit2, VarGeneral.timeFrame);
		frameBoss1Hit3 = new FrameDef(boss1Hit3, VarGeneral.timeFrame);
		frameBoss1Hit4 = new FrameDef(boss1Hit4, VarGeneral.timeFrame);
		frameBoss1Hit5 = new FrameDef(boss1Hit5, VarGeneral.timeFrame);
		frameBoss1Hit6 = new FrameDef(boss1Hit6, VarGeneral.timeFrame);
		frameBoss1Hit7 = new FrameDef(boss1Hit7, VarGeneral.timeFrame);
		frameBoss1Hit8 = new FrameDef(boss1Hit8, VarGeneral.timeFrame);

	}
}
