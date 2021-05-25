package de.lessvoid.nifty.examples.javafx;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.examples.NiftyExample;
import de.lessvoid.nifty.renderer.javafx.input.JavafxInputSystem;

import de.lessvoid.nifty.renderer.javafx.render.JavafxRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

import de.lessvoid.nifty.sound.javafx.JavafxSoundDevice;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JavafxNiftyExampleLoader {
  private static final Logger log = Logger.getLogger(JavafxNiftyExampleLoader.class.getName());
  private static final int FRAME_W = 1024;
  private static final int FRAME_H = 768;
  
  private static Nifty nifty;
  private static NiftyExample example;
  private static JavafxInputSystem inputSystem;
  
  public interface RenderLoopCallback {
    void process(@Nonnull Nifty nifty);
  }

  public static JavafxInputSystem getInputSystem()
  {
    if (inputSystem==null) { inputSystem = new JavafxInputSystem(); }
    return inputSystem;
  }
  /**
   * Easily run any {@link de.lessvoid.nifty.examples.NiftyExample}. Just instantiate your example and pass it to this
   * method. This method will use LWJGL to run your example, automatically creating & initializing an LWJGL application
   * for you before running the example.
   *
   * @param runExample The {@link de.lessvoid.nifty.examples.NiftyExample} to run.
   */
  public static void run (@Nonnull final NiftyExample runExample) {
    initNifty(getInputSystem());
    if (nifty==null) { throw new NullPointerException("Error init nifty!"); }
    example = runExample;
    String[] args = new String[3];
    args[0] = example.getTitle() + " JavaFX";
    args[1] = "" + FRAME_W;
    args[2] = "" + FRAME_H;
            
    javafx.application.Application.launch(JavafxMainFrame.class, args);
  }

  /**
   * Easily run any {@link de.lessvoid.nifty.examples.NiftyExample}. Just instantiate your example and pass it along
   * with your optional custom callback to this method. This method will use LWJGL to run your example, automatically
   * creating & initializing an LWJGL application for you before running the example.
   *
   * @param callback Provides access to the render loop by calling your implementation of the process method once at
   *                 the beginning of each frame. You can put any custom code here that needs to be executed every frame.
   *                 It also provides you access to the Nifty instance.
   */
  public static void runInit (@Nullable RenderLoopCallback callback, Pane pane) {
    try {
      getInputSystem().registerPane(pane);
      example.prepareStart(nifty);
      String mainXml = example.getMainXML();
      if (mainXml != null) {
        nifty.fromXml(mainXml, example.getStartScreen());
      } else {
        nifty.gotoScreen(example.getStartScreen());
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, "Unable to run Nifty example!", e);
    }
  }
  
  public static void runLoop(long time)
  {
      boolean done = false;
      boolean exit = nifty.update();
      if (!exit) {
        nifty.render(true);
      }
  }

  private static void initNifty (final JavafxInputSystem inputSystem) {
    try {
      log.info ("\n\nRunning in Javafx.\n\n");
      nifty = new Nifty(
              new JavafxRenderDevice(JavafxMainFrame.root, FRAME_W, FRAME_H) ,
//              new SoundDeviceNull(),
//new PaulsSoundsystemSoundDevice(paulscode.sound.libraries.LibraryJavaSound.class),
new JavafxSoundDevice(),
              inputSystem,
              new AccurateTimeProvider());
    } catch (Exception e) {
      log.severe(e.toString());
      log.severe("Unable to create Nifty!, exiting...");
    }
  }
}
