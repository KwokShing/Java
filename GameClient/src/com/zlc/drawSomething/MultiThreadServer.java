package com.zlc.drawSomething;

import java.io.*;
import java.net.*;

/**
 * The class MultiThreadServer is used to handle the data receiving from the
 * drawing player and change the current view
 * 
 * @author Kross
 * 
 */
public class MultiThreadServer {
	/**
	 * The PenModel model is use to be the temporary model variable to save the
	 * current model's information.
	 */
	private PenModel model = new PenModel();
	/** The Socket socket is for the guessing player. */
	private Socket socket;
	private penViewController viewCtrl = null;
	/** The ObjuctInputStream in to get the socket's input data. */
	private ObjectInputStream in;
	/** The ObjuctInputStream in to get the socket's output data. */
	private ObjectOutputStream out;
	/** The guess variable is used to save the guess words */
	private String guess = "TAT";
	/** The words variable is used to save the actual words */
	private String words = "TTA";

	/**
	 * The MultiThreadServer's constructor use the thread to get the
	 * drawingplayer's data and once the guessing user guess correctly, it send
	 * back the message
	 */
	public MultiThreadServer(Socket nsocket, penViewController viewCtrl) {
		this.socket = nsocket;
		this.viewCtrl = viewCtrl;
		try {
			in = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
			out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));	
			HandleAClient2 task = new HandleAClient2();
			new Thread(task).start();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * The setGuess method is used to set the guess words
	 * 
	 * @param guess
	 */
	public void setGuess(String guess) {
		this.guess = guess;
	}

	/**
	 * The setWords method is used to set the actual words
	 * 
	 * @param words
	 */
	public void setWords(String words) {
		this.words = words;
	}

	/**
	 * The inner class HandleAClient2 is used to handle the receiving data from
	 * the drawing player and change the current view
	 * 
	 * @author Kross
	 * 
	 */
	class HandleAClient2 implements Runnable {
		/** Run a thread */
		public void run() {
			try {
				while (true) {
					model = (PenModel) in.readObject();
					viewCtrl.changeModel(model);
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
