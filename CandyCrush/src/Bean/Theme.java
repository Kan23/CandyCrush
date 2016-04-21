package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theme {
	private static volatile Theme instance;
	private String currentTheme;
	
	private Map<String,List<String>> beans;
	private Map<String,List<String>> wallpapers;
	private Map<String,List<String>> musics;
	
	public Theme() {
		beans = new HashMap<String, List<String>>();
		wallpapers = new HashMap<String, List<String>>();
		musics = new HashMap<String, List<String>>();
		
		List<String> marioB = new ArrayList<String>();
		marioB.add("Sprites/Yoshi.png");
		marioB.add("Sprites/Boo.png");
		marioB.add("Sprites/BowserJr.png");
		marioB.add("Sprites/Mario.png");
		marioB.add("Sprites/Peach.png");
		marioB.add("Sprites/Toad.png");
		
		beans.put("Mario", marioB);
		
		List<String> marioW = new ArrayList<String>();
		marioW.add("ScreenMenu/MarioMenu.jpg");
		marioW.add("ScreenMenu/MarioOption.jpg");
		
		wallpapers.put("Mario", marioW);
		
		List<String> marioM = new ArrayList<String>();
		marioM.add("Audio/MenuMusic.wav");
		marioM.add("Audio/OptionMusic.wav");
		marioM.add("Audio/GameMusic.wav");
		
		musics.put("Mario", marioM);
		
		List<String> pokemonB = new ArrayList<String>();
		pokemonB.add("Sprites/Cisayox.png");
		pokemonB.add("Sprites/Evoli.png");
		pokemonB.add("Sprites/Germignon.png");
		pokemonB.add("Sprites/Kaiminus.png");
		pokemonB.add("Sprites/Lugia.png");
		pokemonB.add("Sprites/Pikachu.png");
		
		beans.put("Pokemon", pokemonB);
	}
	
	public String getCurrentTheme() {
		return currentTheme;
	}
	
	public void setCurrentTheme(String theme) {
		this.currentTheme = theme;
	}
	
	public List<String> getBeans(String theme) {
		return beans.get(theme);
	}
	
	public List<String> getWallpapers(String theme) {
		return wallpapers.get(theme);
	}
	
	public static Theme getInstance() throws IOException {
		if (instance == null) {
			synchronized (Theme.class) {
				if (instance == null) {
					instance = new Theme();
					instance.setCurrentTheme("Mario");
					return instance;
				}
			}
		}
		return instance;
	}
}
