package forplay.sample.tictacktoe.html;

import forplay.core.ForPlay;
import forplay.html.HtmlAssetManager;
import forplay.html.HtmlGame;
import forplay.html.HtmlPlatform;
import forplay.sample.tictacktoe.core.TicTackToeGame;

public class TicTackToeHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlAssetManager assets = HtmlPlatform.register().assetManager();
    assets.setPathPrefix("tictacktoegame/");
    ForPlay.run(new TicTackToeGame());
    
  }
}
