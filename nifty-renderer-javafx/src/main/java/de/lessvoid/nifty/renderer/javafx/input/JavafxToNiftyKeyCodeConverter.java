package de.lessvoid.nifty.renderer.javafx.input;

import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import javafx.scene.input.KeyCode;

public class JavafxToNiftyKeyCodeConverter
{

  public static int convert(KeyCode key)
  {
    switch (key)
    {
      case A: return KeyboardInputEvent.KEY_A;
      case B: return KeyboardInputEvent.KEY_B;
      case C: return KeyboardInputEvent.KEY_C;
      case D: return KeyboardInputEvent.KEY_D;
      case E: return KeyboardInputEvent.KEY_E;
      case F: return KeyboardInputEvent.KEY_F;
      case G: return KeyboardInputEvent.KEY_G;
      case H: return KeyboardInputEvent.KEY_H;
      case I: return KeyboardInputEvent.KEY_I;
      case J: return KeyboardInputEvent.KEY_J;
      case K: return KeyboardInputEvent.KEY_K;
      case L: return KeyboardInputEvent.KEY_L;
      case M: return KeyboardInputEvent.KEY_M;
      case N: return KeyboardInputEvent.KEY_N;
      case O: return KeyboardInputEvent.KEY_O;
      case P: return KeyboardInputEvent.KEY_P;
      case Q: return KeyboardInputEvent.KEY_Q;
      case R: return KeyboardInputEvent.KEY_R;
      case S: return KeyboardInputEvent.KEY_S;
      case T: return KeyboardInputEvent.KEY_T;
      case U: return KeyboardInputEvent.KEY_U;
      case V: return KeyboardInputEvent.KEY_V;
      case W: return KeyboardInputEvent.KEY_W;
      case X: return KeyboardInputEvent.KEY_X;
      case Y: return KeyboardInputEvent.KEY_Y;
      case Z: return KeyboardInputEvent.KEY_Z;
      case DIGIT0: return KeyboardInputEvent.KEY_0;
      case DIGIT1: return KeyboardInputEvent.KEY_1;
      case DIGIT2: return KeyboardInputEvent.KEY_2;
      case DIGIT3: return KeyboardInputEvent.KEY_3;
      case DIGIT4: return KeyboardInputEvent.KEY_4;
      case DIGIT5: return KeyboardInputEvent.KEY_5;
      case DIGIT6: return KeyboardInputEvent.KEY_6;
      case DIGIT7: return KeyboardInputEvent.KEY_7;
      case DIGIT8: return KeyboardInputEvent.KEY_8;
      case DIGIT9: return KeyboardInputEvent.KEY_9;
      case MINUS: return KeyboardInputEvent.KEY_MINUS;
      case ESCAPE: return KeyboardInputEvent.KEY_ESCAPE;
      case EQUALS: return KeyboardInputEvent.KEY_EQUALS;
      case BACK_SPACE: return KeyboardInputEvent.KEY_BACK;
      case TAB: return KeyboardInputEvent.KEY_TAB;
      case OPEN_BRACKET: return KeyboardInputEvent.KEY_LBRACKET;
      case CLOSE_BRACKET: return KeyboardInputEvent.KEY_RBRACKET;
      case ENTER: return KeyboardInputEvent.KEY_RETURN;
      case CONTROL: return KeyboardInputEvent.KEY_LCONTROL;
      case SEMICOLON: return KeyboardInputEvent.KEY_SEMICOLON;
      case DEAD_GRAVE: return KeyboardInputEvent.KEY_GRAVE;
      case SHIFT: return KeyboardInputEvent.KEY_LSHIFT;
      case BACK_SLASH: return KeyboardInputEvent.KEY_BACKSLASH;
      case COMMA: return KeyboardInputEvent.KEY_COMMA;
      case PERIOD: return KeyboardInputEvent.KEY_PERIOD;
      case SLASH: return KeyboardInputEvent.KEY_SLASH;
      case MULTIPLY: return KeyboardInputEvent.KEY_MULTIPLY;
      case CONTEXT_MENU: return KeyboardInputEvent.KEY_LMENU;
      case SPACE: return KeyboardInputEvent.KEY_SPACE;
      case F1: return KeyboardInputEvent.KEY_F1;
      case F2: return KeyboardInputEvent.KEY_F2;
      case F3: return KeyboardInputEvent.KEY_F3;
      case F4: return KeyboardInputEvent.KEY_F4;
      case F5: return KeyboardInputEvent.KEY_F5;
      case F6: return KeyboardInputEvent.KEY_F6;
      case F7: return KeyboardInputEvent.KEY_F7;
      case F8: return KeyboardInputEvent.KEY_F8;
      case F9: return KeyboardInputEvent.KEY_F9;
      case F10: return KeyboardInputEvent.KEY_F10;
      case F11: return KeyboardInputEvent.KEY_F11;
      case F12: return KeyboardInputEvent.KEY_F12;
      case F13: return KeyboardInputEvent.KEY_F13;
      case F14: return KeyboardInputEvent.KEY_F14;
      case F15: return KeyboardInputEvent.KEY_F15;
      case NUM_LOCK: return KeyboardInputEvent.KEY_NUMLOCK;
      case SCROLL_LOCK: return KeyboardInputEvent.KEY_SCROLL;
      case NUMPAD0: return KeyboardInputEvent.KEY_NUMPAD0;
      case NUMPAD1: return KeyboardInputEvent.KEY_NUMPAD1;
      case NUMPAD2: return KeyboardInputEvent.KEY_NUMPAD2;
      case NUMPAD3: return KeyboardInputEvent.KEY_NUMPAD3;
      case NUMPAD4: return KeyboardInputEvent.KEY_NUMPAD4;
      case NUMPAD5: return KeyboardInputEvent.KEY_NUMPAD5;
      case NUMPAD6: return KeyboardInputEvent.KEY_NUMPAD6;
      case NUMPAD7: return KeyboardInputEvent.KEY_NUMPAD7;
      case NUMPAD8: return KeyboardInputEvent.KEY_NUMPAD8;
      case NUMPAD9: return KeyboardInputEvent.KEY_NUMPAD9;
      case SUBTRACT: return KeyboardInputEvent.KEY_SUBTRACT;
      case ADD: return KeyboardInputEvent.KEY_ADD;
      case DECIMAL: return KeyboardInputEvent.KEY_DECIMAL;
      case KANA: return KeyboardInputEvent.KEY_KANA;
      case CONVERT: return KeyboardInputEvent.KEY_CONVERT;
      case NONCONVERT: return KeyboardInputEvent.KEY_NOCONVERT;
      case CIRCUMFLEX: return KeyboardInputEvent.KEY_CIRCUMFLEX;
      case AT: return KeyboardInputEvent.KEY_AT;
      case COLON: return KeyboardInputEvent.KEY_COLON;
      case UNDERSCORE: return KeyboardInputEvent.KEY_UNDERLINE;
      case KANJI: return KeyboardInputEvent.KEY_KANJI;
      case STOP: return KeyboardInputEvent.KEY_STOP;
      case JAPANESE_KATAKANA: return KeyboardInputEvent.KEY_KANA;
      case DIVIDE: return KeyboardInputEvent.KEY_DIVIDE;
      case PAUSE: return KeyboardInputEvent.KEY_PAUSE;
      case HOME: return KeyboardInputEvent.KEY_HOME;
      case UP: return KeyboardInputEvent.KEY_UP;
      case DOWN: return KeyboardInputEvent.KEY_DOWN;
      case LEFT: return KeyboardInputEvent.KEY_LEFT;
      case RIGHT: return KeyboardInputEvent.KEY_RIGHT;
      case PAGE_UP: return KeyboardInputEvent.KEY_PRIOR;
      case PAGE_DOWN: return KeyboardInputEvent.KEY_NEXT;
      case END: return KeyboardInputEvent.KEY_END;
      case INSERT: return KeyboardInputEvent.KEY_INSERT;
      case DELETE: return KeyboardInputEvent.KEY_DELETE;
      case META: return KeyboardInputEvent.KEY_LMETA;
      default: return KeyboardInputEvent.KEY_NONE;
    }
  }
}
