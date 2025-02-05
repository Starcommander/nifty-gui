package de.lessvoid.nifty.renderer.javafx.input;

/**
 *
 * @author Joseph
 */

import com.starcom.math.Point2i;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import de.lessvoid.nifty.spi.input.InputSystemEventClass;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class JavafxInputSystem extends InputSystemEventClass {
  private final static Logger log = Logger.getLogger(JavafxInputSystem.class.getName());
  private final Point2i mousePos = new Point2i();

  public void registerPane(Pane pane)
  {
    pane.setOnMouseMoved((ev) -> queueMouseMove((int)ev.getX(), (int)ev.getY()));
    pane.setOnMousePressed((ev) -> queueMouseClick(ev, true));
    pane.setOnMouseReleased((ev) -> queueMouseClick(ev, false));
    pane.setOnMouseDragged((ev) -> queueMouseMove((int)ev.getX(), (int)ev.getY()));
    pane.setOnScroll((ev) -> queueMouseEvent((int)(ev.getDeltaY()/10.0f)));
    pane.setFocusTraversable(true);
    pane.requestFocus();
    pane.addEventFilter(MouseEvent.ANY, (e) -> pane.requestFocus());
    pane.setOnKeyPressed((ev) -> queueKey(ev, true));
    pane.setOnKeyReleased((ev) -> queueKey(ev, false));
    pane.setOnKeyTyped((ev) -> queueKey(ev, null));
  }
  
  private void queueMouseMove(int mouseX, int mouseY)
  {
    queueMouseEvent(mouseX,mouseY);
    this.mousePos.x = mouseX;
    this.mousePos.y = mouseY;
  }
  
  private void queueMouseClick(MouseEvent ev, boolean down)
  {
    this.mousePos.x = (int)ev.getX();
    this.mousePos.y = (int)ev.getY();
    var mb = ev.getButton();
    if (mb == MouseButton.PRIMARY) { queueMouseEvent(0, down); }
    else if (mb == MouseButton.SECONDARY) { queueMouseEvent(1, down); }
    else { queueMouseEvent(2, down); }
  }
  
  private void queueKey(KeyEvent ev, Boolean down)
  {
    boolean ctrlDown = ev.isControlDown();
    boolean shiftDown = ev.isShiftDown();
    if (down==null)
    {
      if (ev.getCharacter() == null) return;
      if (ev.getCharacter().length()<1) return;
      char c = ev.getCharacter().toCharArray()[0];
      int code = JavafxToNiftyKeyCodeConverter.convertFromChar(c);
      if (code == 0) { return; }
      var kie = new KeyboardInputEvent(code, c, true, shiftDown, ctrlDown);
      queueKeyboardEvent(kie);
      kie = new KeyboardInputEvent(code, c, false, shiftDown, ctrlDown);
      queueKeyboardEvent(kie);
    }
    else
    {
      int code = JavafxToNiftyKeyCodeConverter.convert(ev.getCode());
      if (code == KeyboardInputEvent.KEY_NONE) { return; }
      char c = JavafxToNiftyKeyCodeConverter.convertToChar(ev);
      var ke = new KeyboardInputEvent(code, c, down, shiftDown, ctrlDown);
      queueKeyboardEvent(ke);
    }
  }
  
  @Override
  public Point2i getMousePosition()
  {
    return mousePos;
  }

  @Override
  public void pollEvents()
  {
  }

  @Override
  public void setMousePosition(int x, int y)
  {
    log.warning("Not supported yet: setMousePos(x,y)");
  }

}
