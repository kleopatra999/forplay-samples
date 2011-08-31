/**
 * Copyright 2011 The PlayN Authors
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
package com.test.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.InAppPayments.PurchaseResponse;
import playn.core.Pointer;
import playn.core.InAppPayments;

public class TestIAPGame implements Game {

  @Override
  public void init() {
    // create and add background image layer
    Image bgImage = assetManager().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    // add a listener for pointer (mouse, touch) input
    pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(Pointer.Event event) {
        InAppPayments inappPayments = inappPayments();
        inappPayments.setCallback(new InAppPayments.Callback() {
          @Override
          public void successHandler(String result) {
            // TODO Auto-generated method stub
            System.out.println(result);
          }

          @Override
          public void failureHandler(String result) {
            // TODO Auto-generated method stub
            System.out.println(result);
          }
        });
        String purchaseInformation = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIwODA3MTI4MjAwMTYxM"
            + "Tc0Mjc3NyIsImF1ZCI6Ikdvb2dsZSIsInR5cCI6Imdvb2dsZS9"
            + "wYXltZW50cy9pbmFwcC9pdGVtL3YxIiwiaWF0IjoxMzE0NzkxN"
            + "zQ2LCJleHAiOjEzMTQ4NzgxNDYsInJlcXVlc3QiOnsiY3VycmV"
            + "uY3lDb2RlIjoiVVNEIiwicHJpY2UiOiIzLjAwIiwibmFtZSI6I"
            + "kdvbGQgU3RhciIsInNlbGxlckRhdGEiOiJzb21lIG9wYXF1ZSB"
            + "kYXRhIiwiZGVzY3JpcHRpb24iOiJBIHNoaW5pbmcgYmFkZ2Ugb"
            + "2YgZGlzdGluY3Rpb24ifX0.udyaKWWfp2HImJrxMPjkkHjfcV-" 
            + "hZU4CqqRtP-03vkE";
        inappPayments.buy(purchaseInformation);
      }
    });
  }

  @Override
  public void paint(float alpha) {
    // the background layer automatically paints itself, so no custom painting
    // is needed by default
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public int updateRate() {
    return 25;
  }
}
