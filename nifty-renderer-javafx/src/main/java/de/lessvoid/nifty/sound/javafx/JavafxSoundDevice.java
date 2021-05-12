package de.lessvoid.nifty.sound.javafx;

import de.lessvoid.nifty.sound.SoundSystem;
import de.lessvoid.nifty.spi.sound.SoundDevice;
import de.lessvoid.nifty.spi.sound.SoundHandle;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 * Implementation of Nifty's SoundDevice for the Minim Java audio library.
 * Minim is an audio library primarily built for use with Processing.
 * @author Xuanming
 */
public class JavafxSoundDevice implements SoundDevice {
	
	NiftyResourceLoader resourceLoader;
	
	/**
	 * Create an instance of SoundDevice.
	 */
	public JavafxSoundDevice(){
	}

	@Override
	public void setResourceLoader(NiftyResourceLoader niftyResourceLoader) {
		this.resourceLoader = niftyResourceLoader;
	}

	@Override
	public SoundHandle loadSound(SoundSystem soundSystem, String filename)
        {
          return new SoundHandle()
          {
            MediaPlayer mediaPlayer = createMediaPlayer();
            SpiPlayer spiPlayer = createSpiPlayer();

            private MediaPlayer createMediaPlayer()
            {
              MediaPlayer mp = null;
              try
              {
                  mp = new MediaPlayer(new Media(resourceLoader.getResource(filename).toString()));
              }
              catch (MediaException e)
              {
                System.out.println("Cannot load media with javafx, try with spiPlayer: " + filename);
              }
              return mp;
            }

            private SpiPlayer createSpiPlayer()
            {
              if (mediaPlayer!=null) { System.err.println("No SpiPlayer necessary, using Javafx."); return null; }
              return new SpiPlayer(resourceLoader.getResource(filename));
            }

            @Override public void dispose()
            {
              if (mediaPlayer==null) { spiPlayer.dispose(); }
            }
            @Override public boolean isPlaying()
            {
              if (mediaPlayer==null) { return spiPlayer.isRunning(); }
              return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
            }
            @Override public float getVolume() { return 1.0f; }
            @Override public void setVolume(float f) {}
            @Override public void stop()
            {
              if (mediaPlayer!=null) { mediaPlayer.stop(); }
              else { spiPlayer.stop(); }
            }
            @Override public void play()
            {
              if (mediaPlayer!=null) { mediaPlayer.play(); }
              else { spiPlayer.play(); }
            }
          };
	}

	@Override
	public SoundHandle loadMusic(SoundSystem soundSystem, String filename) {
		return loadSound(soundSystem, filename);
	}

	@Override
	public void update(int delta) { // Do nothing.
	}
}
