
public class VarEnemy1 {

	// Constant tile size
	public static final int enemy1Size = 20;

	// Store character's state
	private Status enemy1State;
	
	// Health of Enemy1
	private float healthEnemy1;

	// Position of the sprite.
	private int[] enemy1SpritePos;


	// Next Position of the sprite
	public static int nextEnemy1SpritePos;

	// Size of the sprite.
	public static int[] enemy1SpriteSize = new int[2];
	
	// Texture for the sprite.
	public static int enemy11;
	public static int enemy12;
	public static int enemy13;
	public static int enemy14;

	// Frame Definition for enemy_mushroom motion
	public static FrameDef frameEnemy11;
	public static FrameDef frameEnemy12;
	public static FrameDef frameEnemy13;
	public static FrameDef frameEnemy14;

	// Animation Definition and Data
	public static AnimationDef animEnemy1;
	public static AnimationData animDataEnemy1;
	
	public VarEnemy1(){
		
		// Initialize character's state
		enemy1State = Status.WALKING_LEFT;

		// Load the texture for character animation on left
		enemy11 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy1-steps1.tga", enemy1SpriteSize);
		enemy12 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy1-steps2.tga", enemy1SpriteSize);
		enemy13 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy1-steps3.tga", enemy1SpriteSize);
		enemy14 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "enemy1-steps4.tga", enemy1SpriteSize);
		
		// Set each frame
		frameEnemy11 = new FrameDef(enemy11, VarGeneral.timeFrame);
		frameEnemy12 = new FrameDef(enemy12, VarGeneral.timeFrame);
		frameEnemy13 = new FrameDef(enemy13, VarGeneral.timeFrame);
		frameEnemy14 = new FrameDef(enemy14, VarGeneral.timeFrame);
		
		// Initializing Position of the sprite
		enemy1SpritePos = new int[] { VarGeneral.camWidth-53/2, VarGeneral.camHeight/2-66 };
		
		// Initializing Health of the sprite
		healthEnemy1 = 2;
	}
	
	public Status getEnemyState(){
		return enemy1State;
	}
	
	public void setEnemyState(Status newStatus){
		enemy1State = newStatus;
	}
	
	public int getEnemyXPosition(){
		return enemy1SpritePos[0];
	}
	
	public int getEnemyYPosition(){
		return enemy1SpritePos[1];
	}
	
	public void setEnemyPosition(int newPos){
		enemy1SpritePos[0] = newPos;
	}
	
	public void setHealthEnemy(float health){
		this.healthEnemy1 = health;
	}
	
	public float getHealthEnemy(){
		return healthEnemy1;
	}
}
