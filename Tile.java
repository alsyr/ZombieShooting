
public class Tile {
  int image;
  boolean coll = false;

  public Tile() {
  }

  public Tile(int image, boolean coll) {
    this.image = image;
    this.coll = coll;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public int getImage() {
    return image;
  }

  public boolean getColl() {
    return coll;
  }

  public void setColl(Boolean coll) {
    this.coll = coll;
  }
}
