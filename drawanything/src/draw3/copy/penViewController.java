package draw3.copy;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.Socket;

public class penViewController extends penView {
	private ToolMove tMove = new ToolMove();
	private Socket socket = null;
	private static Client client = null;
	private static MultiThreadServer server = null;

//	public penViewController() {
//	}

	public penViewController(Socket nsocket, boolean turn) {
		//this();
		this.socket = nsocket;
		if (turn == true) {// turn to draw
			client = new Client(socket);
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					if (model.getOx() >= 0 && model.getOy() >= 0) {
						model.setXY(e.getX(), e.getY());
						// tMove.drawTool(e.getX(), e.getY());
						client.handle(model.getOx(), model.getOy(),
								model.getX(), model.getY(),
								model.getPenColor(), model.getPenSize(),
								model.getWords());
					}
					model.setOx(e.getX());
					model.setOy(e.getY());
				}

				public void mouseMoved(MouseEvent e) {
					model.setOx(-1);
					model.setOy(-1);
				}
			});
		} else {
			server = new MultiThreadServer(socket, this);
			HandleAClient task = new HandleAClient();
			new Thread(task).start();
		}
	}

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

	public String getGuess() {
		return model.getGuess();
	}

	public void setGuess(String guess) {
		model.setGuess(guess);
	}

	public void setWords(String words) {
		model.setWords(words);
	}

	public void setTurn() {
		model.setTurn(true);
	}

	public float getPenSize() {
		return model.getPenSize();
	}

	public void changeSize(float size) {
		model.setPenSize(size);
	}

	public void useEraser() {
		model.setPenColor(Color.WHITE);
	}

	public void changeColor(Color color) {
		model.setPenColor(color);
	}

	public void setToolMovw(ToolMove tMove) {
		this.tMove = tMove;
	}

	public boolean checkWords(String words) {
		if (model.getWords().compareTo(words) == 0)
			return true;
		return false;
	}

	public String getWords() {
		return model.getWords();
	}

}
