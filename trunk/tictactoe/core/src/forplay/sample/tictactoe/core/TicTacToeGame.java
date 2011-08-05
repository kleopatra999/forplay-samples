package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.*;

import java.util.ArrayList;
import java.util.Iterator;

import forplay.core.Game;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.SurfaceLayer;
import forplay.core.GroupLayer;
import forplay.core.Surface;
import forplay.core.Pointer;
import forplay.core.Color;
import forplay.core.Pointer.Event;
import forplay.sample.tictactoe.core.Block.Player;

public class TicTacToeGame implements Game {
  final static int GAME_WIDTH = 800;
  final static int GAME_HEIGHT = 700;
  final static int MARGIN_TOP = 55;
  final static int MARGIN_LEFT = (GAME_WIDTH - Block.WIDTH * 3) / 2;;
  final static int SPAN = 5;

  private SurfaceLayer surfaceLayer;
  private GroupLayer groupLayer;
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

    groupLayer = graphics().createGroupLayer();
    graphics().rootLayer().add(groupLayer);
    
    this.block_list = new ArrayList<Block>();
    this.button = new Button(groupLayer, MARGIN_LEFT + SPAN, MARGIN_TOP - 50);
    this.result = new Result(groupLayer, MARGIN_LEFT + 20, MARGIN_TOP + Block.HEIGHT * 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int block_px = MARGIN_LEFT + Block.WIDTH * j;
        int block_py = MARGIN_TOP + Block.HEIGHT * i;
        Block block_entity = new Block(groupLayer, block_px, block_py);
        this.block_list.add(block_entity);
      }
    }
    
    TicTacToeGame.drawPlayer(groupLayer, Block.Player.X);

    pointer().setListener(new Pointer.Listener() {
      
      @Override
      public void onPointerStart(Event event) {
        if (!button.hitTest(event.x(), event.y())) {
          Iterator<Block> it = block_list.iterator();
          while (it.hasNext()) {
            Block block = it.next();
            if (block.hitTest(event.x(), event.y()) && block.currentState == Block.State.BLANK) {
              changeBlockState(block, steps);

              boolean isXPlayer = (steps % 2 == 1) ? true : false;
              if (checkWin(isXPlayer)) {
                Result.State resultState = isXPlayer ? Result.State.XWIN : Result.State.OWIN;
                result.changeState(resultState);
                reset();
              } else if (steps == 9) {
                result.changeState(Result.State.NA);
                reset();
              } else {
                steps++;
              }
            }
          }
        } else {
          reset();
        }
        
      }
      
      @Override
      public void onPointerEnd(Event event) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onPointerDrag(Event event) {
        // TODO Auto-generated method stub
        
      }
    });
  }

  static void drawPlayer(GroupLayer groupLayer, Block.Player player) {
    Image image = null;
    if (player == Player.X) {
      image = assetManager().getImage("images/playx.png");
    } else {
      image = assetManager().getImage("images/playo.png");
    }
    int player_px = TicTacToeGame.MARGIN_LEFT + TicTacToeGame.SPAN * 2 + Button.WIDTH;
    int player_py = TicTacToeGame.MARGIN_TOP - 50;
    ImageLayer imageLayer = graphics().createImageLayer();
    imageLayer.setImage(image);
    imageLayer.setTranslation(player_px, player_py);
    groupLayer.add(imageLayer);
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
    TicTacToeGame.drawPlayer(this.groupLayer, Block.Player.X);
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