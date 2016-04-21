package Bean;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BeanFive implements Shape {

	private Theme theme;

	public void draw(int i, int j, ImageObserver observer, Graphics g) throws IOException {
		theme = Theme.getInstance();
		String bean = theme.getBeans(theme.getCurrentTheme()).get(4);

		g.drawImage(ImageIO.read(new File(bean)), 47 * i + 2, 47 * j + 2, observer);
	}

}
