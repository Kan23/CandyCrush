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

public class MenuView extends Panel implements Runnable, MouseListener, MouseMotionListener {

private static final long serialVersionUID = 1L;
	
	private MenuController controller;
	private Frame frame;
	
	int selectedX = -1, selectedY = -1; 
	
	Audio audio;
	Image buffer;
	
	public MenuView() throws IOException, UnsupportedAudioFileException {
		controller = new MenuController();
		frame = new Frame("Crunchy Mario !");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		audio = new Audio();
		audio.play("Audio/StarTheme.wav");
		
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
		
		if(selectedX > 5 && selectedY > 75 && selectedX < 70 && selectedY < 130) {
			try {
				frame.setVisible(false);
				audio.stop();
				controller.getGame();
			} catch (IOException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

		try {
			BufferedImage screen = ImageIO.read(new File("ScreenMenu/Mario.jpg"));
			g.drawImage(screen, 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Menu
		g.setColor(Color.BLUE);
		g.fillRect(5, 20, 270, 35);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Crunchy Mario", 10, 50);
		
		
		// Play
		g.setColor(Color.BLUE);
		g.fillRect(5, 75, 65, 35);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Play", 10, 100);
		
		g2.drawImage(buffer, 0, 0, null);
	}
	
    public Dimension getPreferredSize() {
        return new Dimension(47 * 8 + 1, 47 * 8 + 1);
    }

}
