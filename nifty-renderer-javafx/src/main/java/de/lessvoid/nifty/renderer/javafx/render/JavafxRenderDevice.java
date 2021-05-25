package de.lessvoid.nifty.renderer.javafx.render;

import de.lessvoid.nifty.render.BlendMode;
import de.lessvoid.nifty.spi.render.MouseCursor;
import de.lessvoid.nifty.spi.render.RenderDevice;
import de.lessvoid.nifty.spi.render.RenderFont;
import de.lessvoid.nifty.spi.render.RenderImage;
import de.lessvoid.nifty.spi.time.TimeProvider;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import de.lessvoid.nifty.tools.Color;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

import java.io.IOException;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JavafxRenderDevice implements RenderDevice {
  private static final Logger log = Logger.getLogger(JavafxRenderDevice.class.getName());
//  private final TimeProvider timeProvider = new AccurateTimeProvider();
  private NiftyResourceLoader resourceLoader;
//  private int viewportWidth = -1;
//  private int viewportHeight = -1;
//  private long time;
//  private long frames;
  private boolean displayFPS = false;
//  private boolean logFPS = false;
  private RenderFont fpsFont;
  @Nonnull
  private final Pane pane;
  private final int initW;
  private final int initH;
//
//  // we keep track of which GL states we've already set to make sure we don't set
//  // the same state twice.
//  private boolean currentTexturing = true;
//  @Nullable
//  private BlendMode currentBlendMode = null;
//  private boolean currentClipping = false;
//  private int currentClippingX0 = 0;
//  private int currentClippingY0 = 0;
//  private int currentClippingX1 = 0;
//  private int currentClippingY1 = 0;
//
//  @Nonnull
//  private StringBuilder buffer = new StringBuilder();
//  private int quadCount;
//  private int glyphCount;
//  @Nullable
  private MouseCursor mouseCursor;

  /**
   * The standard constructor. You'll use this in production code. Using this
   * constructor will configure the RenderDevice to not log FPS on System.out.
   */
  public JavafxRenderDevice(Pane pane, int initW, int initH) {
    this.pane = pane;
    this.initW = initW;
    this.initH = initH;
    
//    time = timeProvider.getMsTime();
//    frames = 0;
  }

  @Override
  public void setResourceLoader(@Nonnull final NiftyResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;

    if (this.displayFPS) {
      fpsFont = createFont("fps.fnt");
    }
  }

  /**
   * Get Width.
   *
   * @return width of display mode
   */
  @Override
  public int getWidth() {
    var scene = pane.getScene();
    if (scene == null) { return initW; }
    return (int)scene.getWidth();
  }

  /**
   * Get Height.
   *
   * @return height of display mode
   */
  @Override
  public int getHeight() {
    var scene = pane.getScene();
    if (scene == null) { return initH; }
    return (int)scene.getHeight();
  }

  @Override
  public void beginFrame() {
  }

  @Override
  public void endFrame() {
  }

  @Override
  public void clear() {
    pane.getChildren().clear();
  }

  /**
   * Create a new RenderImage.
   *
   * @param filename     filename
   * @param filterLinear linear filter the image
   * @return RenderImage
   */
  @Override
  @Nonnull
  public RenderImage createImage(@Nonnull final String filename, final boolean filterLinear) {
    return new JavafxRenderImage(filename, filterLinear, resourceLoader);
  }

  /**
   * Create a new RenderFont.
   *
   * @param filename filename
   * @return RenderFont
   */
  @Override
  @Nonnull
  public RenderFont createFont(@Nonnull final String filename) {
    return new JavafxRenderFont(filename, resourceLoader);
  }

  /**
   * Render a quad.
   *
   * @param x      x
   * @param y      y
   * @param width  width
   * @param height height
   * @param color  color
   */
  @Override
  public void renderQuad(final int x, final int y, final int width, final int height, @Nonnull final Color color) {
    log.fine("renderQuad()");

    Rectangle r = new Rectangle();
    r.setX(x);
    r.setY(y);
    r.setWidth(width);
    r.setHeight(height);
//    r.setArcWidth(5);
//    r.setArcHeight(5);
    r.setOpacity(1.0);
//    r.setStrokeWidth(line_thick);
    r.setFill(new javafx.scene.paint.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
//    r.setStroke(Frame.color);
    pane.getChildren().add(r);
  }

  @Override
  public void renderQuad(
      final int x,
      final int y,
      final int width,
      final int height,
      @Nonnull final Color topLeft,
      @Nonnull final Color topRight,
      @Nonnull final Color bottomRight,
      @Nonnull final Color bottomLeft) {
    log.fine("renderQuad2()");

    renderQuad(x, y, width, height, topLeft); //TODO: Different colors!
  }

  /**
   * Render the image using the given Box to specify the render attributes.
   *
   * @param x      x
   * @param y      y
   * @param width  width
   * @param height height
   * @param color  color
   * @param scale  scale
   */
  @Override
  public void renderImage(
      @Nonnull final RenderImage image,
      final int x,
      final int y,
      final int width,
      final int height,
      @Nonnull final Color color,
      final float scale) {
    log.fine("renderImage()");
    
    JavafxRenderImage img = (JavafxRenderImage)image;
    var imgView = img.getImageView();
    if (pane.getChildren().contains(imgView))
    {
      imgView = img.createImageView();
    }
    imgView.setScaleX(scale);
    imgView.setScaleY(scale);
    imgView.setX(x);
    imgView.setY(y);
    
    //TODO: Width and Height
    
    pane.getChildren().add(imgView);
//System.out.println("JavafxRenderDevice.renderImage() w=" + width + " h=" + height + " x=" + x + " y=" + y + " s=" + scale);

  }

  /**
   * Render sub image.
   *
   * @param x     x
   * @param y     y
   * @param w     w
   * @param h     h
   * @param srcX  x
   * @param srcY  y
   * @param srcW  w
   * @param srcH  h
   * @param color color
   */
  @Override
  public void renderImage(
      @Nonnull final RenderImage image,
      final int x,
      final int y,
      final int w,
      final int h,
      final int srcX,
      final int srcY,
      final int srcW,
      final int srcH,
      @Nonnull final Color color,
      final float scale,
      final int centerX,
      final int centerY) {
    log.fine("renderImage2()");
//    System.out.println("renderImage2(" + x + "," + y + ")" +srcX + "/" + srcY + ": " + ((JavafxRenderImage)image).fn);
    JavafxRenderImage img = (JavafxRenderImage)image;
    var imgView = img.getImageView();
    if (pane.getChildren().contains(imgView))
    {
      imgView = img.createImageView();
    }
    float scaleX = 1;
    float scaleY = 1;
    int xDiff = 0;
    int yDiff = 0;
    if (srcW>0 && w>0 && srcW<w) { scaleX = w/srcW; xDiff = (w-srcW)/2; }
    if (srcH>0 && h>0 && srcH<h) { scaleY = h/srcH; yDiff = (h-srcH)/2; }
    imgView.setScaleX(scale * scaleX);
    imgView.setScaleY(scale * scaleY);
    imgView.setX(x + xDiff);
    imgView.setY(y + yDiff);
    
    imgView.setViewport(new Rectangle2D(srcX,srcY,srcW,srcH));
    
    //TODO: CenterX and CenterY
    
//    if (!pane.getChildren().contains(imgView))
//    {
      pane.getChildren().add(imgView);
//    }

  }

  /**
   * render the text.
   */
  @Override
  public void renderFont(
      @Nonnull final RenderFont font,
      @Nonnull final String text,
      final int x,
      final int y,
      @Nonnull final Color color,
      final float fontSizeX,
      final float fontSizeY) {
    log.fine("renderFont()");
    
    JavafxRenderFont jf = (JavafxRenderFont)font;
    Text t = new Text(text);
    t.setFont(jf.getFont());
    t.setX(x);
    t.setY(y + (jf.getHeight()/2)); //TODO: Howto find y?
    t.setFill(new javafx.scene.paint.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    pane.getChildren().add(t);
    
    //TODO: fontSizeX and fontSizeY
  }

  /**
   * Enable clipping to the given region.
   *
   * @param x0 x0
   * @param y0 y0
   * @param x1 x1
   * @param y1 y1
   */
  @Override
  public void enableClip(final int x0, final int y0, final int x1, final int y1) {
    log.fine("enableClip()");

    //TODO: Enable clip.
  }

  /**
   * Disable Clip.
   */
  @Override
  public void disableClip() {
    log.fine("disableClip()");

    //TODO: Disable clip.
  }

  @Override
  public void setBlendMode(@Nonnull final BlendMode renderMode) {
    log.fine("setBlendMode()");

    //TODO: SetBlendMode
  }

  @Override
  @Nonnull
  public MouseCursor createMouseCursor(
      @Nonnull final String filename,
      final int hotspotX,
      final int hotspotY) throws IOException {
    return new JavafxMouseCursor(filename, hotspotX, hotspotY, resourceLoader);
  }

  @Override
  public void enableMouseCursor(@Nonnull final MouseCursor mouseCursor) {
    this.mouseCursor = mouseCursor;
    mouseCursor.enable();
  }

  @Override
  public void disableMouseCursor() {
    if (mouseCursor != null) {
      mouseCursor.disable();
    }
  }
}
