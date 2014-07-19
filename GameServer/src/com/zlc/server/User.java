package com.zlc.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.zlc.common.Config;
import com.zlc.common.Request;

/**
 * 服务器端的用户对象，负责处理服务器进程与单个客户端的socket信息传输，并处理客户端的请求，必要时回复响应。 对于每个对象采用独立的进程。
 * 是观察者模式中的观察者
 * 
 * @author 赵景晨
 * 
 */
public class User implements Runnable, Observer, Config {
	/** 用户名 */
	private String userName;
	/** 该客户的socket对象 */
	private Socket client;

	private ObjectOutputStream oout;
	private ObjectInputStream oin;

	/** 服务器对象，供用户间信息交互时调用方法 */
	Server server;
	/** 用户信息数据库对象 */
	UserDB userDB;
	/** 用户登录状态，默认为未登录（false） */
	private boolean logState = false;
	/** 用户正在进行的游戏状态，默认为空闲（FREE）状态 */
	private GameState gameState = GameState.空闲;

	public GameState getGameState() {
		return gameState;
	}
	/**
	 * 构造函数，建立输入输出流
	 * 
	 * @param server
	 *            服务器对象
	 * @param client
	 *            该客户的socket对象
	 * @param userDB
	 *            用户信息数据库对象
	 */
	public User(Server server, Socket client, UserDB userDB) {
		this.server = server;
		this.client = client;
		this.userDB = userDB;
		try {
			oout = new ObjectOutputStream(new DataOutputStream(
					client.getOutputStream()));
			oin = new ObjectInputStream(new DataInputStream(
					client.getInputStream()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 获取用户名
	 * @return 用户
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 观察者的update方法。转发主服务器对各客户端的请求。
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		sendRequest((Request) arg1);// 转发request
	}

	/**
	 * 向客户端发送响应，同步方法
	 * 
	 * @param response
	 *            要发送的响应对象
	 */
	synchronized void sendResponse(boolean response) {
		try {
			System.out.println("向" + userName + "返回响应：" + response);
			oout.writeObject((Boolean) response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("向" + userName + "返回响应失败！");
			logState = false;
		}
	}

	/**
	 * 向客户端发送请求,同步方法
	 * 
	 * @param request
	 *            要发送的请求对象
	 * @throws InterruptedException
	 */
	synchronized void sendRequest(Request request) {
		try {
			System.out.println("向" + userName + "发送请求：" + request);
			oout.writeObject(request);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("向" + userName + "发送请求错误！" + request);
			logState = false;
		}
	}

	/**
	 * 向客户端发送请求并接受响应
	 * 
	 * @param request
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	boolean sendRequestWithResponse(Request request)
			throws InterruptedException, IOException, ClassNotFoundException {
		boolean response;
		System.out.println("向" + userName + "发送请求并接受响应：" + request);	
		oout.writeObject(request);
		synchronized (oin) {
			response = (Boolean) oin.readObject();
		}
		System.out.println("从" + userName + "收到响应：" + response);
		return response;
	}

	/**
	 * 进程run方法。开始前验证登录状态，登录后持续监听客户端发送的请求，并调用处理请求的方法对请求进行处理。
	 * 
	 */
	@Override
	public void run() {
		while (true) {
			logState = checkLogin();
			if (logState) {
				sendResponse(true);
				sendOnlineUserList();
				server.addOnlineUser(this);
				break;
			} else {
				sendResponse(false);
			}
		}
		System.out.println(userName + "已登录！");

		while (logState) {
			Request request;
			try {
				synchronized (oin) {
					request = (Request) oin.readObject();
				}
				System.out.println("接收到来自"+userName+"的请求"+request);
				handleRequset(request);
			} catch (Exception e) {
				//e.printStackTrace();
				server.removeOnlineUser(this);
				System.out.println(client + "失去连接");
				break;
			}
		}
	}

	/**
	 * 向客户端发送当前在线用户列表，通过迭代的发送增加用户请求完成。同步方法。
	 */
	synchronized private void sendOnlineUserList() {
		for (User u : server.getOnlineUserList()) {
			sendRequest(new Request(Operation.ULIST_ADD_STC, u.userName, null,
					u.gameState));
		}
	}

	/**
	 * 处理客户端发送到服务器的请求。通过判断不同的请求操作，执行或调用函数执行相关的操作。
	 * 
	 * @param request
	 *            接收到的客户端请求
	 * @return 有必要时返回请求处理是否成功
	 * @throws InterruptedException
	 */
	private boolean handleRequset(Request request) throws InterruptedException {
		Operation op = request.operation;
		
		if (op == null) 
			Thread.sleep(8000);
		
		switch (op) {
		case MESSAGE:// 收到客户端发来的消息，向所有在线用户转发
			server.relayMessage(request.stringArg1);
			break;
		case LOGIN_CTS:// 用户登录
			userName=request.stringArg1;
			return matchPassword(userName, request.stringArg2);
		case LOGOUT_CTS:// 用户注销
			server.removeOnlineUser(this);
			System.out.println(userName + "已注销");
			logState = false;
			break;
		case INVITE_GAME_CTS:// 向对应用户转发客户端发来的游戏连接邀请，并回复响应
			boolean invited = server.sendInvite(request, this);
			if (invited) {
				sendRequest(new Request(null, null, null, null));
				sendResponse(true);
			} else {
				sendRequest(new Request(null, null, null, null));
				sendResponse(false);
			}
			break;
		case INVITE_GAME_STC:
			break;
		case ULIST_ADD_STC:
			break;
		case ULIST_CHANGE:// 用户请求改变游戏状态
			gameState = (GameState) request.objectArg;
			server.changeOnlineUser(this);
			break;
		case ULIST_REMOVE_STC:
			break;
		case ENTER_ROOM:
			server.enterRoom(request, this);
			break;
		case EXIT_ROOM:
			server.exitRoom(request, this);
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * 在线程监听启动前，接收用户的登录请求并调用相关方法进行验证。
	 * 
	 * @return 登录验证是否成功
	 */
	private boolean checkLogin() {
		try {
			Request request = (Request) oin.readObject();
			if (request.operation == Operation.LOGIN_CTS) {
				if (handleRequset(request)) {
					return true;
				} else {
					userName = "";
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 进行用户名和密码的匹配
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 匹配是否成功
	 */
	private boolean matchPassword(String userName, String password) {
		/*
		 * if (userName == null || userName == "" || password == null) return
		 * false; String realPsw = userDB.selectPassWowdByName(userName); if
		 * (realPsw == null) return false; else if (realPsw.equals(password))
		 * return true; else return false;
		 */
		return true;
	}
}
