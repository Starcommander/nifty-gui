package de.lessvoid.nifty.renderer.javafx.render;

import de.lessvoid.nifty.render.io.ImageLoader;
import de.lessvoid.nifty.render.io.ImageLoaderFactory;
import de.lessvoid.nifty.spi.render.MouseCursor;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;

public class JavafxMouseCursor implements MouseCursor {
  @Nonnull
  private static final Logger log = Logger.getLogger(JavafxMouseCursor.class.getName());

  public JavafxMouseCursor(
          @Nonnull final String cursorImageFilename,
          final int hotspotX,
          final int hotspotY,
          @Nonnull final NiftyResourceLoader resourceLoader) throws IOException
  {
  }

  @Override
  public void dispose() {
  }

  @Override
  public void enable() {
  }

  @Override
  public void disable() {
  }
}
