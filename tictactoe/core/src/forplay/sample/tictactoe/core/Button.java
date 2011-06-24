package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import forplay.core.Surface;

public class Button extends Entity {

  private Image image;
  public Button(Surface surface, float px, float py, float width, float height) {
    super(surface, px, py, width, height);
    Image button = assetManager().getImage("images/button.png");
    this.surface.drawImage(button, px, py);
  }
  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public boolean changeStatus() {
    return false;
  }
  @Override
  public void reset() {
    // TODO Auto-generated method stub
    
  }

}
