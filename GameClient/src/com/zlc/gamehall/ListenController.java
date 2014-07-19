package com.zlc.gamehall;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.zlc.exception.PassWordEmptyException;
import com.zlc.exception.UserNameEmptyException;
/**
 * 游戏大厅的监听器集合类，该类在游戏大厅主窗体构造时进行构造，生成各监听器对象
 * @author 赵景晨
 *
 */
public class ListenController {
	/**监听器所监听的游戏大厅窗体*/
	HallFrame hallFrame;
	/** 登录按钮监听器*/
	static LoginButtonListener loginButtonListener;
	/**注销按钮监听器*/
	static LogoutButtonListener logoutButtonListener;
	/**退出按钮监听器*/
	static ExitButtonListener exitButtonListener;
	/**游戏目录树选择监听器*/
	static TreeSelectedListener treeSelectedListener;
	/**发送消息按钮监听器*/	
	static SendMessageButonListener sendMessageButonListener;

	/**
	 * 构造函数，创建各监听器对象
	 * @param hallFrame 监听游戏大厅主窗体
	 */
	public ListenController(HallFrame hallFrame) {
		this.hallFrame = hallFrame;
		 loginButtonListener = new LoginButtonListener();
		 treeSelectedListener = new TreeSelectedListener();
		 sendMessageButonListener = new SendMessageButonListener();
		 logoutButtonListener = new LogoutButtonListener();
		exitButtonListener = new ExitButtonListener();
	}
	/**
	 * 内部类，发送消息按钮监听器
	 * @author 赵景晨
	 *
	 */
	class SendMessageButonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			String text = hallFrame.textPane.getText();
			if (text.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入消息内容", "消息发送错误",
						JOptionPane.ERROR_MESSAGE);
			} else if (hallFrame.socketController.isLogged()==false) {
				JOptionPane.showMessageDialog(null, "请登录后再发送消息", "消息发送错误",
						JOptionPane.ERROR_MESSAGE);
			} else {
				hallFrame.socketController
						.sendMessage(hallFrame.socketController.getUserName() + "："
								+ text);
				int height = 20;
				Point p = new Point();
				p.setLocation(0, hallFrame.messageShowArea.getLineCount()
						* height);
				hallFrame.messageAreaScrollPane.getViewport().setViewPosition(p);//自动滚动
				hallFrame.textPane.setText("");
			}
		}
	}

	class LogoutButtonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			hallFrame.socketController.logout();
		}
	}

	class ExitButtonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			if (hallFrame.socketController != null)
				hallFrame.socketController.logout();
			hallFrame.dispose();
		}
	}

	class LoginButtonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			String userName = "", password = "";
			while (true) {
				try {
					userName = JOptionPane.showInputDialog(null, "请输入用户名",
							"用户名", JOptionPane.DEFAULT_OPTION);
					if (userName.equals(""))
						throw new UserNameEmptyException();
					while (true) {
						try {
							password = JOptionPane.showInputDialog(null,
									"请输入密码", "密码", JOptionPane.DEFAULT_OPTION);
							if ((password.equals("")))
								throw new PassWordEmptyException();
							break;
						} catch (PassWordEmptyException e) {
							e.action();
							continue;
						}
					}
					break;
				} catch (UserNameEmptyException e) {
					e.action();
					continue;
				}
			}
			hallFrame.socketController = new SocketController(hallFrame);
			hallFrame.socketController.loginCheck(userName, password);
		}
	}

	class TreeSelectedListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			String str = selectNode.toString();
			String imagePath = DataModel.imagePath.get(str);
			String gameInfo = DataModel.gameInfo.get(str);
			if (gameInfo == null)
				gameInfo = "";
			if (imagePath != null) {
				hallFrame.addGameTab(imagePath, str, gameInfo);
			}
		}
	}
	
}