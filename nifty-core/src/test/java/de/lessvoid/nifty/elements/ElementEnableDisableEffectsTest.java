package de.lessvoid.nifty.elements;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.FocusHandler;
import de.lessvoid.nifty.elements.render.ElementRenderer;
import de.lessvoid.nifty.loaderv2.types.ElementType;
import de.lessvoid.nifty.spi.time.TimeProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class ElementEnableDisableEffectsTest {
  private Nifty niftyMock;
  private Element e1;
  private Element e2;

  @Before
  public void before() throws Exception {
    niftyMock = createMock(Nifty.class);
    expect(niftyMock.getAlternateKey()).andReturn(null).times(2);
    replay(niftyMock);

    e1 = createMockBuilder(Element.class)
        .withConstructor(Nifty.class, ElementType.class, String.class, Element.class, FocusHandler.class, boolean.class, TimeProvider.class, ElementRenderer[].class)
        .withArgs(niftyMock, null, "e1", null, null, false, null, null)
        .addMockedMethod(Element.class.getDeclaredMethod("enableEffect"))
        .addMockedMethod(Element.class.getDeclaredMethod("disableEffect"))
        .addMockedMethod(Element.class.getDeclaredMethod("disableFocus"))
        .createMock();

    e2 = createMockBuilder(Element.class)
        .withConstructor(Nifty.class, ElementType.class, String.class, Element.class, FocusHandler.class, boolean.class, TimeProvider.class, ElementRenderer[].class)
        .withArgs(niftyMock, null, "e1", null, null, false, null, null)
        .addMockedMethod(Element.class.getDeclaredMethod("enableEffect"))
        .addMockedMethod(Element.class.getDeclaredMethod("disableEffect"))
        .addMockedMethod(Element.class.getDeclaredMethod("disableFocus"))
        .createMock();
    e1.addChild(e2);
  }

  @After
  public void after() {
    verify(niftyMock);
    verify(e1);
    verify(e2);
  }

  @Test
  public void testSimpleDisable() {
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e2.disable();
  }

  @Test
  public void testDisableTwice() {
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e2.disable();
    e2.disable();
  }

  @Test
  public void testDisableTwiceAndThenEnable() {
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    e2.enableEffect();
    replay(e2);

    e2.disable();
    e2.disable();
    e2.enable();
    e2.enable();
  }

  @Test
  public void testSimpleEnable() {
    replay(e1);
    replay(e2);

    e2.enable();
  }

  @Test
  public void testEnableTwice() {
    replay(e1);
    replay(e2);

    e2.enable();
    e2.enable();
  }

  @Test
  public void testEnableTwiceAndThenDisable() {
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e2.enable();
    e2.enable();
    e2.disable();
  }

  @Test
  public void testParentDisable() {
    e1.disableFocus();
    e1.disableEffect();
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e1.disable();
  }

  @Test
  public void testParentDisableWithDisabledChild() {
    e1.disableFocus();
    e1.disableEffect();
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e2.disable();
    e1.disable();
  }

  @Test
  public void testParentDisableWithDisabledChildAndParentEnable() {
    e1.disableFocus();
    e1.disableEffect();
    e1.enableEffect();
    replay(e1);

    e2.disableFocus();
    e2.disableEffect();
    replay(e2);

    e2.disable();
    e1.disable();
    e1.enable();
  }

}
