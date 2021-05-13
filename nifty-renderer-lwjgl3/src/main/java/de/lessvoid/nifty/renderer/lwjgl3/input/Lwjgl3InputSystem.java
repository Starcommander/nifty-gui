package de.lessvoid.nifty.renderer.lwjgl3.input;

import com.starcom.math.Point2i;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import de.lessvoid.nifty.spi.input.InputSystemEventClass;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

/**
 * Port of <code>de.lessvoid.nifty.renderer.lwjgl.LwjglInputSystem</code>
 * to LWJGL3/GLFW.
 * 
 * @author Brian Groenke
 */
public class Lwjgl3InputSystem extends InputSystemEventClass {
  
  private final static Logger log = Logger.getLogger(Lwjgl3InputSystem.class.getName());
  private final long glfwWindow;
  private final DoubleBuffer cursorX = BufferUtils.createDoubleBuffer(1);
  private final DoubleBuffer cursorY = BufferUtils.createDoubleBuffer(1);
  private final Point2i cursorPos = new Point2i();
  
  public final GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
      queueKeyboardEvent(createKeyEvent(key, scancode, action, mods));
    }
  };
  
  public final GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
    @Override
    public void invoke(long window, double xpos, double ypos) {
      queueMouseEvent((int)xpos, (int)ypos);
    }
  };
  
  public final GLFWMouseButtonCallback mouseButtonCallback = new GLFWMouseButtonCallback() {
    @Override
    public void invoke(long window, int button, int action, int mods) {
      queueMouseEvent(button, action == GLFW_PRESS);
    }
  };
  
  public final GLFWScrollCallback scrollCallback = new GLFWScrollCallback(){
    @Override
    public void invoke(long window, double xoffset, double yoffset) {
      queueMouseEvent((int) yoffset);
    }
  };
  
  private boolean initialized = false;
  
  public Lwjgl3InputSystem (final long glfwWindow) {
    this.glfwWindow = glfwWindow;
  }

  @Override
  public void setResourceLoader(@Nonnull final NiftyResourceLoader resourceLoader) {
  }

  public void startup() throws Exception {
    log.finer("Initializing LWJGL3 input system...");
    glfwSetCursorPosCallback(glfwWindow, cursorPosCallback);
    glfwSetMouseButtonCallback(glfwWindow, mouseButtonCallback);
    glfwSetKeyCallback(glfwWindow, keyCallback);
    glfwSetScrollCallback(glfwWindow, scrollCallback);
    
    initialized = true;
  }

  public void shutdown() {
    log.finer("Shutting down LWJGL3 input system...");
    clearEvents();
    
    initialized = false;
  }

  @Override
  public void setMousePosition(final int x, final int y) {
    final IntBuffer xpos = IntBuffer.allocate(1);
    final IntBuffer ypos = IntBuffer.allocate(1);
    glfwGetWindowPos(glfwWindow, xpos, ypos);
    glfwSetCursorPos(glfwWindow, x - xpos.get(0), y - ypos.get(0));
  }
  
  @Nonnull
  private KeyboardInputEvent createKeyEvent(final int key, final int scancode, final int action, final int mods) {
    final boolean keyDown = action == GLFW_PRESS || action == GLFW_REPEAT;
    final boolean shiftDown = (mods & GLFW_MOD_SHIFT) != 0;
    final boolean ctrlDown = (mods & GLFW_MOD_CONTROL) != 0;
    final int niftyKeyCode = GlfwToNiftyKeyCodeConverter.convertToNiftyKeyCode(key);
    final String keyName = glfwGetKeyName (key, scancode);
    final char keyChar = (keyName != null && keyName.length() == 1) ? keyName.charAt(0) : Character.MIN_VALUE;
    return new KeyboardInputEvent(niftyKeyCode, keyChar, keyDown, shiftDown, ctrlDown);
  }

  @Override
  public Point2i getMousePosition()
  {
    glfwGetCursorPos(glfwWindow, cursorX, cursorY);
    cursorPos.x = (int)cursorX.get(0);
    cursorPos.y = (int)cursorY.get(0);
    return cursorPos;
  }

  @Override
  public void pollEvents()
  {
    glfwPollEvents();
  }
}
