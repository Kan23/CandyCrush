package Bean;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mario implements Shape {
	
	public void draw(int i, int j, ImageObserver observer, Graphics g) throws IOException {
		g.drawImage(ImageIO.read(new File("Sprites/Mario.png")), 47 * i + 2, 47 * j + 2, observer);
	}

}
