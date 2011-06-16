package forplay.sample.tictacktoe;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Color;
import forplay.core.Image;
import forplay.core.Surface;

public class Result extends Entity {

  Image xWin;
  Image oWin;
  Player player;
  
  private static int NA = 0;
  private static int XWIN = 1;
  private static int OWIN = 2;
  
  public Result(Surface surface, float px, float py, float width, float height) {
    super(surface, px, py, width, height);
    this.xWin = assetManager().getImage("images/xwin.png"); 
    this.oWin = assetManager().getImage("images/owin.png");
    this.reset();
  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    if (this.cur_status == Result.XWIN) {
      this.surface.drawImage(this.xWin, this.px, this.py);
    } else if (this.cur_status == Result.OWIN) {
      this.surface.drawImage(this.oWin, this.px, this.py);
    }
  }

  @Override
  public boolean changeStatus() {
    // TODO Auto-generated method stub
    if (this.player.cur_status == Player.PLAYX) {
      this.cur_status = Result.XWIN;
    } else if (this.player.cur_status == Player.PLAYO) {
      this.cur_status = Result.OWIN;
    }
    return true;
  }

  @Override
  public void reset() {
    this.cur_status = Result.NA;
    this.pre_status = Result.NA;
    this.surface.setFillColor(Color.rgb(255, 255, 255));
    this.surface.fillRect(this.px, this.py, this.width, this.height);
  }
  
  public void attachPlayer(Player player) {
    this.player = player;
  }
}
