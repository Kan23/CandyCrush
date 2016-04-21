package Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private static volatile Audio instance;
	private Clip clip;
	private static FloatControl gainControl;

	public Audio(){}

	public void play(String file, float volume) throws UnsupportedAudioFileException, IOException {
		if(clip != null) {
			this.stop();
			clip = null;
		}
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.loop(Integer.MAX_VALUE);

		} catch(Exception ex) {
			System.err.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}

	public void upLevel() {
		if(gainControl.getValue() + 1f < -6f)
			gainControl.setValue(gainControl.getValue() + 1f);
	}

	public void downLevel() {
		if(gainControl.getValue() - 1f > -80f)
			gainControl.setValue(gainControl.getValue() - 1f);
	}

	public static Audio getInstance(String file) throws UnsupportedAudioFileException, IOException {
		if (instance == null) {
			synchronized (Audio.class) {
				if (instance == null) {
					instance = new Audio();
					instance.play(file, -80f);
					return instance;
				}
			}
		}
		instance.play(file, Audio.gainControl.getValue());
		return instance;
	}
}
