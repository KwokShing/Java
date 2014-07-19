package draw3;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.Socket;

/**
 * The penViewController is the core part of the game, it connects to the model
 * and all the performances
 * 
 * @author Kross
 * 
 */
public class penViewController extends penView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2870041273800127142L;
	// private ToolMove tMove = new ToolMove();
	/** The socket is to correspond the drawing and guessing player */
	private Socket socket = null;
	/** The client is to send the drawing info and get the guessing words */
	private static Client client = null;
	/** The server is to receive the model and send the guessing words */
	private static MultiThreadServer server = null;
	/** To show wrong message */
	private ShowMessage showMessage = new ShowMessage();
	
	private boolean activeControll = true;

	/**
	 * The penViewController's constructor, if it is drawing's turn, it new the
	 * client and if it is the guessing' turn, it new the server
	 * 
	 * @param nsocket
	 * @param turn
	 */
	public penViewController(Socket nsocket, int turn) {
		this.socket = nsocket;
		if (turn == 1) {// turn to draw
			client = new Client(socket, this);
			addMouseMotionListener(new MouseMotionAdapter() {
				/**
				 * Handle the mouse movement. change the model and send the
				 * model's info to the server
				 */
				public void mouseDragged(MouseEvent e) {
					if (model.getOx() >= 0 && model.getOy() >= 0) {
						model.setXY(e.getX(), e.getY());
						// tMove.drawTool(e.getX(), e.getY());
						client.handle(model.getOx(), model.getOy(),
								model.getX(), model.getY(),
								model.getPenColor(), model.getPenSize(),
								model.getWords());
					}
					model.setOxOy(e.getX(), e.getY());
				}

				/**
				 * If the mouse is not in dragged state, the set the ox, oy to
				 * -1
				 */
				public void mouseMoved(MouseEvent e) {
					// model.setOx(-1);
					// model.setOy(-1);
					model.setOxOy(-1, -1);
				}
			});
		} else {
			server = new MultiThreadServer(socket, this);
			HandleAClient task = new HandleAClient();
			new Thread(task).start();
		}
	}

	/**
	 * The inner class HandleAClient for the server. One is to receive the data
	 * and also send the guessing words tot the client
	 * 
	 * @author Kross
	 * 
	 */
	class HandleAClient implements Runnable {
		/** Run a thread */
		public void run() {
			while (true) {
				server.setGuess(getGuess());
				server.setWords(getWords());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setActiveControl(){
		this.activeControll = false;
	}
	
	public boolean getActiveControl(){
		return activeControll;
	}

	public void timeUp() {
		showMessage.timeUp();
	}

	/** The method is to set if the game is over */
	public void isEnd() {
		DrawMain.end = true;
	}

	/** The method is to show self's wrong */
	public void isWrong() {
		showMessage.drawWrong();
	}

	/** The method is to show the opposite player's win */
	public void oppositeWin() {
		showMessage.oppositeWin();
	}

	/** The method is to show self's win */
	public void selfWin() {
		showMessage.selfWin();
	}

	/**
	 * The getGuess method is to get the guess words
	 * 
	 * @return model's guess words
	 */
	public String getGuess() {
		return model.getGuess();
	}

	/**
	 * The setGuess method is to set the guess words
	 * 
	 * @param guess
	 */
	public void setGuess(String guess) {
		model.setGuess(guess);
	}

	/**
	 * The setWords method is to set the actual words
	 * 
	 * @param words
	 */
	public void setWords(String words) {
		model.setWords(words);
	}

	/**
	 * The getPenSize method is to get the pen's size from the model
	 * 
	 * @return pen's size
	 */
	public float getPenSize() {
		return model.getPenSize();
	}

	/**
	 * The changeSize method is to change the pen's size of the model
	 * 
	 * @param size
	 */
	public void changeSize(float size) {
		model.setPenSize(size);
	}

	/**
	 * The useEraser method is to change the pen's color to white
	 */
	public void useEraser() {
		model.setPenColor(Color.WHITE);
	}

	/**
	 * The changeColor method is to change the model's penColor
	 * 
	 * @param color
	 */
	public void changeColor(Color color) {
		model.setPenColor(color);
	}

	/**
	 * The checkWords method is to check whether the guessing word is equal to
	 * the actual words
	 * 
	 * @param words
	 * @return true if the guessing and the actual words are equal or false, on
	 *         the other hand
	 */
	public boolean checkWords(String words) {
		if (model.getWords().compareTo(words) == 0)
			return true;
		return false;
	}

	/**
	 * The getWords method is to get the model's wordss
	 * 
	 * @return model's actual words
	 */
	public String getWords() {
		return model.getWords();
	}

	/** Set the showMessage from the main frame */
	public void setWrong(ShowMessage showWrong2) {
		// TODO Auto-generated method stub
		this.showMessage = showWrong2;
	}
}
