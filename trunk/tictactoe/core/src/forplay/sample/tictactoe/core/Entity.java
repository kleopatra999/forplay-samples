package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.log;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;

public abstract class Entity {

  float px;
  float py;
  boolean isHit;
  GroupLayer groupLayer;
  int width;
  int height;
  ImageLayer imageLayer;
  protected Image[] img_list;
  Image initImage;

  public Entity(GroupLayer groupLayer, float px, float py, int width, int height) {
    this.groupLayer = groupLayer;
    this.px = px;
    this.py = py;
    this.isHit = false;
    this.width = width;
    this.height = height;
    this.imageLayer = graphics().createImageLayer();
    this.imageLayer.setTranslation(px, py);
    groupLayer.add(this.imageLayer);
    
  }

  void loadImage(Image image) {
    image.addCallback(new ResourceCallback<Image>() {
      @Override
      public void done(Image image) {
        imageLayer.setImage(image);
      }

      @Override
      public void error(Throwable err) {
        log().error("Error loading image!", err);
      }
    });
  }
  
  public boolean hitTest(float x, float y) {
    if ((x > px) && (x < px + width) && (y > py) && (y < py + height)) {
      this.isHit = true;
    } else {
      this.isHit = false;
    }
    return this.isHit;
  } 
}
