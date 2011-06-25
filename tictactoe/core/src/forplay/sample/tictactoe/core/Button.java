package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import forplay.core.Surface;

public class Button extends Entity {

  public static int WIDTH = 100;
  public static int HEIGHT = 40;

  public Button(Surface surface, float px, float py) {
    super(surface, px, py, WIDTH, HEIGHT);
    Image button = assetManager().getImage("images/button.png");
    this.surface.drawImage(button, px, py);
  }

}
