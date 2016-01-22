package Bean;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

public interface Shape {
	public void draw(int i, int j, ImageObserver observer, Graphics g) throws IOException;
}
