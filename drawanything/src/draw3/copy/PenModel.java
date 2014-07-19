package draw3.copy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class PenModel implements Serializable {
	private float penSize = 5.0f;
	private Color penColor = Color.black;
	private int ox = 0, oy = 0, x, y;
	private String words = "hey";
	private String guess = "man";
	private boolean turn = false;
	private ArrayList<ActionListener> actionListenerList;

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public float getPenSize() {
		return penSize;
	}

	public void setPenSize(float penSize) {
		this.penSize = penSize;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

	public int getOx() {
		return ox;
	}

	public void setOx(int ox) {
		this.ox = ox;
	}

	public int getOy() {
		return oy;
	}

	public void setOy(int oy) {
		this.oy = oy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "damn"));
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "fuck"));
	}

	public void setXY(int x, int y) {
		this.y = y;
		this.x = x;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "xy"));
	}

	public void setOxOyXY(int ox, int oy, int x, int y) {
		this.ox = ox;
		this.oy = oy;
		this.x = x;
		this.y = y;
	}

	public void sync() {
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "sync"));
	}

	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	private void processEvent(ActionEvent e) {
		ArrayList list;

		synchronized (this) {
			if (actionListenerList == null)
				return;
			list = (ArrayList) actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener) list.get(i);
			listener.actionPerformed(e);
		}
	}
}
