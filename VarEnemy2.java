
public class VarEnemy2 {

	// Constant tile size
	public static final int enemy2Size = 20;

	// Store character's state
	private Status enemy2State;
	
	// Health of Enemy2
	private float healthEnemy2;

	// Position of the sprite.
	private int[] enemy2SpritePos;
	
	// Next Position of the sprite
	public static int nextEnemy2SpritePos;

	// Size of the sprite.
	public static int[] enemy2SpriteSize = new int[2];
	
	// Texture for the sprite.
	public static int enemy21;
	public static int enemy22;
	public static int enemy23;
	public static int enemy24;
	public static int enemy25;
	public static int enemy26;
	public static int enemy27;
	public static int enemy28;
	public static int enemy29;

	// Frame Definition for enemy_mushroom motion
	public static FrameDef frameEnemy21;
	public static FrameDef frameEnemy22;
	public static FrameDef frameEnemy23;
	public static FrameDef frameEnemy24;
	public static FrameDef frameEnemy25;
	public static FrameDef frameEnemy26;
	public static FrameDef frameEnemy27;
	public static FrameDef frameEnemy28;
	public static FrameDef frameEnemy29;

	// Animation Definition and Data
	public static AnimationDef animEnemy2;
	public static AnimationData animDataEnemy2;
	
	public VarEnemy2(){
		
		// Initialize character's state
		enemy2State = Status.WALKING_LEFT;

		// Load the texture for character animation on left
		enemy21 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps1.tga", enemy2SpriteSize);
		enemy22 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps2.tga", enemy2SpriteSize);
		enemy23 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps3.tga", enemy2SpriteSize);
		enemy24 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps4.tga", enemy2SpriteSize);
		enemy25 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps5.tga", enemy2SpriteSize);
		enemy26 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps6.tga", enemy2SpriteSize);
		enemy27 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps7.tga", enemy2SpriteSize);
		enemy28 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps8.tga", enemy2SpriteSize);
		enemy29 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy2-steps9.tga", enemy2SpriteSize);
		
		// Set each frame
		frameEnemy21 = new FrameDef(enemy21, VarGeneral.timeFrame);
		frameEnemy22 = new FrameDef(enemy22, VarGeneral.timeFrame);
		frameEnemy23 = new FrameDef(enemy23, VarGeneral.timeFrame);
		frameEnemy24 = new FrameDef(enemy24, VarGeneral.timeFrame);
		frameEnemy25 = new FrameDef(enemy25, VarGeneral.timeFrame);
		frameEnemy26 = new FrameDef(enemy26, VarGeneral.timeFrame);
		frameEnemy27 = new FrameDef(enemy27, VarGeneral.timeFrame);
		frameEnemy28 = new FrameDef(enemy28, VarGeneral.timeFrame);
		frameEnemy29 = new FrameDef(enemy29, VarGeneral.timeFrame);
		
		// Initializing Position of the sprite
		enemy2SpritePos = new int[] { VarGeneral.camWidth-53/2, VarGeneral.camHeight/2-63 };
		
		// Initializing Health of the sprite
		healthEnemy2 = 4;
	}
	
	public Status getEnemyState(){
		return enemy2State;
	}
	
	public void setEnemyState(Status newStatus){
		enemy2State = newStatus;
	}
	
	public int getEnemyXPosition(){
		return enemy2SpritePos[0];
	}
	
	public int getEnemyYPosition(){
		return enemy2SpritePos[1];
	}
	
	public void setEnemyPosition(int newPos){
		enemy2SpritePos[0] = newPos;
	}
	
	public void setHealthEnemy(float health){
		this.healthEnemy2 = health;
	}
	
	public float getHealthEnemy(){
		return healthEnemy2;
	}
}
