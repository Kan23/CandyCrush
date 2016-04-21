package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;

import Audio.Audio;
import Bean.Theme;

public class MenuView extends Panel implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private MenuController controller;
	private Theme theme;
	private Frame frame;
	private String wallpaper;

	int selectedX = -1, selectedY = -1; 

	Audio audio;
	Image buffer;

	public MenuView() throws IOException, UnsupportedAudioFileException {
		controller = new MenuController();
		theme = Theme.getInstance();
		frame = new Frame("Crunchy Mario !");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(this);
		frame.pack();
		frame.setVisible(true);

		audio = Audio.getInstance("Audio/StarTheme.wav");

		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	public void mousePressed(MouseEvent e) { 
		selectedX = e.getX();
		selectedY = e.getY();
		repaint();
	}
	public void mouseMoved(MouseEvent e) { 
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		if(selectedX > 5 && selectedY > 75 && selectedX < 85 && selectedY < 110) {
			try {
				frame.setVisible(false);
				controller.getGame();
			} catch (IOException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
		if(selectedX > 5 && selectedY > 125 && selectedX < 145 && selectedY < 160) {
			try {
				frame.setVisible(false);
				controller.getOptions();
			} catch (IOException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
		if(selectedX > 5 && selectedY > 325 && selectedX < 85 && selectedY < 360) {
			System.exit(0);
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { mouseMoved(e); }

	@SuppressWarnings("static-access")
	public void run() {
		while(true) {
			try { Thread.currentThread().sleep(100); } catch(InterruptedException e) { }
			repaint();
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g2) {
		if(buffer == null) buffer = createImage(800, 600);
		Graphics g = (Graphics2D) buffer.getGraphics();
		String wallpaper = theme.getWallpapers(theme.getCurrentTheme()).get(0);

		try {
			BufferedImage screen = ImageIO.read(new File(wallpaper));
			g.drawImage(screen, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Menu
		g.setColor(Color.BLUE);
		g.fillRect(5, 15, 340, 40);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Crunchy Mario", 10, 50);


		// Play
		g.setColor(Color.BLUE);
		g.fillRect(5, 75, 80, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Play", 10, 100);

		// Options
		g.setColor(Color.BLUE);
		g.fillRect(5, 125, 140, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Options", 10, 150);

		// Back to menu
		g.setColor(Color.BLUE);
		g.fillRect(5, 325, 80, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Exit", 10, 350);

		g2.drawImage(buffer, 0, 0, null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(47 * 8 + 1, 47 * 8 + 1);
	}

}
