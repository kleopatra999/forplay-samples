package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.*;

import java.util.ArrayList;
import java.util.Iterator;

import forplay.core.Game;
import forplay.core.Image;
import forplay.core.SurfaceLayer;
import forplay.core.Surface;
import forplay.core.Pointer;
import forplay.core.Color;
import forplay.sample.tictactoe.core.Block.Player;

public class TicTacToeGame implements Game, Pointer.Listener {
  final static int GAME_WIDTH = 800;
  final static int GAME_HEIGHT = 700;
  final static int MARGIN_TOP = 55;
  final static int MARGIN_LEFT = (GAME_WIDTH - Block.WIDTH * 3) / 2;;
  final static int SPAN = 5;

  private SurfaceLayer surfaceLayer;
  private Button button;
  private Result result;
  private ArrayList<Block> block_list;
  private int steps;

  @Override
  public void init() {
    this.steps = 1; 
    graphics().setSize(GAME_WIDTH, GAME_HEIGHT);
    surfaceLayer = graphics().createSurfaceLayer(GAME_WIDTH, GAME_HEIGHT);
    Surface surface = surfaceLayer.surface();
    graphics().rootLayer().add(surfaceLayer);

    surface.setFillColor(Color.rgb(255, 255, 255));
    surface.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

    this.block_list = new ArrayList<Block>();
    this.button = new Button(surface, MARGIN_LEFT + SPAN, MARGIN_TOP - 50);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int block_px = MARGIN_LEFT + Block.WIDTH * j;
        int block_py = MARGIN_TOP + Block.HEIGHT * i;
        Block block_entity = new Block(surface, block_px, block_py);
        this.block_list.add(block_entity);
      }
    }
    this.result = new Result(surface, MARGIN_LEFT + 20, MARGIN_TOP + Block.HEIGHT * 3);
    TicTacToeGame.drawPlayer(surface, Block.Player.X);

    pointer().setListener(this);
  }

  static void drawPlayer(Surface surface, Block.Player player) {
    Image image = null;
    if (player == Player.X) {
      image = assetManager().getImage("images/playx.png");
    } else {
      image = assetManager().getImage("images/playo.png");
    }
    int player_px = TicTacToeGame.MARGIN_LEFT + TicTacToeGame.SPAN * 2 + Button.WIDTH;
    int player_py = TicTacToeGame.MARGIN_TOP - 50;
    surface.drawImage(image, player_px, player_py);
  }

  private boolean compareStatus(Block first, Block second, Block third, boolean isXPlayer) {
    if ((first.currentState != Block.State.BLANK) && (first.currentState == second.currentState)
        && (first.currentState == third.currentState)) {
      if (isXPlayer & first.currentState == Block.State.X) {
        return true;
      } else if (first.currentState == Block.State.O) {
        return true;
      }
    }
    return false;
  }

  private boolean checkWin(boolean isXPlayer) {
    for (int row = 0; row < 3; row++) {
      if (compareStatus(this.block_list.get(row * 3), this.block_list.get(row * 3 + 1),
          this.block_list.get(row * 3 + 2), isXPlayer)) {
        return true;
      }
    }
    for (int col = 0; col < 3; col++) {
      if (compareStatus(this.block_list.get(col), this.block_list.get(col + 3),
          this.block_list.get(col + 6), isXPlayer)) {
        return true;
      }
    }

    if (compareStatus(this.block_list.get(0), this.block_list.get(4), this.block_list.get(8),
        isXPlayer))
      return true;

    if (compareStatus(this.block_list.get(2), this.block_list.get(4), this.block_list.get(6),
        isXPlayer))
      return true;

    return false;
  }

  private void reset() {
    Iterator<Block> it = this.block_list.iterator();
    while (it.hasNext()) {
      Block block = it.next();
      block.reset();
    }
    TicTacToeGame.drawPlayer(this.surfaceLayer.surface(), Block.Player.X);
    this.steps = 1;
  }

  private void changeBlockState(Block block, int steps) {
    Block.Player player;
    if (steps % 2 == 0) {
      player = Block.Player.O;
      block.changeState(Block.State.O);
    } else {
      player = Block.Player.X;
      block.changeState(Block.State.X);
    }
    block.setPlayer(player);

  }

  @Override
  public void onPointerStart(float x, float y) {
    if (!this.button.hitTest(x, y)) {
      Iterator<Block> it = this.block_list.iterator();
      while (it.hasNext()) {
        Block block = it.next();
        if (block.hitTest(x, y) && block.currentState == Block.State.BLANK) {
          changeBlockState(block, steps);

          boolean isXPlayer = (this.steps % 2 == 1) ? true : false;
          if (this.checkWin(isXPlayer)) {
            Result.State resultState = isXPlayer ? Result.State.XWIN : Result.State.OWIN;
            this.result.changeState(resultState);
            reset();
          } else if (this.steps == 9) {
            reset();
          } else {
            this.steps++;
          }
        }
      }
    } else {
      reset();
    }
  }

  @Override
  public void onPointerEnd(float x, float y) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onPointerDrag(float x, float y) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
  }

  @Override
  public void paint(float alpha) {
    // TODO Auto-generated method stub

  }

  @Override
  public int updateRate() {
    // TODO Auto-generated method stub
    return 25;
  }

}