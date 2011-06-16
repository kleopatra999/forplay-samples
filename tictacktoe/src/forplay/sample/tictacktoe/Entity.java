package forplay.sample.tictacktoe;

import forplay.core.Surface;

public abstract class Entity {

  float px;
  float py;
  float width;
  float height;
  boolean isHit;
  Surface surface;
  int pre_status;
  int cur_status;

  public Entity(Surface surface, float px, float py, float width, float height) {
    this.surface = surface;
    this.px = px;
    this.py = py;
    this.width = width;
    this.height = height;
    this.isHit = false;
  }

  public boolean hitTest(float x, float y) {
    if ((x > px) && (x < px + width) && (y > py) && (y < py + height)) {
      this.isHit = true;
    } else {
      this.isHit = false;
    }
    return this.isHit;
  }
  
  public abstract void update(float delta); 
  public abstract boolean changeStatus();
  public abstract void reset();
  
  public int getStatus() {
    return this.cur_status;
  }

}
