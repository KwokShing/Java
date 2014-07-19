package com.zlc.gamehall;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

import com.zlc.common.Config;
import com.zlc.common.Request;

public class SocketController implements Config, Runnable {
	private Socket client;
	private ObjectOutputStream oout;
	private ObjectInputStream oin;
	private String userName;
	private String rivalName;
	private boolean logState = false;
	private boolean pause=false;
	private HallFrame hallFrame;
	private GameBootFrame gameFrame;
	private boolean firstFlag = true;

	private Lock oinLock = new ReentrantLock();
	private Lock ooutLock = new ReentrantLock();

	public SocketController(HallFrame hallFrame) {
		this.hallFrame = hallFrame;
		try {
			client = new Socket(SERVER_IP, MAIN_PORT);

			oout = new ObjectOutputStream(new DataOutputStream(
					client.getOutputStream()));
			oin = new ObjectInputStream(new DataInputStream(
					client.getInputStream()));
			System.out.println("已与服务器建立连接");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getRivalName() {
		return rivalName;
	}

	void sendResponse(boolean response) {
		try {
			ooutLock.lock();
			System.out.println("向服务器返回响应：" + response);
			oout.writeObject((Boolean) response);
			ooutLock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
			logoutAndCloseSocket();
			ooutLock.unlock();
			System.out.println("向服务器返回响应失败！");
		}
	}

	void sendRequest(Request request) {
		try {
			ooutLock.lock();
			System.out.println("向服务器发送请求：" + request);
			oout.writeObject(request);
			ooutLock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("向服务器发送请求错误！" + request);
			logoutAndCloseSocket();
			ooutLock.unlock();
		}
	}

	boolean sendRequestWithResponse(Request request) {
		boolean response = false;
		System.out.println("向服务器发送请求并接受响应：" + request);
		try {
			
			ooutLock.lock();
			oout.writeObject(request);
			oinLock.lock();
			response = (Boolean) oin.readObject();
			System.out.println("从服务器收到响应：" + response);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("向服务器发送请求错误！" + request);
			logoutAndCloseSocket();
			return response;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("向服务器接受响应错误！" + request);
			logoutAndCloseSocket();
			return response;
		} finally {
			oinLock.unlock();
			ooutLock.unlock();
		}
	}

	void logout() {
		sendRequest(new Request(Operation.LOGOUT_CTS, userName, null, null));
		logoutAndCloseSocket();
		userName = "";
		hallFrame.logout();
		JOptionPane.showMessageDialog(null, "注销成功！", "注销成功",
				JOptionPane.DEFAULT_OPTION);
		while (DataModel.tableModel.getRowCount() > 0) {
			DataModel.tableModel.removeRow(0);
		}
	}

	boolean loginCheck(String userName, String password) {
		//boolean logState = false;
		try {
			boolean response;
			response = sendRequestWithResponse((new Request(
					Operation.LOGIN_CTS, userName, password, null)));
			if (response == true) {
				logState = true;
				this.userName = userName;
				hallFrame.login(userName);
				JOptionPane.showMessageDialog(null, "登录成功！", "登录成功",
						JOptionPane.DEFAULT_OPTION);
				new Thread(this).start();
			} else if (response == false) {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！", "登录失败",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return logState;
	}

	@Override
	public void run() {
		while (logState&&!pause) {
			Request request;
			try {
				oinLock.lock();
				request = (Request) oin.readObject();
				handleRequset(request);
			} catch (Exception e) {
				e.printStackTrace();
				hallFrame.logout();
				System.out.println("连接中断");
				break;
			} finally {
				oinLock.unlock();
			}
		}

	}

	void sendMessage(String message) {
		Request request = new Request(Operation.MESSAGE, message, null, null);
		sendRequest(request);
	}

	void showMessage(String message) {
		hallFrame.messageShowArea.setText(hallFrame.messageShowArea.getText()
				+ "\n[" + new Date() + "]" + message);
	}

	private boolean handleRequset(Request request) {
		Operation op = request.operation;
		if (op == null) {
			pause=true;
			return false;
		}
		switch (op) {
		case MESSAGE:
			showMessage(request.stringArg1);
			break;
		case LOGIN_CTS:
			break;
		case LOGOUT_CTS:
			break;
		case INVITE_GAME_CTS:
			break;
		case INVITE_GAME_STC:
			System.out.println("用户收到邀请~");
			rivalName = request.stringArg1;
			String gameName = request.stringArg2;
			if (gameFrame.isReady) {
				System.out.println("用户接受邀请~");
				sendRequest(new Request(null, null, null, null));
				sendResponse(true);
				sendRequest(new Request(Operation.ULIST_CHANGE, userName, null,
						DataModel.gameState.get(gameName)));
				gameFrame.connectAsClient((String) request.objectArg,(String)request.stringArg2);// 连接到P2P服务器
			} else {
				System.out.println("用户还没准备好，拒绝邀请~");
				sendRequest(new Request(null, null, null, null));
				sendResponse(false);
			}
			break;
		case ULIST_ADD_STC:
			addUser(request.stringArg1, (GameState) request.objectArg);
			break;
		case ULIST_CHANGE:
			changeState(request.stringArg1, (GameState) request.objectArg);
			break;
		case ULIST_REMOVE_STC:
			removeUser(request.stringArg1);
			break;
		case ENTER_ROOM:
			System.out.println("有人进入房间");
			if (firstFlag) {
				firstFlag = false;
				if (request.stringArg1.equals(userName)) {
					gameFrame.isFirst = true;
					gameFrame.listPanel.setVisible(true);
					gameFrame.startGameServer();
				} else
					rivalName = request.stringArg1;

				gameFrame.player1Enter(request.stringArg1);
			} else {
				gameFrame.player2Enter(request.stringArg1);
				if (!request.stringArg1.equals(userName)) {
					rivalName = request.stringArg1;
				}
			}
			break;
		case EXIT_ROOM:
			System.out.println("有人退出房间");
			if (gameFrame.isFirst) {
				gameFrame.player2Exit();
			} else {
				gameFrame.player1Exit();				
			}
			break;
		default:
			break;
		}
		return false;
	}

	private void addUser(String userName, GameState gameState) {
		DataModel.tableModel.addRow(new Object[] { userName, gameState });
	}

	private void removeUser(String userName) {
		int rowNum = findUser(userName);
		if (rowNum != -1)
			DataModel.tableModel.removeRow(rowNum);
	}

	private void changeState(String userName, GameState gameState) {
		int rowNum = findUser(userName);
		if (rowNum != -1)
			DataModel.tableModel.setValueAt(gameState, rowNum, 1);
	}

	private int findUser(String userName) {
		int rowNum = 0;
		int rowCount = DataModel.tableModel.getRowCount();
		String uname;
		for (; rowNum < rowCount; rowNum++) {
			uname = (String) DataModel.tableModel.getValueAt(rowNum, 0);
			if (userName.equals(uname)) {
				return rowNum;
			}
		}
		return -1;

	}

	public void setGameFrame(GameBootFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public void logoutAndCloseSocket() {
		logState = false;
	}

	public boolean isLogged() {
		return logState;
	}
}
