package draw3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShowMessage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5815827628078078851L;
	/**Wrong message*/
	private ImageIcon image = new ImageIcon("wrong.png");
	/**Time's up message*/
	private ImageIcon time = new ImageIcon("timeup.png");
	/** To show the self's win message */
	private ImageIcon win = new ImageIcon("win.png");
	/** To show the opposite player's win */
	private ImageIcon selfwin = new ImageIcon("selfwin.png");

	/**Constructor for ShowMessage*/
	public ShowMessage() {
		super();
		setBackground(Color.WHITE);
		//setOpaque(false);
		setPreferredSize(new Dimension(100, 100));
	}

	/**Show the guess wrong message*/
	public void drawWrong() {
		Graphics g = this.getGraphics();
		g.drawImage(image.getImage(), 200, 20, null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	/**Show the time's up message*/
	public void timeUp() {
		Graphics g = this.getGraphics();
		g.drawImage(time.getImage(), 200, 20, null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**Show the opposite's player win message*/
	public void oppositeWin() {
		// TODO Auto-generated method stub
		Graphics g = this.getGraphics();
		g.drawImage(win.getImage(), 200, 20, null);
		System.out.println("duile");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**Show the self's win message*/
	public void selfWin() {
		// TODO Auto-generated method stub
		Graphics g = this.getGraphics();
		g.drawImage(selfwin.getImage(), 200, 20, null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
