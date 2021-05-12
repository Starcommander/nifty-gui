package de.lessvoid.nifty.examples.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventType;

public class JavafxMainFrame extends Application
{
  public static Pane root = new Pane();
  
  private int w;
  private int h;

    @Override
    public void start(Stage primaryStage) {
        String title = "Hello World!";
        List<String> args = getParameters().getRaw();
        if (args.size() != 3) { throw new IllegalStateException("Must have 3 Arguments for javafx frame: title width height"); }
        w = Integer.parseInt(args.get(1));
        h = Integer.parseInt(args.get(2));
//        root.setMinWidth(w);
//        root.setMinHeight(h);
        title = args.get(0);
        primaryStage.setTitle(title);
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
JavafxNiftyExampleLoader.runInit(null);
        
//        root.getChildren().add(btn);
         AnimationTimer t = new AnimationTimer(){
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
