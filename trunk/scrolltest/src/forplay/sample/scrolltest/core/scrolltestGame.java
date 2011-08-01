/**
 * Copyright 2011 The ForPlay Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package forplay.sample.scrolltest.core;

import static forplay.core.ForPlay.*;

import forplay.core.ForPlay;
import forplay.core.Game;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;

import forplay.core.Mouse;

public class scrolltestGame implements Game, Mouse.Listener {
  GroupLayer peaLayer;

  ImageLayer imgLayer;
  @Override
  public void init() {
    // create and add background image layer
    Image bgImage = assetManager().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);  
    

    mouse().setListener(this);
  }

  @Override
  public void paint(float alpha) {
    // layers automatically paint themselves (and their children). The rootlayer
    // will paint itself, the background, and the pea group layer automatically
    // so no need to do anything here!
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public int updateRate() {
    return 25;
  }

  @Override
  public void onMouseDown(float x, float y, int button) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMouseUp(float x, float y, int button) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMouseMove(float x, float y) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMouseWheelScroll(float velocity) {
    if (Math.abs(velocity) == 1.0) {
      ForPlay.log().info(String.valueOf(velocity));  
    } else if (Math.floor(velocity) == velocity) {
      ForPlay.log().warn(String.valueOf(velocity));
    } else {
      ForPlay.log().error(String.valueOf(velocity));
    }
    
//    String imageName = "";
//    if (velocity < 0) {
//      imageName = "scrollup.png";
//    } else if (velocity > 0) {
//      imageName = "scrolldown.png";
//    } 
//    
//    
//    
//    Image img = assetManager().getImage("images/" + imageName);
//    imgLayer = graphics().createImageLayer(img);
//    
//    img.addCallback(new ResourceCallback<Image>() {
//      @Override
//      public void done(Image image) {
//        imgLayer.setOrigin(0, 0);
//        graphics().rootLayer().add(imgLayer);
//      }
//
//      @Override
//      public void error(Throwable err) {
//      }
//    });
  }
}
