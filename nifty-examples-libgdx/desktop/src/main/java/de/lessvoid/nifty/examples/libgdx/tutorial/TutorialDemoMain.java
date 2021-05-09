package de.lessvoid.nifty.examples.libgdx.tutorial;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import de.lessvoid.nifty.examples.libgdx.LibgdxExampleApplication;
import de.lessvoid.nifty.examples.tutorial.TutorialExample;

/**
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 */
public class TutorialDemoMain {
  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.useVsync(true);
    config.setWindowedMode(1024, 768);
    config.setTitle("Nifty LibGDX Desktop Examples: Nifty Tutorial");
    config.setResizable(true);
    final int atlasWidth = 2048;
    final int atlasHeight = 2048;
    new Lwjgl3Application(new LibgdxExampleApplication(new TutorialExample(), atlasWidth, atlasHeight), config);
  }
}
