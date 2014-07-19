package com.zlc.common;

/**
 * 配置项接口，由于定义一些常数和枚举类型。服务器端和客户端使用相同的配置项接口
 * 
 * @author 赵景晨
 * 
 */
public interface Config {
	/**
	 * 主服务器端口号，主服务器用于用户发送消息、获取在线用户列表和促成游戏连接
	 */
	final public int MAIN_PORT = 8386;
	/**
	 * 泡泡堂游戏服务器端口
	 */
	final public int PPT_PORT = 8387;
	/**
	 * 你画我猜游戏服务器端口
	 */
	final public int NHWC_PORT = 8388;
	/**
	 * 扫雷游戏服务器端口
	 */
	final public int SL_PORT = 8389;
	/**
	 * 俄罗斯方块服务器端口
	 */
	final public int ELSFK_PORT = 8390;
	/**
	 * 服务器IP地址
	 */
	final public String SERVER_IP = "127.0.0.1";

	/**
	 * 游戏状态，表示用户正在进行的游戏或空闲状态
	 * 
	 * @author 赵景晨
	 */
	public enum GameState {
		空闲, 泡泡堂, 你画我猜, 扫雷, 俄罗斯方块
	}

	/**
	 * 请求的操作类型，CTS表示从客户端发往服务器的请求，STC表示从服务器发往客户端的请求，默认为双向都能使用
	 * 
	 * @author 赵景晨
	 */
	public enum Operation {
		MESSAGE, LOGIN_CTS, LOGOUT_CTS, ULIST_ADD_STC, ULIST_REMOVE_STC, ULIST_CHANGE, 
		INVITE_GAME_CTS, INVITE_GAME_STC, ENTER_ROOM,EXIT_ROOM
	}
}
