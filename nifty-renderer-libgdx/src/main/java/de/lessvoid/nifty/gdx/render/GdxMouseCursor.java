package de.lessvoid.nifty.gdx.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.Cursor;
import de.lessvoid.nifty.spi.render.MouseCursor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;

/**
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 */
public final class GdxMouseCursor implements MouseCursor {
  @Nonnull
  private static final Logger log = Logger.getLogger(GdxMouseCursor.class.getName());
  @Nonnull
  private final GdxImage cursorImage;
  private final int hotspotX;
  private final int hotspotY;
  private Cursor cursor;

  /**
   * @param cursorImage The pre-loaded cursor image.
   * @param hotspotX The x location of the pixel in the cursor image that actually clicks
   * @param hotspotY The y location of the pixel in the cursor image that actually clicks
   */
  public GdxMouseCursor(@Nonnull GdxImage cursorImage, final int hotspotX, final int hotspotY) {
    this.cursorImage = cursorImage;
    this.hotspotX = hotspotX;
    this.hotspotY = hotspotY;
  }

  /**
   * Enables (shows) the mouse cursor image specified in {@link #GdxMouseCursor(GdxImage, int, int)}. Replaces (hides)
   * the system mouse cursor image.
   */
  @Override
  public void enable() {
    try {
      if (cursorImage.hasPixmap()) {
        cursor = Gdx.graphics.newCursor(cursorImage.getPixmap(), hotspotX, hotspotY);
        Gdx.graphics.setCursor(cursor);
      }
    } catch (GdxRuntimeException e) {
      log.log(Level.SEVERE, "Applying the mouse cursor failed!", e);
    }
  }

  /**
   * Disables (hides) the mouse cursor image specified in {@link #GdxMouseCursor(GdxImage, int, int)}. Restores (shows)
   * the system mouse cursor image.
   */
  @Override
  public void disable() {
    try {
      Gdx.graphics.setCursor(Gdx.graphics.newCursor(null, hotspotX, hotspotY));
    } catch (NullPointerException e) {} // On lwjgl3 this is not necessary, and causes this NullPointer.
    if (cursor!=null) { cursor.dispose(); }
    cursor = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if (cursor!=null) { cursor.dispose(); }
    cursor = null;
  }
}
