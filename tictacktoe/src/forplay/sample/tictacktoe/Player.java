package forplay.sample.tictacktoe;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import forplay.core.Surface;

public class Player extends Entity {

  private Image playX;
  private Image playO;
    
  public final static int PLAYX = 0;
  public final static int PLAYO = 1;
  
  boolean isPlayX;
  public Player(Surface surface, float px, float py, float width, float height) {
    super(surface, px, py, width, height);
    this.playX = assetManager().getImage("images/playx.png"); 
    this.playO = assetManager().getImage("images/playo.png");
    this.reset();
  }

  public boolean changeStatus(){
    if (this.cur_status == Player.PLAYX) {
      this.cur_status = Player.PLAYO;
    } else {
      this.cur_status = Player.PLAYX;
    }
    return true;
  }

  @Override
  public void update(float delta) {
    if (this.cur_status == this.pre_status) return;
    this.pre_status = this.cur_status;
    if (this.cur_status == Player.PLAYX) {
      this.surface.drawImage(this.playX, this.px, this.py);
    } else {
      this.surface.drawImage(this.playO, this.px, this.py);
    }
  }

  @Override
  public void reset() {
    this.pre_status = Player.PLAYX;
    this.cur_status = Player.PLAYX;
    this.surface.drawImage(this.playX, px, py);    
  }
}
