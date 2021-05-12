package de.lessvoid.nifty.renderer.javafx.time;

import de.lessvoid.nifty.spi.time.TimeProvider;

/**
 * This time provider uses the timer that is provided by LWJGL.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public class JavafxTimeProvider implements TimeProvider {
  /**
   * The conversation factor from the time provided by the LWJGL time to the
   * time expected by Nifty.
   */
  private static final long CONVERSATION_FACTOR = 1000;

  @Override
  public long getMsTime() {
    return System.currentTimeMillis();
  }

}
