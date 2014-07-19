package com.zlc.gamehall;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.zlc.common.Config.Operation;
import com.zlc.common.Request;

public class P2PGameServer {
	private ServerSocket gameServer;
	private String userName;
	private String gameName;
	//private String rivalName;
	private SocketController mainSocketController;
	private Socket rivalSocket = null;

	public P2PGameServer(SocketController mainSocketController, String gameName) {

		this.mainSocketController = mainSocketController;
		this.gameName = gameName;
		this.userName = mainSocketController.getUserName();
		try {
			gameServer = new ServerSocket(DataModel.gamePort.get(gameName));
			System.out.println("P2P服务器" + gameServer + "已建立");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket tryToConnect() {
		try {
			System.out.println("发送游戏邀请...");
			Request request = new Request(Operation.INVITE_GAME_CTS, mainSocketController.getRivalName(),
					gameName, InetAddress.getLocalHost().getHostAddress());
			Boolean response = false;

			response = mainSocketController.sendRequestWithResponse(request);
			new Thread(mainSocketController).start();

			if (response == true) {
				mainSocketController.sendRequest(new Request(
						Operation.ULIST_CHANGE, userName, null,
						DataModel.gameState.get(gameName)));
				rivalSocket = gameServer.accept();
				return rivalSocket;
			} else 
				return null;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public void close() throws IOException {
		gameServer.close();
	}
}
