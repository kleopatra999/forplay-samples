package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.log;

import java.util.ArrayList;

import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;
import forplay.core.Surface;

public class Block extends Entity {

  public enum State {
    BLANK(0), X(1), O(2);
    private int val;

    int value() {
      return val;
    }

    State(int value) {
      this.val = value;
    }
  }

  public enum Player {
    X(0), O(1);
    private int val;

    int value() {
      return val;
    }

    Player(int value) {
      this.val = value;
    }
  }

  public static int WIDTH = 185;
  public static int HEIGHT = 185;

  public State currentState;
  public Player player;

  public Block(GroupLayer groupLayer, float px, float py) {
    super(groupLayer, px, py, WIDTH, HEIGHT);
    this.img_list = new Image[3];
    this.img_list[0] = assetManager().getImage("images/block.png");
    this.img_list[1] = assetManager().getImage("images/x.png");
    this.img_list[2] = assetManager().getImage("images/o.png");
    this.loadImage(img_list[0]);
    this.reset();
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
    if (player == Block.Player.X) {
      TicTacToeGame.drawPlayer(this.groupLayer, Block.Player.O);
    } else if (player == Block.Player.O) {
      TicTacToeGame.drawPlayer(this.groupLayer, Block.Player.X);
    }
  }

  public void changeState(State newState) {
    if (newState != this.currentState) {
      this.currentState = newState;
      this.loadImage(this.img_list[this.currentState.value()]);
    }
  }

  public void reset() {
    this.currentState = State.BLANK;
    this.loadImage(img_list[0]);
  }

}