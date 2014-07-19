package draw3;

import java.awt.Color;
import java.io.*;
import java.net.*;

/**
 * class Client use to handle the drawing player's socket data
 * 
 * @author Kross
 */
public class Client {
	/** The ObjuctInputStream in to get the socket's input data. */
	private ObjectInputStream in = null;
	/** The ObjuctInputStream in to get the socket's output data. */
	private ObjectOutputStream out = null;
	/**
	 * The PenModel model is use to be the temporary model variable to save the
	 * current model's information.
	 */
	private PenModel model = null;
	/** The Socket socket is for the drawing player. */
	private Socket socket = null;
	/** The viewCtrl is the core controller for the client to change the model */
	private penViewController viewCtrl = null;

	/**
	 * The class Client's constructor
	 */
	public Client(Socket nsocket, penViewController viewCtrl) {
		this.socket = nsocket;
		this.viewCtrl = viewCtrl;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("error");
		}
	}

	/**
	 * The handle method is to get the current model and save in a temporary
	 * model and is to be sent to the guessing player
	 * 
	 * @param ox
	 * @param oy
	 * @param x
	 * @param y
	 * @param color
	 * @param size
	 * @param words
	 */
	public void handle(int ox, int oy, int x, int y, Color color, float size,
			String words) {
		// TODO Auto-generated method stub
		model = new PenModel();
		model.setOxOyXY(ox, oy, x, y);
		model.setPenColor(color);
		model.setPenSize(size);
		model.setWords(words);

		try {
			out.writeObject(model);
			out.flush();
			model = new PenModel();
			model = (PenModel) in.readObject();
			if (model.getWords().compareTo(model.getGuess()) == 0) {
				System.out.println("fuckman");
				viewCtrl.oppositeWin();
				Thread.sleep(2000);
				viewCtrl.isEnd();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public Socket getSocket() {
	// return socket;
	// }

	// public void setSocket(Socket socket) {
	// this.socket = socket;
	// }
}
