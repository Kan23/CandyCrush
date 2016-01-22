package Game;
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
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import Audio.Audio;
import Bean.Shape;
import Bean.ShapeFactory;

public class CandyCrushView extends Panel implements Runnable, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;

	private CandyCrushController controller;

	private Frame frame;
	private ShapeFactory factory;

	String spritesNames[] = {"WHITE", "MARIO", "PEACH", "BOO", "BOWSERJR", "YOSHI", "TOAD"};

	int selectedX = -1, selectedY = -1; 
	int swappedX = -1, swappedY = -1;

	Image buffer;
	Audio audio;

	public CandyCrushView() throws IOException, UnsupportedAudioFileException {
		controller = new CandyCrushController();

		frame = new Frame("It's me, Mario !");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(this);
		frame.pack();
		frame.setVisible(true);

		audio = new Audio();
		audio.play("Audio/GameMusic.wav");
		factory = new ShapeFactory();
		while(controller.fill());
		while(controller.removeAlignments()) {
			controller.fill();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	public void mousePressed(MouseEvent e) { 
		selectedX = e.getX() / 47;
		selectedY = e.getY() / 47;
		repaint();
	}
	public void mouseMoved(MouseEvent e) { 
		if(selectedX != -1 && selectedY != -1) {
			swappedX = e.getX() / 47;
			swappedY = e.getY() / 47;
			if(!controller.isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
				swappedX = swappedY = -1;
			}
		}
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		if(selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
			controller.swap(selectedX, selectedY, swappedX, swappedY);
		}
		selectedX = selectedY = swappedX = swappedY = -1;

		if(e.getX() > 245 && e.getY() > 405 && e.getX() < 325 && e.getY() < 435) {
			try {
				frame.setVisible(false);
				audio.stop();
				controller.getMenu();
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

			if(!controller.fill()) {
				controller.removeAlignments();
			}

			repaint();
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g2) {
		if(buffer == null) buffer = createImage(800, 600);
		Graphics g = (Graphics2D) buffer.getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.BLACK);
		for(int i = 0; i < 9; i++) {
			g.drawLine(47 * i, 0, 47 * i, 8 * 47 + 1); 
			g.drawLine(0, 47 * i, 8 * 47 + 1, 47 * i); 
		}

		if(selectedX != -1 && selectedY != -1 && selectedY < 8) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedX * 47 + 1, selectedY * 47 + 1, 47, 47);
		}

		if(swappedX != -1 && swappedY != -1 && swappedY < 8) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedX * 47 + 1, swappedY * 47 + 1, 47, 47);
		}

		// Affichage du score
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Score : ", 10, 430);
		g.drawString(controller.getScore(), 100, 430);

		// Retour au menu
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Menu ", 250, 430);

		Shape shape;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				try {
					shape = factory.getShape(spritesNames[controller.getColor(i, j)]);
					shape.draw(i, j, this, g);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		g2.drawImage(buffer, 0, 0, null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(47 * 8 + 1, 47 * 8 + 1 + 100);
	}

}
