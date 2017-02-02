
public class AnimationData {

  private AnimationDef def;
  private int curFrame;
  private float secsUntilNextFrame;

  public AnimationData(AnimationDef def, int curFrame, float time) {
    this.def = def;
    this.curFrame = curFrame;
    this.secsUntilNextFrame = time;
  }

  public void update(float deltaTime) {
    secsUntilNextFrame = secsUntilNextFrame + deltaTime;
    if (secsUntilNextFrame >= def.getFrameDef()[curFrame].getFrameTimeSecs()) {
      secsUntilNextFrame = 0;
      if (curFrame + 1 == def.getFrameDef().length)
        curFrame = 0;
      else
        curFrame++;
    }
  }

  public int getCurFrame() {
    return curFrame;
  }

  public void draw(int x, int y) {

  }
}