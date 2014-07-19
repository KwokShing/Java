package com.zlc.paopaotang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	Socket socket;
	public DataInputStream inputFromClient;
	public DataOutputStream outputToClient;
	public ObjectInputStream din;
	public ObjectOutputStream dout;
	
	private int operation;
	private ArrayList<ActionListener> actionListenerList;

	public Server(Socket rivalSocket) {
		try {

			socket = rivalSocket;
			//System.out.println("Server: server.java�Ѿ�accept��");
			// Create data input and output streams
			outputToClient = new DataOutputStream(socket.getOutputStream());
			inputFromClient = new DataInputStream(socket.getInputStream());
			/*
			Thread thread1 = new Thread(new ListenClient(this));
			thread1.start();
			System.out.println("构造函数：rivalSocket is setup");
			*/

		} catch (IOException ex) {
			System.err.println(ex);
		}

	}

	public void listen(){
		Thread thread1 = new Thread(new ListenClient(this));
		thread1.start();
		System.out.println("构造函数：rivalSocket is setup");
	}
	
	
	
	
	
	public void sendMap(Modle_map map) throws IOException {
		
		dout = new ObjectOutputStream(outputToClient);
		dout.writeObject(map);
		System.out.println("Send map already");
		dout.flush();
	}

	public Modle_map getMap() throws IOException, ClassNotFoundException {
		System.out.println(000);
		din = new ObjectInputStream(inputFromClient);
		//din = new ObjectInputStream(socket.getInputStream());
		System.out.println(111);
		return (Modle_map) din.readObject();
	}

	public void sendInt(int i) throws IOException {
		outputToClient.writeInt(i);
		outputToClient.flush();
	}

	public void setOperation(int s) {
		this.operation = s;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"operation"));
	}

	public int getOperation() {
		return operation;
	}

	// 下面三个是在用MVC模式的标准代码，不需要改动
	/** Register an action event listener */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/** Fire TickEvent */
	private void processEvent(ActionEvent e) {
		ArrayList list;
		synchronized (this) {
			if (actionListenerList == null) {
				System.out.println("action listerner is null");
				return;
			}
			list = (ArrayList) actionListenerList.clone();
		}
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener) list.get(i);
			listener.actionPerformed(e);
		}
	}

}
