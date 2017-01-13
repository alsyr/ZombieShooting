
public class VarGeneral {

	// Camera Size
	public static final int camWidth = 640;
	public static final int camHeight = 480;
	
	// End Of World
	public static final int endWorld = 3400-camWidth-10;

	// Original position camera on Y
	public static final int originalCamPosY = 30;
	
	// Constant tile size
	public static final int tileSize = 34;
	
	// Constant time frame
	public static final int timeFrame = 100;
	
	// Set this to true to force the game to exit.
	public static boolean shouldExit;

	// The previous frame's keyboard state.
	public static boolean kbPrevState[] = new boolean[256];

	// The current frame's keyboard state.
	public static boolean kbState[] = new boolean[256];
	
	// Position of the camera
	public static Camera camPos;
	public static Camera camPosChar;
	
	
	public VarGeneral(){
		
		// Initialize camera
		camPos = new Camera(0,originalCamPosY);
		camPosChar = new Camera(0,0);
	}
}
