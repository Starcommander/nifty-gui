package de.lessvoid.nifty.examples.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;
import javafx.animation.AnimationTimer;

public class JavafxMainFrame extends Application
{
  public static Pane root = new Pane();
  
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        if (args.size() != 3) { throw new IllegalStateException("Must have 3 Arguments for javafx frame: title width height"); }
        int w = Integer.parseInt(args.get(1));
        int h = Integer.parseInt(args.get(2));
        String title = args.get(0);
        primaryStage.setTitle(title);
        JavafxNiftyExampleLoader.runInit(null, root);
        
        AnimationTimer t = new AnimationTimer()
        {
          @Override
          public void handle(long l)
          {
            JavafxNiftyExampleLoader.runLoop(l);
          }
        };
        t.start();

        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }
    
    void loop(long time){}

}
