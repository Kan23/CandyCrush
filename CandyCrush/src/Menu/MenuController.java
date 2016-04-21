package Menu;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import Game.CandyCrushView;
import Options.OptionsView;

public class MenuController {

	public void getGame() throws IOException, UnsupportedAudioFileException {
		@SuppressWarnings("unused")
		final CandyCrushView obj = new CandyCrushView();
	}
	
	public void getOptions() throws UnsupportedAudioFileException, IOException {
		@SuppressWarnings("unused")
		final OptionsView obj = new OptionsView();
	}

}
