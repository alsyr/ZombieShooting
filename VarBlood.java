
public class VarBlood {

  // Constant tile size
  public static final int bloodSize = 50;

  // Store blood's state
  private Status bloodState;

  // Position of the sprite.
  private int[] bloodSpritePos;

  // Size of the sprite.
  public static int[] bloodSpriteSize = new int[2];

  // Texture for the sprite.
  public static int blood1;
  public static int blood2;
  public static int blood3;
  public static int blood4;
  public static int blood5;
  public static int blood6;
  public static int blood7;
  public static int blood8;
  public static int blood9;
  public static int blood10;
  public static int blood11;
  public static int blood12;

  // Frame Definition for enemy_mushroom motion
  public static FrameDef frameBlood1;
  public static FrameDef frameBlood2;
  public static FrameDef frameBlood3;
  public static FrameDef frameBlood4;
  public static FrameDef frameBlood5;
  public static FrameDef frameBlood6;
  public static FrameDef frameBlood7;
  public static FrameDef frameBlood8;
  public static FrameDef frameBlood9;
  public static FrameDef frameBlood10;
  public static FrameDef frameBlood11;
  public static FrameDef frameBlood12;

  // Animation Definition and Data
  public static AnimationDef animBlood;
  public static AnimationData animDataBlood;

  public VarBlood() {

    // Initialize blood's state
    bloodState = Status.DISAPPEAR;

    // Load the texture for character animation on left
    blood1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps1.tga", bloodSpriteSize);
    blood2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps2.tga", bloodSpriteSize);
    blood3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps3.tga", bloodSpriteSize);
    blood4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps4.tga", bloodSpriteSize);
    blood5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps5.tga", bloodSpriteSize);
    blood6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps6.tga", bloodSpriteSize);
    blood7 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps7.tga", bloodSpriteSize);
    blood8 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps8.tga", bloodSpriteSize);
    blood9 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps9.tga", bloodSpriteSize);
    blood10 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps10.tga", bloodSpriteSize);
    blood11 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps11.tga", bloodSpriteSize);
    blood12 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "blood-steps12.tga", bloodSpriteSize);

    // Set each frame
    frameBlood1 = new FrameDef(blood1, VarGeneral.timeFrame);
    frameBlood2 = new FrameDef(blood2, VarGeneral.timeFrame);
    frameBlood3 = new FrameDef(blood3, VarGeneral.timeFrame);
    frameBlood4 = new FrameDef(blood4, VarGeneral.timeFrame);
    frameBlood5 = new FrameDef(blood5, VarGeneral.timeFrame);
    frameBlood6 = new FrameDef(blood6, VarGeneral.timeFrame);
    frameBlood7 = new FrameDef(blood7, VarGeneral.timeFrame);
    frameBlood8 = new FrameDef(blood8, VarGeneral.timeFrame);
    frameBlood9 = new FrameDef(blood9, VarGeneral.timeFrame);
    frameBlood10 = new FrameDef(blood10, VarGeneral.timeFrame);
    frameBlood11 = new FrameDef(blood11, VarGeneral.timeFrame);
    frameBlood12 = new FrameDef(blood12, VarGeneral.timeFrame);

    // Initializing Position of the sprite
    bloodSpritePos = new int[]{VarGeneral.camWidth - bloodSize, VarGeneral.camHeight / 2 - bloodSize + 3};
  }

  public Status getBloodState() {
    return bloodState;
  }

  public void setBloodState(Status newStatus) {
    bloodState = newStatus;
  }

  public int getBloodXPosition() {
    return bloodSpritePos[0];
  }

  public int getBloodYPosition() {
    return bloodSpritePos[1];
  }

  public void setbloodXPosition(int newPos) {
    bloodSpritePos[0] = newPos;
  }

  public void setbloodYPosition(int newPos) {
    bloodSpritePos[1] = newPos;
  }
}
