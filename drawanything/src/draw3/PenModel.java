package draw3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class PenModel is used to save all the model data, including the pen's
 * size, color, position and the actual as well as the guessing words
 * 
 * @author Kross
 * 
 */
public class PenModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8101788343605262320L;
	/** The penSize is to save the pen's size and its default value is 5.0f */
	private float penSize = 5.0f;
	/** The penColor is to save the pen's color and its default color is black */
	private Color penColor = Color.black;
	/** The ox, oy, x, y are the positions of(ox,oy) and (x,y),respectively */
	private int ox = 0, oy = 0, x, y;
	/**
	 * The words is to save the actual words to be guessed and its default value
	 * is "hey"
	 */
	private String words = "hey";
	/** The guess is to save the guessing word its default value is "man" */
	private String guess = "man";

	// private boolean turn = false;
	/**
	 * The actionListenerList is to save all the actions that change the model
	 * and notify the controller to draw the view
	 */
	private ArrayList<ActionListener> actionListenerList;

	// public boolean isTurn() {
	// return turn;
	// }
	//
	// public void setTurn(boolean turn) {
	// this.turn = turn;
	// }

	/**
	 * The getWords method is to get the actual words
	 * 
	 * @return words
	 */
	public String getWords() {
		return words;
	}

	/**
	 * The setWords method is to set the actual words
	 * 
	 * @param words
	 */
	public void setWords(String words) {
		this.words = words;
	}

	/**
	 * The getGuess method is to get the guessing words
	 * 
	 * @return guess
	 */
	public String getGuess() {
		return guess;
	}

	/**
	 * The setGuess method is to set the guessing words
	 * 
	 * @param guess
	 */
	public void setGuess(String guess) {
		this.guess = guess;
	}

	/**
	 * The getPenSize method is to get the pen's size
	 * 
	 * @return penSize
	 */
	public float getPenSize() {
		return penSize;
	}

	/**
	 * The setGuess method is to set the guessing words
	 */
	public void setPenSize(float penSize) {
		this.penSize = penSize;
	}

	/**
	 * The getPenColor method is to get the pen's color
	 * 
	 * @return penColor
	 */
	public Color getPenColor() {
		return penColor;
	}

	/**
	 * The setGuess method is to set the guessing words
	 */
	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

	/**
	 * The getOx method is to get the Ox
	 * 
	 * @return ox
	 */
	public int getOx() {
		return ox;
	}

	/**
	 * The getOx method is to get the oy
	 * 
	 * @return oy
	 */
	public int getOy() {
		return oy;
	}

	/**
	 * The setGuess method is to set the guessing words
	 */
	public void setOx(int ox) {
		this.ox = ox;
	}

	/**
	 * The setGuess method is to set the guessing words
	 */
	public void setOy(int oy) {
		this.oy = oy;
	}

	/**
	 * The getY method is to set the current position (x,y)
	 */
	public void setOxOy(int ox, int oy) {
		this.ox = ox;
		this.oy = oy;
	}

	/**
	 * The getY method is to return the current position's x
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * The getY method is to set the current position's x
	 */
	public void setX(int x) {
		this.x = x;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "damn"));
	}

	/**
	 * The getY method is to return the current position's y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * The setGuess method is to set the guessing words
	 */
	public void setY(int y) {
		this.y = y;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "fuck"));
	}

	/**
	 * The setXY method is to set the current pen's position
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY(int x, int y) {
		this.y = y;
		this.x = x;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "xy"));
	}

	/**
	 * The setOxOyXY method is to set the pen's position
	 * 
	 * @param ox
	 * @param oy
	 * @param x
	 * @param y
	 */
	public void setOxOyXY(int ox, int oy, int x, int y) {
		this.ox = ox;
		this.oy = oy;
		this.x = x;
		this.y = y;
	}

	/**
	 * The sync method is used to sync the action once the data are changed.
	 */
//	public void sync() {
//		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "sync"));
//	}

	/**
	 * The addActionListener is to sync the actions to the view
	 */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/**
	 * The processEvent method is to save all the actions that change the model
	 * and notify the controller to draw the view
	 * 
	 * @param e
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

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
