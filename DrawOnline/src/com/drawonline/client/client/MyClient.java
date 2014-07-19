package com.drawonline.client.client;

import java.awt.Point;

import it.gotoandplay.smartfoxclient.ISFSEventListener;
import it.gotoandplay.smartfoxclient.SFSEvent;
import it.gotoandplay.smartfoxclient.SmartFoxClient;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.drawonline.client.myContainer.MyDrawContainer;
import com.drawonline.client.ui.LoginFrame;
import com.drawonline.client.ui.MainFrame;

/**
 * 书写一个与服务端交互的类
 * 
 * @author lw
 * 
 */
public class MyClient implements ISFSEventListener {
	
	private SmartFoxClient sfs; // 声明客户端对象
	
	public String nickName;
	
	public MyClient(String nickName) {
		sfs = new SmartFoxClient(true); // 初始化客户端对象
		/** 初始化监听事件 **/
		sfs.addEventListener(SFSEvent.onObjectReceived, this);
		sfs.addEventListener(SFSEvent.onConnection, this); // 连接事件
		sfs.addEventListener(SFSEvent.onLogin, this); // 登录事件
		sfs.addEventListener(SFSEvent.onRoomListUpdate, this); // 修改房间事件
		sfs.addEventListener(SFSEvent.onJoinRoom, this); // 加入房间事件
		sfs.addEventListener(SFSEvent.onExtensionResponse, this); // 接收服务端消息事件
		/** 加载外部xml **/
		sfs.loadConfig("config.xml", false);
		/** 连接服务端 **/
		sfs.connect(sfs.ipAddress, sfs.port); // 这句话要触发 handleEvent事件 事件名是
		this.nickName = nickName;
	}

	/** 事件监听方法 **/
	public void handleEvent(SFSEvent arg0) {
		String eventName = arg0.getName(); // 当前监听事件名
		/** 连接事件监听 **/
		if (eventName.equals(SFSEvent.onConnection)) {
			if (arg0.getParams().getBool("success")) { // 登录成功
				sfs.login(sfs.defaultZone, nickName, "123");// 参数分别表示
				System.out.println("连接成功");
			} else {
				JOptionPane.showMessageDialog(null, "登录失败   ip或者端口号错误");
			}
			/** 登录监听事件 **/
		} else if (eventName.equals(SFSEvent.onLogin)) {
			if (arg0.getParams().getBool("success")) { // 用户名唯一，连接服务器名字存在 连接成功
				System.out.println("登陆成功");
			} else {
				JOptionPane.showMessageDialog(null, "登录失败   可能是名字已经存在");
			}

			/** 加入房间监听事件 **/
		} else if (eventName.equals(SFSEvent.onJoinRoom)) {
			/** 房间列表改变事件 **/
		} else if (eventName.equals(SFSEvent.onRoomListUpdate)) {
			sendData("login",nickName);
			/** 服务器发送信息监听事件 **/
		} else if (eventName.equals(SFSEvent.onExtensionResponse)) {
			JSONObject jsoObj = (JSONObject)arg0.getParams().get("dataObj");
			if(!jsoObj.isNull("login")){
				try{
					JSONArray jsoArray = jsoObj.getJSONArray("login");
					System.out.println(jsoArray);
					LoginFrame.loginFrame.setVisible(false);
					new MainFrame(jsoArray,nickName);
					MainFrame.mainFrame.appendStr("系统消息:欢迎您来到XXXXX");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else if(!jsoObj.isNull("point")){
				try {
					String pointStr = jsoObj.get("point").toString();
					int x = Integer.parseInt(pointStr.split(",")[0]);
					int y = Integer.parseInt(pointStr.split(",")[1]);
					Point point = new Point(x,y);
					MainFrame.mainFrame.updateCanvs(point);
				} catch (JSONException e) {
				}
			}else if(!jsoObj.isNull("cz")){
				try {
					String pointStr = jsoObj.get("cz").toString();
					int x = Integer.parseInt(pointStr.split(",")[0]);
					int y = Integer.parseInt(pointStr.split(",")[1]);
					Point point = new Point(x,y);
					MainFrame.mainFrame.clearCanvs(point);
				} catch (JSONException e) {
				}
			}else if(!jsoObj.isNull("message_login")){
				try{
					String pointStr = jsoObj.get("message_login").toString();
					MainFrame.mainFrame.appendStr("系统消息:欢迎【"+pointStr+"】进入房间");
					MainFrame.mainFrame.appendUser(pointStr);
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("message_logout")){
				try{
					String pointStr = jsoObj.get("message_logout").toString();
					MainFrame.mainFrame.appendStr("系统消息:【"+pointStr+"】离开了房间");
					MainFrame.mainFrame.remove(pointStr);
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("ready")){
				try{
					String pointStr = jsoObj.get("ready").toString();
					MainFrame.mainFrame.appendStr("系统消息:【"+pointStr+"】已经准备");
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("begin")){
				try{
					String pointStr = jsoObj.get("begin").toString();
					MainFrame.mainFrame.appendStr("系统消息:游戏开始...");
					MainFrame.mainFrame.appendStr("系统消息:当前玩家【"+pointStr+"】手拿画笔,让我们来猜他画的什么吧");
					MainFrame.mainFrame.updatePreColor(pointStr);
					if(LoginFrame.loginFrame.getNickName().equals(pointStr)){
						MainFrame.mainFrame.initDrawerData();
						MainFrame.mainFrame.initDrawerUI();
						MyDrawContainer.allowDraw = true;
						MainFrame.mainFrame.setEnable(false);
					}else{
						MainFrame.mainFrame.setEnable(true);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else if(!jsoObj.isNull("answer")){
				try{
					String result = jsoObj.get("answer").toString();
					MainFrame.mainFrame.initOthersData(Integer.parseInt(result));
					MainFrame.mainFrame.initOtherUI();
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("guess")){
				try{
					String result = jsoObj.get("guess").toString();
					System.out.println(result);
					String nickName = result.split(",")[0];
					String msg = result.split(",")[1];
					if(MainFrame.mainFrame.isGuess(msg)){
						MainFrame.mainFrame.appendStr("【"+nickName+"】猜对了正确的答案");
						MainFrame.mainFrame.setEnable(true);
					}else{
						MainFrame.mainFrame.appendStr("【"+nickName+"】说:"+msg);
					}
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else if(!jsoObj.isNull("NULL")){
				MainFrame.mainFrame.setNull();
			}
		}
	}

	/**
	 * 这个方法是自定义的发送数据的方法 想要发数据给其他客户端 就调用这里 先发送给服务端 然后再发送给同房间的人 如果发送的数据比较特殊
	 * 可以重载这个方法 但是这里不支持 发送一个实体类 只能把数据拼接成字符串发送 接收的数据也是字符串类型的 我给你直接解析成 实体类也ok
	 * 参数1：指令 参数2：发送内容
	 * **/
	public void sendData(String cmd, Object obj) {
		sfs.sendXtMessage("drawOnline", cmd, new String[]{obj.toString()});
	}
}
