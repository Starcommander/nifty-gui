package de.lessvoid.nifty.spi.input;

import com.starcom.math.Point2i;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import de.lessvoid.nifty.NiftyInputConsumer;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;


/**
 * Port of <code>de.lessvoid.nifty.renderer.lwjgl.LwjglInputSystem</code>
 * to a nifty-core class.
 * 
 * @author Brian Groenke
 * @author Paul Kashofer Starcommander@github
 */
public abstract class InputSystemEventClass implements InputSystem {
  private final static Logger log = Logger.getLogger(InputSystemEventClass.class.getName());

  @Nonnull
  private final ConcurrentLinkedQueue<MouseInputEvent> mouseEventsOut = new ConcurrentLinkedQueue<MouseInputEvent>();
  @Nonnull
  private final ConcurrentLinkedQueue<KeyboardInputEvent> keyboardEventsOut = new ConcurrentLinkedQueue<KeyboardInputEvent>();
  
  @Override
  public void setResourceLoader(@Nonnull final NiftyResourceLoader resourceLoader) {
  }

  @Override
  public void forwardEvents(@Nonnull final NiftyInputConsumer inputEventConsumer) {
    log.fine("Forwarding events.");
//    mouseEventsOut.clear();
//    keyboardEventsOut.clear();

    pollEvents();
    
    KeyboardInputEvent nextEvent;
    while ((nextEvent = keyboardEventsOut.poll()) != null) {
      inputEventConsumer.processKeyboardEvent(nextEvent);
    }

    MouseInputEvent nextME;
    while ((nextME = mouseEventsOut.poll()) != null) {
      inputEventConsumer.processMouseEvent((int) nextME.mouseX, (int) nextME.mouseY, nextME.scrollY,
          nextME.button, nextME.buttonDown);
    }
  }
  
  public void queueKeyboardEvent(KeyboardInputEvent ev)
  {
    keyboardEventsOut.offer(ev);
  }
  
  public void queueMouseEvent(int xpos, int ypos)
  {
    log.fine("queueMouseEvent(x,y)");
    var me = new MouseInputEvent(xpos, ypos, 0, -1, false);
    mouseEventsOut.offer(me);
  }
  
  public void queueMouseEvent(final int button, final boolean buttonDown)
  {
    log.fine("queueMouseEvent(button,b)");
    Point2i mousePos = getMousePosition();
    var me = new MouseInputEvent(mousePos.x, mousePos.y, 0, button, buttonDown);
    mouseEventsOut.offer(me);
  }
  
  public void queueMouseEvent(final int scrollY)
  {
    log.fine("queueMouseEvent(scrollY)");
    Point2i mousePos = getMousePosition();
    var me = new MouseInputEvent(mousePos.x, mousePos.y, scrollY, 0, false);
    mouseEventsOut.offer(me);
  }
  
  public void clearEvents()
  {
    mouseEventsOut.clear();
    keyboardEventsOut.clear();
  }
  
  /** Request the mouse position on click and scroll events. **/
  public abstract Point2i getMousePosition();
  
  /** Used to poll events and queue them, for example cursor position or glfwPollEvents on Lwjgl.
    * <br>May also be empty **/
  public abstract void pollEvents();

  public static class MouseInputEvent {
    public float mouseX;
    public float mouseY;
    public int button;
    public int scrollY;
    public boolean buttonDown;

    MouseInputEvent(float mx, float my, int scrollY, int button, boolean buttonDown) {
      this.mouseX = mx;
      this.mouseY = my;
      this.button = button;
      this.scrollY = scrollY;
      this.buttonDown = buttonDown;
    }

    @Nonnull
    @Override
    public String toString() {
      return this.button + "=" + this.buttonDown + " at " + this.mouseX + "," + this.mouseY + " scroll:" + this.scrollY;
    }
  }
}
