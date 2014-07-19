package com.zlc.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import com.zlc.common.Config;
import com.zlc.common.Request;
/**
 * 主服务器对象，用于建立User对象并进行用户间的通信，是观察者模式中的被观察者
 * 
 * @author 赵景晨
 * 
 */
public class Server extends Observable implements Runnable, Config {
	/** 服务器socket对象 */
	ServerSocket serverSocket;
	/** 当前在线用户列表 */
	private List<User> onlineUserList = new LinkedList<User>();
	/** 用户信息数据库 */
	private UserDB userDB;
	/** 泡泡堂玩家列表 */
	private Vector<User> pptUser = new Vector<User>();
	/** 你画我猜玩家列表 */
	private Vector<User> nhwcUser = new Vector<User>();

	/**
	 * 构造方法，建立服务器socket
	 * 
	 * @param userDB
	 *            用户信息数据库对象
	 */
	public Server(UserDB userDB) {
		try {
			serverSocket = new ServerSocket(MAIN_PORT);
			System.out.println("服务器"
					+ InetAddress.getLocalHost().getHostAddress() + "已启动！");
			System.out.println(serverSocket);
			this.userDB = userDB;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 获取在线用户列表
	 * @return
	 */
	public List<User> getOnlineUserList() {
		return onlineUserList;
	}
	
	/**
	 * 进程run方法。接收客户端的socket连接并创建不同的User对象进程处理与每个客户的信息交换
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Socket client = serverSocket.accept();
				User newUser = new User(this, client, userDB);
				System.out.println(client + "已连接!");
				new Thread(newUser).start();
			} catch (IOException ex) {
				ex.printStackTrace();
				break;
			}
		}
	}

	/**
	 * 生成请求，告知所有观察者在用户列表中增加用户。同步方法。
	 * 
	 * @param user
	 *            需要增加的用户
	 */
	synchronized public void addOnlineUser(User user) {
		Request request = new Request(Operation.ULIST_ADD_STC, user.getUserName(),
				null, user.getGameState());
		onlineUserList.add(user);
		addObserver(user);
		setChanged();
		notifyObservers(request);
	}

	/**
	 * 生成请求，告知所有观察者在用户列表中移除用户。同步方法
	 * 
	 * @param user
	 *            需要移除的用户
	 */
	synchronized public void removeOnlineUser(User user) {
		Request request = new Request(Operation.ULIST_REMOVE_STC,
				user.getUserName(), null, null);
		onlineUserList.remove(user);
		deleteObserver(user);
		setChanged();
		notifyObservers(request);
	}

	/**
	 * 生成请求，告知所有观察者在修改用户列表中某用户的游戏状态。同步方法
	 * 
	 * @param user
	 *            需要修改的用户
	 */
	synchronized public void changeOnlineUser(User user) {
		Request request = new Request(Operation.ULIST_CHANGE, user.getUserName(),
				null, user.getGameState());
		setChanged();
		notifyObservers(request);
	}

	/**
	 * 生成请求，告知所有观察者在向客户端发送某条消息。同步方法
	 * 
	 * @param message
	 */
	synchronized public void relayMessage(String message) {
		setChanged();
		notifyObservers(new Request(Operation.MESSAGE, message, null, null));
	}

	/**
	 * 向目的用户转发来自某用户的游戏邀请
	 * 
	 * @param request
	 *            游戏邀请的请求
	 * @param source
	 *            邀请的来源用户
	 * @return 返回邀请是否被接受
	 * @throws InterruptedException
	 */
	synchronized public boolean sendInvite(Request request, User source)
			throws InterruptedException {
		String gameName = request.stringArg2;
		boolean response = false;

		System.out.println("服务器牵线ing...");
		Request newRequest = new Request(Operation.INVITE_GAME_STC,
				source.getUserName(), gameName, request.objectArg);
		try {
			if (gameName.equals("泡泡堂")) {
				response = pptUser.get(1).sendRequestWithResponse(newRequest);
				if (response)
					pptUser.clear();
			} else if (gameName.equals("你画我猜")) {
				response = nhwcUser.get(1).sendRequestWithResponse(newRequest);
				if (response)
					nhwcUser.clear();
			}

		} catch (ClassNotFoundException | InterruptedException | IOException e) {
			e.printStackTrace();
			throw new InterruptedException();
		}
		System.out.println("受邀请用户的响应为：" + response);
		return response;

	}

	/**
	 * 向游戏房间内用户发送用户进入房间的消息，并向新进入房间的用户发送房间内已有用户的信息
	 * 
	 * @param request
	 *            进入房间消息
	 * @param user
	 *            消息源
	 * @throws InterruptedException
	 */
	synchronized void enterRoom(Request request, User user)
			throws InterruptedException {
		String gameName = request.stringArg2;

		if (gameName.equals("泡泡堂")) {
			System.out.println(user.getUserName() + "进入了泡泡堂游戏房间");

			if (pptUser.isEmpty()) {
				user.sendRequest(request);
				pptUser.add(user);
			} else {
				user.sendRequest(new Request(Operation.ENTER_ROOM, pptUser
						.lastElement().getUserName(), gameName, null));
				user.sendRequest(request);
				pptUser.lastElement().sendRequest(request);
				pptUser.add(user);
			}

		} else if (gameName.equals("你画我猜")) {
			System.out.println(user.getUserName() + "进入了你画我猜游戏房间");

			if (nhwcUser.isEmpty()) {
				user.sendRequest(request);
				nhwcUser.add(user);
			} else {
				user.sendRequest(new Request(Operation.ENTER_ROOM, nhwcUser
						.lastElement().getUserName(), gameName, null));
				user.sendRequest(request);
				nhwcUser.lastElement().sendRequest(request);
				nhwcUser.add(user);
			}
		}

	}

	/**
	 * 向向游戏房间内用户发送用户离开房间的消息
	 * 
	 * @param request
	 *            离开房间消息
	 * @param user
	 *            消息源
	 * @throws InterruptedException
	 */
	synchronized void exitRoom(Request request, User user)
			throws InterruptedException {
		String gameName = request.stringArg2;

		if (gameName.equals("泡泡堂")) {
			System.out.println(user.getUserName() + "退出了泡泡堂游戏房间");
			pptUser.remove(user);
			if (!pptUser.isEmpty()) {
				pptUser.lastElement().sendRequest(request);
			}
		} else if (gameName.equals("你画我猜")) {
			System.out.println(user.getUserName() + "退出了你画我猜游戏房间");
			nhwcUser.remove(user);
			if (!nhwcUser.isEmpty()) {
				user.sendRequest(request);
				nhwcUser.lastElement().sendRequest(request);
			}
		}
	}
}
