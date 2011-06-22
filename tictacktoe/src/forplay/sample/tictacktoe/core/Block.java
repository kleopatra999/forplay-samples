package forplay.sample.tictacktoe.core;

import static forplay.core.ForPlay.assetManager;

import java.util.ArrayList;

import forplay.core.Image;
import forplay.core.Surface;

public class Block extends Entity {

  public final static int BLANK = 0;
  public final static int X = 1;
  public final static int O = 2;
  
  protected ArrayList<Image> img_list;
  private Player player;
  
  public Block(Surface surface, float px, float py, float width, float height) {
    super(surface, px, py, width, height);
    this.img_list = new ArrayList<Image>();
    this.img_list.add(assetManager().getImage("images/block.png"));
    this.img_list.add(assetManager().getImage("images/x.png"));
    this.img_list.add(assetManager().getImage("images/o.png"));
    this.reset();
  }
  
  public boolean changeStatus() {
    if(this.cur_status == Block.BLANK) {
      if (this.player.getStatus() == Player.PLAYX) {
        this.cur_status = Block.X; 
      } else if (this.player.getStatus() == Player.PLAYO){
        this.cur_status = Block.O;
      }
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public void update(float delta) {
    if (this.cur_status == this.pre_status) return;
    this.pre_status = this.cur_status;
    Image image = this.img_list.get(this.cur_status);
    this.surface.drawImage(image, px, py);
  }
  
  public void attachPlayer(Player player) {
    this.player = player;
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub
    this.surface.drawImage(this.img_list.get(0), px, py);
    this.pre_status = BLANK;
    this.cur_status = BLANK;    
  }
}