package com.drawonline.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONException;
import com.drawonline.client.bean.Kind;
import com.drawonline.client.myContainer.MyDrawContainer;
import com.drawonline.client.myContainer.MyMessageContainer;
import com.drawonline.client.myContainer.MyShowUserConainer;
import com.drawonline.client.tool.Tool;
/**
 * xxx
 * @author lw
 *
 */
public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JSONArray jsoArray;		//
	
	public static MainFrame mainFrame;
	
	private MyDrawContainer pnlMain;
	
	private MyMessageContainer pnlRight;
	
	private MyShowUserConainer pnlBottom;
	
	private List<Kind> listKinds;
	
	private Kind drawKind;
	
	public MainFrame(JSONArray jsoArray,String nickName){
		this.jsoArray = jsoArray;
		setTitle("µÇÂ½ÓÃ»§¡¾"+nickName+"¡¿");
		initUI();
		initLayout();
		setVisible(true);
		mainFrame = this;
		listKinds = Tool.getAllKinds();
	}
	//
	public void initDrawerData(){
		int drawId = randDraw();
		LoginFrame.loginFrame.sendsmg("answer",drawId+"");
		for(int i = 0 ; i < listKinds.size() ; i++){
			Kind kind = listKinds.get(i);
			if(kind.getId() == drawId){
				drawKind = kind;
				break;
			}
		}
	}
	//
	public void initOthersData(int drawId){
		for(int i = 0 ; i < listKinds.size() ; i++){
			Kind kind = listKinds.get(i);
			if(kind.getId() == drawId){
				drawKind = kind;
				break;
			}
		}
	}
	//
	public void initDrawerUI(){
		pnlMain.initMidi(drawKind.getName(), true,drawKind.getName());
	}
	//
	public void initOtherUI(){
		pnlMain.initMidi(drawKind.getType(), false,drawKind.getName());
	}
	//
	private void initUI(){
		setSize(new Dimension(800,600));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				LoginFrame.loginFrame.sendsmg("logout", LoginFrame.loginFrame.getNickName());
				System.exit(0);
			}
			
		});
	}
	//
	private void initLayout(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlMain = new MyDrawContainer();
		layoutMain(pnlMain);
		pnlBottom = new MyShowUserConainer();
		layoutBottom(pnlBottom);
		pnlRight = new MyMessageContainer();
		layoutLeft(pnlRight);
		add(pnlMain);
		add(pnlRight);
		add(pnlBottom);
	}
	//
	private void layoutMain(JPanel pnl){
		pnl.setPreferredSize(new Dimension(600,400));
		pnl.setBackground(Color.white);
	}
	//
	private void layoutLeft(JPanel pnl){
		pnl.setPreferredSize(new Dimension(180,400));
	}
	//
	private void layoutBottom(MyShowUserConainer pnl){
		pnl.setPreferredSize(new Dimension(785,155));
		for(int i = 0 ; i < jsoArray.length() ; i++){
			try {
				pnl.addUser(jsoArray.getString(i));
			} catch (JSONException e) {
				continue;
			}
		}
	}
	
	public void updateCanvs(Point point){
		pnlMain.paintImg(point);
		pnlMain.repaint();
	}
	
	public void clearCanvs(Point point){
		pnlMain.clearPaintImg(point);
		pnlMain.repaint();
	}
	
	public void appendStr(String msg){
		pnlRight.appendMsg(msg);
	}
	
	public void appendUser(String nickName){
		pnlBottom.addUser(nickName);
		try {
			jsoArray.put(nickName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pnlBottom.repaint();
		pnlBottom.validate();
	}
	
	public void remove(String nickName){
		pnlBottom.removeUser();
		JSONArray jsoFriend = new JSONArray();
		for(int i = 0 ;i < jsoArray.length(); i ++){
			try{
				String listNick = jsoArray.getString(i);
				if(!listNick.equals(nickName)){
					jsoFriend.put(listNick);
					pnlBottom.addUser(listNick);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		jsoArray = jsoFriend;
	}
	
	public void updatePreColor(String nickName){
		pnlBottom.updatePreColor(nickName);
	}
	
	public int randDraw(){
		Random random = new Random();
		return random.nextInt(9) + 1;
	}
	
	public boolean isGuess(String guess){
		if(this.drawKind.getName().equals(guess)){
			return true;
		}
		return false;
	}
	
	public void setEnable(boolean result){
		pnlRight.setEable(result);
	}
	
	public void setNull(){
		pnlMain.setNull();
	}
}
