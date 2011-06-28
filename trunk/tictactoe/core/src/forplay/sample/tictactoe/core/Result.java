package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.Surface;

public class Result extends Entity {

  public enum State {
    NA(0), XWIN(1), OWIN(2);
    private int val;

    int value() {
      return val;
    }

    State(int value) {
      this.val = value;
    }
  }

  public static int WIDTH = 200;
  public static int HEIGHT = 50;

  Image[] image_list;
  Image oWin;
  State currentState;

  public Result(GroupLayer groupLayer, float px, float py) {
    super(groupLayer, px, py, WIDTH, HEIGHT);
    image_list = new Image[3];
    image_list[0] = assetManager().getImage("images/nobodywin.png");
    image_list[1] = assetManager().getImage("images/xwin.png"); 
    image_list[2] = assetManager().getImage("images/owin.png");
    this.currentState = State.NA;
    this.loadImage(image_list[0]);
  }

  public void changeState(State newState) {
    if (newState != this.currentState) {
      this.loadImage(this.image_list[newState.value()]);
      this.currentState = newState;
    }
  }
}
