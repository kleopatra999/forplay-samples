package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
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

  Image xWin;
  Image oWin;

  public Result(Surface surface, float px, float py) {
    super(surface, px, py, WIDTH, HEIGHT);
    this.xWin = assetManager().getImage("images/xwin.png");
    this.oWin = assetManager().getImage("images/owin.png");
    this.reset();
  }

  public void update(float delta) {
    // TODO Auto-generated method stub
  }

  public void changeState(State newState) {
    if (newState == State.XWIN) {
      this.surface.drawImage(this.xWin, px, py);
    } else {
      this.surface.drawImage(this.oWin, px, py);
    }
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub

  }
}
