package draw3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StillClock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126450433170078696L;
	private int second = 0;
	/** The viewCtrl is for clock to change the game state */
	private penViewController viewCtrl = null;

	/** Construct a default clock with the current time */
	public StillClock() {
		setBackground(Color.WHITE);
		setCurrentTime();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}

	/** The inner class to implement the action */
	private class TimerListener implements ActionListener {
		/** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			setCurrentTime();
			repaint();
		}
	}

	/** Construct a clock with specified hour, minute, and second */
	public StillClock(int hour, int minute, int second) {
		this.second = second;
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		repaint();
	}

	/** Draw the clock */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Initialize clock parameters
		int clockRadius = (int) (Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		// Draw circle
		g.setColor(Color.black);
		g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
				2 * clockRadius, 2 * clockRadius);
		g.drawString("60", xCenter - 5, yCenter - clockRadius + 12);
		g.drawString("15", xCenter - clockRadius + 3, yCenter + 5);
		g.drawString("30", xCenter + clockRadius - 15, yCenter + 3);
		g.drawString("45", xCenter - 3, yCenter + clockRadius - 3);

		// Draw second hand
		int sLength = (int) (clockRadius * 0.8);
		int xSecond = (int) (xCenter + sLength
				* Math.sin(second * (2 * Math.PI / 60)));
		int ySecond = (int) (yCenter - sLength
				* Math.cos(second * (2 * Math.PI / 60)));
		g.setColor(Color.red);
		g.drawLine(xCenter, yCenter, xSecond, ySecond);
	}

	/** Add the second */
	public void setCurrentTime() {
		this.second++;
		if (second == 59) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewCtrl.timeUp();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewCtrl.isEnd();
		}
	}

	/** To set the dimension */
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public void setCtrl(penViewController viewCtrl) {
		// TODO Auto-generated method stub
		this.viewCtrl = viewCtrl;
	}
}
