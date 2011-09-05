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

import java.util.Date;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.PlayN;
import playn.core.Mouse.ButtonEvent;
import playn.payments.core.InAppPayments;
import playn.payments.core.InAppPayments.PurchaseRequest;
import playn.payments.core.InAppPayments.PurchaseResponse;
import playn.payments.core.InAppPaymentsFactory;

public class TestIAPGame implements Game {

  private static String goodPurchaseJWT = null;
  private static String badPurchaseJWT = null;
  private InAppPayments inappPayments = InAppPaymentsFactory.payments();

  @Override
  public void init() {
    // create and add background image layer
    Image bgImage = assetManager().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    Date cal = new Date();
    String iat = String.valueOf(cal.getTime() / 1000);
    PlayN.log().info("iat:" + iat);

    String goodexp = String.valueOf(cal.getTime() / 1000 + 60000L);
    PlayN.log().info("goodexp:" + goodexp);
    String badexp = String.valueOf(cal.getTime() / 1000 - 60000L);
    PlayN.log().info("badexp:" + badexp);
    PurchaseRequest request = new PurchaseRequest.Imp("Gold Star",
        "A shining badge of distinction", "3.00", "USD", "some opaque data");

    getJWTFromServer(iat, goodexp, request, new Callback() {
      @Override
      public void setJWT(String JWT) {
        goodPurchaseJWT = JWT;
      }
    });

    getJWTFromServer(iat, badexp, request, new Callback() {
      @Override
      public void setJWT(String JWT) {
        badPurchaseJWT = JWT;
      }
    });

    // add a listener for pointer (mouse, touch) input
    mouse().setListener(new Mouse.Adapter() {
      @Override
      public void onMouseDown(ButtonEvent event) {

        inappPayments.setCallback(new InAppPayments.Adapter() {
          @Override
          public void successHandler(PurchaseResponse result) {
            PlayN.log().info("Big Congratulation: you get your products!!!");
            PlayN.log().info("jwt:" + result.jwt());
            PlayN.log().info("orderId:" + result.orderId());
            PlayN.log().info("request.name" + result.request().name());
            PlayN.log().info("request.description" + result.request().description());
            PlayN.log().info("request.currencycode" + result.request().currencyCode());
            PlayN.log().info("request.price" + result.request().price());
            PlayN.log().info("request.sellerData" + result.request().sellerData());
          }

          @Override
          public void failureHandler(PurchaseResponse result) {
            PlayN.log().warn("It is bad: you could not get your products!!!");
            PlayN.log().warn("jwt:" + badPurchaseJWT);
            PlayN.log().warn("request.name" + result.request().name());
            PlayN.log().warn("request.description" + result.request().description());
            PlayN.log().warn("request.currencycode" + result.request().currencyCode());
            PlayN.log().warn("request.price" + result.request().price());
            PlayN.log().warn("request.sellerData" + result.request().sellerData());
          }
        });

        goodPurchaseJWT = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIwODA3MTI4MjAwMTYxM" + 
         "Tc0Mjc3NyIsImF1ZCI6Ikdvb2dsZSIsInR5cCI6Imdvb2dsZS9" + 
         "wYXltZW50cy9pbmFwcC9pdGVtL3YxIiwiaWF0IjoxMzE1MjE3O" + 
         "DY0LCJleHAiOjEzMTUzMDQyNjQsInJlcXVlc3QiOnsiY3VycmV" + 
         "uY3lDb2RlIjoiVVNEIiwicHJpY2UiOiIzLjAwIiwibmFtZSI6I" + 
         "kdvbGQgU3RhciIsInNlbGxlckRhdGEiOiJzb21lIG9wYXF1ZSB" + 
         "kYXRhIiwiZGVzY3JpcHRpb24iOiJBIHNoaW5pbmcgYmFkZ2Ugb" + 
         "2YgZGlzdGluY3Rpb24ifX0.HnSVkyYXcQ8G0J3YYrIV5AMD2FQ" + 
         "2kWQKCjmf-e0u4IE";
        if (event.button() == Mouse.BUTTON_LEFT) {
          inappPayments.buy(goodPurchaseJWT);
        } else if (event.button() == Mouse.BUTTON_RIGHT) {
          inappPayments.buy(badPurchaseJWT);
        }
      }
    });

  }

  interface Callback {
    public void setJWT(String JWT);
  }

  private void getJWTFromServer(String iat, String exp, PurchaseRequest request,
      final Callback callback) {
    inappPayments.encodeJWT(iat, exp, request, new InAppPayments.EncodeJWTCallback() {
      @Override
      public void successHandler(String JWT) {
        callback.setJWT(JWT);
      }

      @Override
      public void failureHandler(String error) {
        PlayN.log().info(error);
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
