package de.lessvoid.nifty.examples.lwjgl3;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.examples.LoggerShortFormat;
import de.lessvoid.nifty.examples.NiftyExample;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.render.batch.BatchRenderDevice;
import de.lessvoid.nifty.render.batch.CheckGL;
import de.lessvoid.nifty.render.batch.spi.GL;
import de.lessvoid.nifty.renderer.lwjgl3.input.Lwjgl3InputSystem;
import de.lessvoid.nifty.renderer.lwjgl3.render.Lwjgl3BatchRenderBackendCoreProfileFactory;
import de.lessvoid.nifty.renderer.lwjgl3.render.Lwjgl3BatchRenderBackendFactory;
import de.lessvoid.nifty.renderer.lwjgl3.render.Lwjgl3GL;
import de.lessvoid.nifty.sound.openal.OpenALSoundDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import org.lwjgl.BufferUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

/**
 * Loads & runs any {@link de.lessvoid.nifty.examples.NiftyExample} using Nifty and LWJGL3.
 *
 * @author void
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 * @author Paul Kashofer &lt;starcommander@github&gt;
 */
public class NiftyExampleLoaderLWJGL3 {
  private static final Logger log = Logger.getLogger(NiftyExampleLoaderLWJGL3.class.getName());
  private static final boolean USE_CORE_PROFILE = true;
  private static final int WIDTH = 1024;
  private static final int HEIGHT = 768;
  private static Lwjgl3InputSystem inputSystem;
  private static Nifty nifty;
  private static LWJGL3Application app = new LWJGL3Application();

  public interface RenderLoopCallback {
    void process(@Nonnull Nifty nifty);
  }

  /**
   * Easily run any {@link de.lessvoid.nifty.examples.NiftyExample}. Just instantiate your example and pass it to this
   * method. This method will use LWJGL to run your example, automatically creating & initializing an LWJGL application
   * for you before running the example.
   *
   * @param example The {@link de.lessvoid.nifty.examples.NiftyExample} to run.
   */
  public static void run (@Nonnull final NiftyExample example) {
    run (example, null);
  }

  /**
   * Easily run any {@link de.lessvoid.nifty.examples.NiftyExample}. Just instantiate your example and pass it along
   * with your optional custom callback to this method. This method will use LWJGL to run your example, automatically
   * creating & initializing an LWJGL application for you before running the example.
   *
   * @param example The {@link de.lessvoid.nifty.examples.NiftyExample} to run.
   * @param callback Provides access to the render loop by calling your implementation of the process method once at
   *                 the beginning of each frame. You can put any custom code here that needs to be executed every frame.
   *                 It also provides you access to the Nifty instance.
   */
  public static void run (@Nonnull final NiftyExample example, @Nullable RenderLoopCallback callback) {
    long winID = app.init(example.getTitle());
    if (!initSubSystems(winID)) { shutDown(); throw new IllegalStateException("Cannot init subsystems."); }

    try {
      example.prepareStart(nifty);
      if (example.getMainXML() != null) {
        nifty.fromXml(example.getMainXML(), example.getStartScreen());
      } else {
        nifty.gotoScreen(example.getStartScreen());
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, "Unable to run Nifty example!", e);
    }

    app.loop(nifty, callback);
//render(nifty, callback);
    shutDown();
  }

  private static boolean initSubSystems(long winID) {
    try {
      LoggerShortFormat.intialize();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return initInput(winID) && initNifty(inputSystem, winID);
  }

  private static boolean initInput(long winID) {
    try {
      inputSystem = new Lwjgl3InputSystem(winID);
      inputSystem.startup();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      log.warning("Unable to create keyboard!, exiting...");
      return false;
    }
  }

  private static boolean initNifty (final Lwjgl3InputSystem inputSystem, long winID) {
    try {
      log.info ("\n\nRunning in OpenGL " + (USE_CORE_PROFILE ? "Core Profile Mode (3.2+)" : "Compatibility Mode (1.2)") + ".\n\n");
      nifty = new Nifty(
              new BatchRenderDevice(USE_CORE_PROFILE ? Lwjgl3BatchRenderBackendCoreProfileFactory.create(winID) : Lwjgl3BatchRenderBackendFactory.create(winID)),
              new OpenALSoundDevice(),
              inputSystem,
              new AccurateTimeProvider());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      log.warning("Unable to create Nifty!, exiting...");
      return false;
    }
  }

  private static void shutDown() {
    inputSystem.shutdown();
    app.cleanup();
    System.exit(0);
  }
}
