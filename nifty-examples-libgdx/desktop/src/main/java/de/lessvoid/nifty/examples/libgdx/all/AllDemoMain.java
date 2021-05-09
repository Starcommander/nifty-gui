package de.lessvoid.nifty.examples.libgdx.all;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.lessvoid.nifty.examples.all.AllExamples;
import de.lessvoid.nifty.examples.libgdx.LibgdxExampleApplication;

/**
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 */
public class AllDemoMain {
  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.useVsync(true);
    config.setWindowedMode(1024, 768);
    config.setTitle("Nifty LibGDX Desktop Examples");
    config.setResizable(true);
    final int atlasWidth = 2048;
    final int atlasHeight = 2048;
    new Lwjgl3Application(new LibgdxExampleApplication(new AllExamples(), atlasWidth, atlasHeight), config);
  }
}
