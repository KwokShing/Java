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
 * ��дһ�������˽�������
 * 
 * @author lw
 * 
 */
public class MyClient implements ISFSEventListener {
	
	private SmartFoxClient sfs; // �����ͻ��˶���
	
	public String nickName;
	
	public MyClient(String nickName) {
		sfs = new SmartFoxClient(true); // ��ʼ���ͻ��˶���
		/** ��ʼ�������¼� **/
		sfs.addEventListener(SFSEvent.onObjectReceived, this);
		sfs.addEventListener(SFSEvent.onConnection, this); // �����¼�
		sfs.addEventListener(SFSEvent.onLogin, this); // ��¼�¼�
		sfs.addEventListener(SFSEvent.onRoomListUpdate, this); // �޸ķ����¼�
		sfs.addEventListener(SFSEvent.onJoinRoom, this); // ���뷿���¼�
		sfs.addEventListener(SFSEvent.onExtensionResponse, this); // ���շ������Ϣ�¼�
		/** �����ⲿxml **/
		sfs.loadConfig("config.xml", false);
		/** ���ӷ���� **/
		sfs.connect(sfs.ipAddress, sfs.port); // ��仰Ҫ���� handleEvent�¼� �¼�����
		this.nickName = nickName;
	}

	/** �¼��������� **/
	public void handleEvent(SFSEvent arg0) {
		String eventName = arg0.getName(); // ��ǰ�����¼���
		/** �����¼����� **/
		if (eventName.equals(SFSEvent.onConnection)) {
			if (arg0.getParams().getBool("success")) { // ��¼�ɹ�
				sfs.login(sfs.defaultZone, nickName, "123");// �����ֱ��ʾ
				System.out.println("���ӳɹ�");
			} else {
				JOptionPane.showMessageDialog(null, "��¼ʧ��   ip���߶˿ںŴ���");
			}
			/** ��¼�����¼� **/
		} else if (eventName.equals(SFSEvent.onLogin)) {
			if (arg0.getParams().getBool("success")) { // �û���Ψһ�����ӷ��������ִ��� ���ӳɹ�
				System.out.println("��½�ɹ�");
			} else {
				JOptionPane.showMessageDialog(null, "��¼ʧ��   �����������Ѿ�����");
			}

			/** ���뷿������¼� **/
		} else if (eventName.equals(SFSEvent.onJoinRoom)) {
			/** �����б�ı��¼� **/
		} else if (eventName.equals(SFSEvent.onRoomListUpdate)) {
			sendData("login",nickName);
			/** ������������Ϣ�����¼� **/
		} else if (eventName.equals(SFSEvent.onExtensionResponse)) {
			JSONObject jsoObj = (JSONObject)arg0.getParams().get("dataObj");
			if(!jsoObj.isNull("login")){
				try{
					JSONArray jsoArray = jsoObj.getJSONArray("login");
					System.out.println(jsoArray);
					LoginFrame.loginFrame.setVisible(false);
					new MainFrame(jsoArray,nickName);
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��ӭ������XXXXX");
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
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��ӭ��"+pointStr+"�����뷿��");
					MainFrame.mainFrame.appendUser(pointStr);
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("message_logout")){
				try{
					String pointStr = jsoObj.get("message_logout").toString();
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��"+pointStr+"���뿪�˷���");
					MainFrame.mainFrame.remove(pointStr);
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("ready")){
				try{
					String pointStr = jsoObj.get("ready").toString();
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��"+pointStr+"���Ѿ�׼��");
				}catch (Exception e) {
				}
			}else if(!jsoObj.isNull("begin")){
				try{
					String pointStr = jsoObj.get("begin").toString();
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��Ϸ��ʼ...");
					MainFrame.mainFrame.appendStr("ϵͳ��Ϣ:��ǰ��ҡ�"+pointStr+"�����û���,����������������ʲô��");
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
						MainFrame.mainFrame.appendStr("��"+nickName+"���¶�����ȷ�Ĵ�");
						MainFrame.mainFrame.setEnable(true);
					}else{
						MainFrame.mainFrame.appendStr("��"+nickName+"��˵:"+msg);
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
	 * ����������Զ���ķ������ݵķ��� ��Ҫ�����ݸ������ͻ��� �͵������� �ȷ��͸������ Ȼ���ٷ��͸�ͬ������� ������͵����ݱȽ�����
	 * ��������������� �������ﲻ֧�� ����һ��ʵ���� ֻ�ܰ�����ƴ�ӳ��ַ������� ���յ�����Ҳ���ַ������͵� �Ҹ���ֱ�ӽ����� ʵ����Ҳok
	 * ����1��ָ�� ����2����������
	 * **/
	public void sendData(String cmd, Object obj) {
		sfs.sendXtMessage("drawOnline", cmd, new String[]{obj.toString()});
	}
}
