package de.lessvoid.nifty.examples.javafx;

//import de.lessvoid.nifty.examples.allcontrols.AllControlsDemoStartScreen;
import de.lessvoid.nifty.examples.javafx.JavafxNiftyExampleLoader;
import de.lessvoid.nifty.examples.controls.ControlsDemoStartScreen;
import de.lessvoid.nifty.examples.helloworld.HelloWorldStartScreen;
import de.lessvoid.nifty.examples.helloworld.ResizeExample;
import de.lessvoid.nifty.examples.allcontrols.AllControlsDemoStartScreen;

public class JavafxExampleApp // implements de.lessvoid.nifty.spi.render.RenderImage
{
  public static void main(String args[])
  {
JavafxNiftyExampleLoader.run(new de.lessvoid.nifty.examples.all.AllExamples());
//    JavafxNiftyExampleLoader.run(new ControlsDemoStartScreen());
  }

//  @Override
//  public int getHeight() { return 50; }

//  @Override
//  public int getWidth() { return 50; }

//  @Override
//  public void dispose() {}
}
