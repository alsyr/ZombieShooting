
public class FrameDef {

  int image;
  float frameTimeSecs;

  public FrameDef(int image, float frameTimeSecs) {
    this.image = image;
    this.frameTimeSecs = frameTimeSecs;
  }

  public int getImage() {
    return image;
  }

  public float getFrameTimeSecs() {
    return frameTimeSecs;
  }
}
