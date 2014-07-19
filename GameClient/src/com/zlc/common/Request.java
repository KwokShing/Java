package com.zlc.common;

import java.io.Serializable;
/**
 * 定义请求对象，一个命令操作和三个可选参数。请求格式如下所示：<br>
 * MESSAGE 消息内容 null null<br>
 * LOGIN_CTS 用户名 密码 null<br>
 * LOGOUT_CTS 用户名 null<br>
 * ULIST_ADD_STC 用户名 null 游戏状态<br>
 * ULIST_REMOVE_STC 用户名 null null<br>
 * ULIST_CHANGE 用户名 null 游戏状态<br>
 * INVITE_GAME_CTS 目标用户 游戏名 源用户IP<br>
 * INVITE_GAME_STC 源用户 游戏名 源用户IP<br>
 * ENTER_ROOM 用户名 游戏名<br>
 * EXIT_ROOM 用户名 游戏名
 * @author 赵景晨
 *
 */
public class Request implements Config,Serializable{

	private static final long serialVersionUID = 4291335251912040643L;
	/**命令操作*/
	public Operation operation;
	/**字符串参数1*/
	public String stringArg1;
	/**字符串参数2*/
	public String stringArg2;
	/**object参数，可以灵活使用*/
	public Object objectArg;
	/**
	 * 构造函数，初始化请求对象
	 * @param operation 命令操作
	 * @param stringArg1 参数1
	 * @param stringArg2 参数2
	 * @param objectArg 参数3
	 */
	public Request(Operation operation, String stringArg1, String stringArg2,
			Object objectArg) {
		this.operation = operation;
		this.stringArg1 = stringArg1;
		this.stringArg2 = stringArg2;
		this.objectArg = objectArg;
	}
	/**
	 * 重载的toString方法，输出请求的命令和各个参数
	 */
	@Override
	public String toString() {
		return operation+" Arg1:"+stringArg1+" Arg2:"+stringArg2+" Arg3:"+objectArg; 
	}
	
}
