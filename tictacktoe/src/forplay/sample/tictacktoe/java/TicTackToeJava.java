package forplay.sample.tictacktoe.java;

import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;
import forplay.sample.tictacktoe.core.TicTackToeGame;

public class TicTackToeJava {
  public static void main(String[] args) {
    JavaAssetManager assets = JavaPlatform.register().assetManager();
    assets.setPathPrefix("src/forplay/sample/tictacktoe/resources");
    ForPlay.run(new TicTackToeGame());
  }
}
