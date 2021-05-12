package de.lessvoid.nifty.renderer.javafx.render;

import de.lessvoid.nifty.spi.render.RenderFont;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

import javax.annotation.Nonnull;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class JavafxRenderFont implements RenderFont {
  @Nonnull
  private final Font defaultFont;

  public JavafxRenderFont(@Nonnull final String name, @Nonnull final NiftyResourceLoader resourceLoader) {
    defaultFont = new Font(name, 16.0);
    //text = new Text(name,font).getLayoutBounds().getWidth();
  }

  @Override
  public int getHeight() {
    Text t = new Text("Hello");
    t.setFont(defaultFont);
    return (int)t.getLayoutBounds().getWidth();
  }

  @Override
  public int getWidth(@Nonnull final String text) {
    Text t = new Text(text);
    t.setFont(defaultFont);
    return (int)t.getLayoutBounds().getWidth();
  }

  @Override
  public int getWidth(@Nonnull final String text, final float size) {
    Text t = new Text(text);
    t.setFont(new Font(defaultFont.getName(),size));
    return (int)t.getLayoutBounds().getWidth();
  }

  @Override
  public int getCharacterAdvance(final char currentCharacter, final char nextCharacter, final float size) {
    return -1;
  }

  @Nonnull
  public Font getFont() {
    return defaultFont;
  }

  @Override
  public void dispose() {
  }
}
