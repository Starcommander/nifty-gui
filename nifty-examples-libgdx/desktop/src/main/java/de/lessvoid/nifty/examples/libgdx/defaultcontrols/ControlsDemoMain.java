package de.lessvoid.nifty.examples.libgdx.defaultcontrols;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.lessvoid.nifty.examples.defaultcontrols.ControlsDemo;
import de.lessvoid.nifty.examples.libgdx.LibgdxExampleApplication;
import de.lessvoid.nifty.examples.libgdx.resolution.GdxResolutionControl;
import de.lessvoid.nifty.examples.libgdx.resolution.GdxResolutionControl.Resolution;

/**
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 */
public class ControlsDemoMain {
  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.useVsync(true);
    config.setWindowedMode(1024, 768);
    config.setTitle("Nifty LibGDX Desktop Examples: Default Controls");
    config.setResizable(true);
    final int atlasWidth = 2048;
    final int atlasHeight = 2048;
    new Lwjgl3Application(new LibgdxExampleApplication(new ControlsDemo<Resolution>(new GdxResolutionControl()),
            atlasWidth, atlasHeight), config);
  }
}
