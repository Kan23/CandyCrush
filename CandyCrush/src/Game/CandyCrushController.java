package Game;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.UnsupportedAudioFileException;

import Menu.MenuView;

public class CandyCrushController {

	private CandyCrushModel model;
	private int nbColors = 7; 
	
	private boolean gameStarted = false;
	
	CandyCrushController() {
		model = new CandyCrushModel();
	}   
	
	public void startGame() {
		gameStarted = true;
	}

	boolean removeAlignments() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(model.getGridCase(i,j) != 0 && horizontalAligned(i, j)) {
					model.setMarkedCase(i, j, true);
					model.setMarkedCase(i + 1, j, true);
					model.setMarkedCase(i + 2, j, true);
				}
				if(model.getGridCase(i,j) != 0 && verticalAligned(i, j)) {
					model.setMarkedCase(i, j, true);
					model.setMarkedCase(i, j + 1, true);
					model.setMarkedCase(i, j + 2, true);
				}
			}
		}
		boolean modified = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(model.getMarkedCase(i, j)) {
					if(gameStarted) model.addScore();
					model.setGridCase(i, j, 0);
					model.setMarkedCase(i, j, false);
					modified = true;
				}
			}
		}	
		return modified;
	}

	boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 7; j >= 0; j--) {
				if(model.getGridCase(i, j) == 0) {
					if(j == 0) 
						model.setGridCase(i, j, 1 + rand.nextInt(nbColors - 1));
					else {
						model.setGridCase(i, j, model.getGridCase(i, j -1));
						model.setGridCase(i, j - 1, 0);
					}
					modified = true;
				}
			}
		}
		return modified;
	}

	boolean horizontalAligned(int i, int j) {
		if(i < 0 || j < 0 || i >= 6 || j >= 8) 
			return false;
		if(model.getGridCase(i, j) == model.getGridCase(i + 1, j) 
		   && (model.getGridCase(i, j) == model.getGridCase(i + 2, j)))
				return true;
		return false;
	}

	boolean verticalAligned(int i, int j) {
		if(i < 0 || j < 0 || i >= 8 || j >= 6) 
			return false;
		if(model.getGridCase(i, j) == model.getGridCase(i, j + 1) 
		   && (model.getGridCase(i, j) == model.getGridCase(i, j + 2))) 
				return true;
		return false;
	}

	boolean isValidSwap(int x1, int y1, int x2, int y2) {
		if(x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1) return false;
		if(Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1) return false;
		if(model.getGridCase(x1, y1) == model.getGridCase(x2, y2)) 
			return false;

		model.swap(x1, y1, x2, y2);

		boolean newAlignment = false;
		for(int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(x1 - i, y1);
			newAlignment |= horizontalAligned(x2 - i, y2);
			newAlignment |= verticalAligned(x1, y1 - i);
			newAlignment |= verticalAligned(x2, y2 - i);
		}

		model.swap(x1, y1, x2, y2);
		return newAlignment;
	}
	
	public void swap(int x1, int y1, int x2, int y2) {
		model.swap(x1, y1, x2, y2);
	}

	public int getColor(int i, int j) {
		return model.getGridCase(i, j);
	}
	
	public String getScore(){
		return model.getScore().toString();
	}

	public void getMenu() throws IOException, UnsupportedAudioFileException {
		@SuppressWarnings("unused")
		final MenuView menu = new MenuView();
	}
}
