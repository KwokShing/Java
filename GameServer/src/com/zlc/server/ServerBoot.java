package com.zlc.server;
/**
 * 程序主类，用于连接数据库并启动服务器
 * @author 赵景晨
 *
 */
public class ServerBoot {
	public static void main(String[] args) {
		//UserDB userDB = new UserDB();
		UserDB userDB=null;
		new Thread(new Server(userDB)).start();
	}
}
