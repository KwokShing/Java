package draw3.copy;

import java.io.*;
import java.net.*;

public class MultiThreadServer /* extends JFrame */{
	private PenModel model = new PenModel();
	private Socket socket;
	private Copy_2_of_penViewController copyviewCtrl = null;
	private penViewController viewCtrl = null;

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String guess = null;
	private String words = null;

	public MultiThreadServer(Socket socket,
			Copy_2_of_penViewController copyviewCtrl) {
		this.socket = socket;
		this.copyviewCtrl = copyviewCtrl;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			HandleAClient task = new HandleAClient();
			new Thread(task).start();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	public MultiThreadServer(Socket socket,
			penViewController viewCtrl) {
		this.socket = socket;
		this.viewCtrl = viewCtrl;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			HandleAClient2 task = new HandleAClient2();
			new Thread(task).start();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public void setWords(String words) {
		this.words = words;
	}

	class HandleAClient implements Runnable {
		/** Run a thread */
		public void run() {
			try {
				while (true) {
					model = (PenModel) in.readObject();
					copyviewCtrl.getModel(model);
					model = new PenModel();
					model.setGuess(guess);
					model.setWords(words);
					out.writeObject(model);
					out.flush();
				}
			} catch (IOException e) {
				System.err.println(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class HandleAClient2 implements Runnable {
		/** Run a thread */
		public void run() {
			try {
				while (true) {
					model = (PenModel) in.readObject();
					viewCtrl.getModel(model);
					model = new PenModel();
					model.setGuess(guess);
					model.setWords(words);
					out.writeObject(model);
					out.flush();
				}
			} catch (IOException e) {
				System.err.println(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
