package de.lessvoid.nifty.sound.javafx;

import java.net.*;
import javax.sound.sampled.*;

public class SpiPlayer {
  URL url;
  Clip clip;
   
  public SpiPlayer(URL url)
  {
    this.url = url;
  }

  public void play() {
    if (clip==null)
    { // Init clip in constructor leads to inputstream-errors.
      try (AudioInputStream ais=AudioSystem.getAudioInputStream(url))
      {
        clip = AudioSystem.getClip();
        clip.open(ais);
      }
      catch(Exception e) {
         e.printStackTrace();
      }
    }
    playNow();
  }
  
  private void playNow()
  {
    if (clip!=null) { clip.setFramePosition(0); clip.start(); }
  }
   
   public boolean isRunning() {
     if (clip!=null) { clip.isRunning(); }
     return false;
   }

  public void stop() {
    if (clip!=null) { clip.stop(); }
  }
  
  public void dispose() { clip.close(); }

}
