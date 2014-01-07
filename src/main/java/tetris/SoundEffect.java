package tetris;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.net.URLConnection;
import java.net.URISyntaxException;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
	BOOM("water.wav"),
		CHIME("chime.wav");

	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
			}

	public static Volume volume = Volume.LOW;

	private Clip clip;

	SoundEffect(String soundFileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource("waves/"+soundFileName);
			File soundFile = null;
			InputStream inStream = null;
			AudioInputStream soundIn = null;

			if (url.toString().contains("jar")) {
				try {
					URLConnection uc = url.openConnection();
					inStream = uc.getInputStream(); 
					InputStream bufferedIn = new BufferedInputStream(inStream);
					soundIn = AudioSystem.getAudioInputStream(bufferedIn);
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}
			else{
				try {
					soundFile = new File(url.toURI());
					soundIn = AudioSystem.getAudioInputStream(soundFile);
				} catch (URISyntaxException ex) {
					System.err.println(ex);
				}
			}

			AudioFormat format = soundIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(soundIn);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop();   
			clip.setFramePosition(0); 
			clip.start();   
		}
	}

	static void init() {
		values(); 
	}
}
