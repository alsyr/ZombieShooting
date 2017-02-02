
public class BackgroundDef {

  private int width;
  private int height;
  private Tile[] tiles;

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setTiles(Tile[] tiles) {
    this.tiles = tiles;
  }

  public Tile getTile(int x, int y) {
    return tiles[y * width + x];
  }

  public int getTileImage(int x, int y) {
    return tiles[y * width + x].getImage();
  }
}