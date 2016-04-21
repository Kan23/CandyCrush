package Options;

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

public class OptionsView extends Panel implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private OptionsController controller;
	private Theme theme;
	private String wallpaper;
	private Frame frame;

	int selectedX = -1, selectedY = -1; 

	Audio audio;
	Image buffer;

	public OptionsView() throws UnsupportedAudioFileException, IOException {
		controller = new OptionsController();
		theme = Theme.getInstance();
		frame = new Frame("Options");
		wallpaper = theme.getWallpapers(theme.getCurrentTheme()).get(1);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(this);
		frame.pack();
		frame.setVisible(true);

		audio = Audio.getInstance("Audio/OptionMusic.wav");

		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}


	public void mouseMoved(MouseEvent e) {
		repaint();		
	}

	public void mousePressed(MouseEvent e) {
		selectedX = e.getX();
		selectedY = e.getY();
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if(selectedX > 5 && selectedY > 75 && selectedX < 195 && selectedY < 110) {
			audio.upLevel();
		}
		if(selectedX > 5 && selectedY > 125 && selectedX < 240 && selectedY < 160) {
			audio.downLevel();
		}
		if(selectedX > 5 && selectedY > 325 && selectedX < 115 && selectedY < 360) {
			try {
				frame.setVisible(false);
				controller.getMenu();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
		repaint();		
	}

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
		
		try {
			BufferedImage screen = ImageIO.read(new File(wallpaper));
			g.drawImage(screen, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Options
		g.setColor(Color.BLUE);
		g.fillRect(5, 15, 185, 45);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Options", 10, 50);

		// Volume up
		g.setColor(Color.BLUE);
		g.fillRect(5, 75, 190, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Volume up", 10, 100);

		// Volume down
		g.setColor(Color.BLUE);
		g.fillRect(5, 125, 235, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Volume down", 10, 150);

		// Back to menu
		g.setColor(Color.BLUE);
		g.fillRect(5, 325, 110, 35);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Menu", 10, 350);
		
		g2.drawImage(buffer, 0, 0, null);

	}

	public Dimension getPreferredSize() {
		return new Dimension(47 * 8 + 1, 47 * 8 + 1);
	}

}
