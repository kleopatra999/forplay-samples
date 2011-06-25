package forplay.sample.tictactoe.core;

import forplay.core.Surface;

public abstract class Entity {

  float px;
  float py;
  boolean isHit;
  Surface surface;
  int width;
  int height;

  public Entity(Surface surface, float px, float py, int width, int height) {
    this.surface = surface;
    this.px = px;
    this.py = py;
    this.isHit = false;
    this.width = width;
    this.height = height;
  }

  public boolean hitTest(float x, float y) {
    if ((x > px) && (x < px + width) && (y > py) && (y < py + height)) {
      this.isHit = true;
    } else {
      this.isHit = false;
    }
    return this.isHit;
  }
  
  public abstract void reset();
}
