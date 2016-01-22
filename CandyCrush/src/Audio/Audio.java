package Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip clip;
	
	public Audio(){}

	public void play(String file) throws UnsupportedAudioFileException, IOException {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
			clip.loop(123456789);
			
		} catch(Exception ex) {
			System.err.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}
}
