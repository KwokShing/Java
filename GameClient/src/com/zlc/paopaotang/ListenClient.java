package com.zlc.paopaotang;

import java.io.IOException;


public class ListenClient implements Runnable{

	
	private int dataRecive;
	Server server;
	
	public ListenClient(Server s) {
		server =s;
	}
	
	
	@Override
	public void run() {
		while (true) {
			// Receive radius from the client
			try {
				dataRecive = server.inputFromClient.readInt();
				server.setOperation(dataRecive);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}	
}


