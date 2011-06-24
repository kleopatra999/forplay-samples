package forplay.sample.tictactoe.core;

import static forplay.core.ForPlay.*;

import java.util.ArrayList;
import java.util.Iterator;


import forplay.core.Game;
import forplay.core.SurfaceLayer;
import forplay.core.Surface;
import forplay.core.Pointer;
import forplay.core.Color;

public class TicTacToeGame implements Game, Pointer.Listener {
  final static int GAME_WIDTH = 800;
  final static int GAME_HEIGHT = 700;
  private SurfaceLayer surfaceLayer;
  private Player player;
  private Button button;
  private Result result;
  private ArrayList<Entity> block_list;
  private int steps;
  
  @Override
  public void init() {
    // TODO Auto-generated method stub
    graphics().setSize(GAME_WIDTH, GAME_HEIGHT);
    this.steps = 0;
    surfaceLayer = graphics().createSurfaceLayer(GAME_WIDTH, GAME_HEIGHT);
    Surface surface = surfaceLayer.surface();
    graphics().rootLayer().add(surfaceLayer);

    surface.setFillColor(Color.rgb(255, 255, 255));
    surface.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

    int block_width = 185;
    int block_height = 185;
    int margin_left = (GAME_WIDTH - block_width * 3) / 2;
    int margin_top = 55;
    this.block_list = new ArrayList<Entity>();
    int button_px = margin_left + 5;
    int button_py = margin_top - 50;
    this.button = new Button(surface, button_px, button_py, 100, 40);
    this.player = new Player(surface, button_px + 5 + 100, button_py, 200, 40);
    
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int block_px = margin_left + block_width * j;
        int block_py = margin_top + block_height * i;
        Block block_entity = new Block(surface, block_px, block_py, block_width, block_height);
        block_entity.attachPlayer(this.player);
        this.block_list.add(block_entity);
      }
    }
    
    this.result = new Result(surface, margin_left + 20, margin_top + block_height * 3, 200, 50);
    this.result.attachPlayer(this.player);
    pointer().setListener(this);
  }

  private boolean compareStatus(Entity first, Entity second, Entity third) {
    if ((first.getStatus() != Block.BLANK) && (first.getStatus() == second.getStatus())
        && (first.getStatus() == third.getStatus())) {
      return true;
    }
    return false;
  }

  private boolean computeWinner() {
    for (int row = 0; row < 3; row++) {
      if (compareStatus(this.block_list.get(row * 3), this.block_list.get(row * 3 + 1),
          this.block_list.get(row * 3 + 2))) {
        return true;
      }
    }
    for (int col = 0; col < 3; col++) {
      if (compareStatus(this.block_list.get(col), this.block_list.get(col + 3),
          this.block_list.get(col + 6))) {
        return true;
      }
    }

    if (compareStatus(this.block_list.get(0), this.block_list.get(4), this.block_list.get(8)))
      return true;

    if (compareStatus(this.block_list.get(2), this.block_list.get(4), this.block_list.get(6)))
      return true;

    return false;
  }

  private void reset(){
    this.player.reset();
    Iterator<Entity> it = this.block_list.iterator();
    while (it.hasNext()) {
      Entity entity = it.next();
      entity.reset();
    }
   this.steps = 0; 
  }
  @Override
  public void onPointerStart(float x, float y) {
    if (!this.button.hitTest(x, y)) {
      Iterator<Entity> it = this.block_list.iterator();
      while (it.hasNext()) {
        Entity entity = it.next();
        if (entity.hitTest(x, y)) {
          if (entity.changeStatus()) {
            this.steps ++;
            if (this.computeWinner()) {
              this.result.changeStatus();
              reset();
            } else if (this.steps == 9) {
              this.result.reset();
              reset();
            } else {
              this.player.changeStatus();
            }
          }
          return;
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
    this.button.update(delta);
    this.player.update(delta);
    this.result.update(delta);
    Iterator<Entity> it = this.block_list.iterator();
    while (it.hasNext()) {
      Entity entity = it.next();
      entity.update(delta);
    }
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