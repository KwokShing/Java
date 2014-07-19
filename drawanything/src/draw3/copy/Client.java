package draw3.copy;

import java.awt.Color;
import java.io.*;
import java.net.*;

public class Client {
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private PenModel model;
	private Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("error");
		}
	}


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
				System.out.println("fuck damn right");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
