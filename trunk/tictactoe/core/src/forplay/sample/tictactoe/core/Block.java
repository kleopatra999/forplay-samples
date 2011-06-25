package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;

import java.util.ArrayList;

import forplay.core.Image;
import forplay.core.Surface;

public class Block extends Entity {

  public enum State {
    BLANK(0), X(1), O(2);
    private int val;
    int value() {return val;}
    State(int value) {
      this.val = value;
    }
  }

  public enum Player {
    X(0), O(1);
    private int val;
    int value() {return val;}
    Player(int value) {
      this.val = value;
    }
  }

  public static int WIDTH = 185;
  public static int HEIGHT = 185;
  
  protected ArrayList<Image> img_list;
  public State currentState;
  public Player player;
 
  public Block(Surface surface, float px, float py) {
    super(surface, px, py, WIDTH, HEIGHT);
    this.img_list = new ArrayList<Image>();
    this.img_list.add(assetManager().getImage("images/block.png"));
    this.img_list.add(assetManager().getImage("images/x.png"));
    this.img_list.add(assetManager().getImage("images/o.png"));
    this.reset();
  }
  
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
    if (player == Block.Player.X) {
      TicTacToeGame.drawPlayer(surface, Block.Player.O);
    } else if (player == Block.Player.O){
      TicTacToeGame.drawPlayer(surface, Block.Player.X);
    }
    
  }
  
  public void changeState(State newState) {
    
    if (newState != this.currentState) {
      this.currentState = newState;
      Image image = this.img_list.get(this.currentState.value());
      this.surface.drawImage(image, px, py);
    }
  }

  public void reset() {
    // TODO Auto-generated method stub
    this.surface.drawImage(this.img_list.get(0), px, py);
    this.currentState = State.BLANK;    
  }
  

}