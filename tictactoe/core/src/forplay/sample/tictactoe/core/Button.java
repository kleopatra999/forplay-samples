package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import forplay.core.GroupLayer;
import forplay.core.Image;

public class Button extends Entity {

  public static int WIDTH = 100;
  public static int HEIGHT = 40;

  public Button(GroupLayer groupLayer, float px, float py) {
    super(groupLayer, px, py, WIDTH, HEIGHT);
    Image button = assetManager().getImage("images/button.png");
    this.loadImage(button);
  }
}
