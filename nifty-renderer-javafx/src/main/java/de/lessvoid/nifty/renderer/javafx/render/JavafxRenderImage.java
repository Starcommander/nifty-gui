package de.lessvoid.nifty.renderer.javafx.render;

import de.lessvoid.nifty.render.io.ImageLoader;
import de.lessvoid.nifty.render.io.ImageLoaderFactory;
import de.lessvoid.nifty.spi.render.RenderImage;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JavafxRenderImage implements RenderImage {
  @Nonnull
  private static final Logger log = Logger.getLogger(JavafxRenderImage.class.getName());
//  private int width;
//  private int height;
//  private int textureWidth;
//  private int textureHeight;
//  private int textureId;
  private Image image;
  private ImageView imageV;
public String fn; //Just for debug!!!

  @Nonnull
  public JavafxRenderImage (
          @Nonnull String filename,
          final boolean filterParam,
          @Nonnull final NiftyResourceLoader resourceLoader) {
    log.fine("Loading image: " + filename);
    this.fn = filename;
    ImageLoader loader = ImageLoaderFactory.createImageLoader(filename);
    InputStream imageStream = null;
    try {
      imageStream = resourceLoader.getResourceAsStream(filename);
      if (imageStream != null) {
//        ByteBuffer image = loader.loadAsByteBufferRGBA(imageStream);
//        image.rewind();
//        width = loader.getImageWidth();
//        height = loader.getImageHeight();
//        textureWidth = loader.getTextureWidth();
//        textureHeight = loader.getTextureHeight();
        image = new Image(imageStream);
        imageV = new ImageView(image);
      }
    } catch (Exception e) {
      log.log(Level.WARNING, "Could not load image from file: [" + filename + "]", e);
    } finally {
      if (imageStream != null) {
        try {
          imageStream.close();
        } catch (IOException e) {
          log.log(Level.INFO, "An error occurred while closing the InputStream used to load image: " + "[" + filename +
                  "].", e);
        }
      }
    }
  }

  @Override
  public int getWidth() {
    return (int)(image.getWidth()*imageV.getScaleX());
  }

  @Override
  public int getHeight() {
    return (int)(image.getHeight()*imageV.getScaleY());
  }
  
  public ImageView getImageView() { return imageV; }
  
  public ImageView createImageView() { return new ImageView(image); }

  @Override
  public void dispose() {
  }

}
