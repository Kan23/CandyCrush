package Options;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import Menu.MenuView;

public class OptionsController {
	public OptionsController () {}
	public void getMenu() throws IOException, UnsupportedAudioFileException {
		@SuppressWarnings("unused")
		final MenuView obj = new MenuView();
	}
}
