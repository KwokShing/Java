/**
 * @author 赵景晨
 * @version 1.5
 */
package com.zlc.gamehall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.zlc.common.Config.Operation;
import com.zlc.common.Request;
import com.zlc.drawSomething.DrawMain;
import com.zlc.paopaotang.MapFrame;

public class GameBootFrame extends JFrame implements MouseListener,
		ListSelectionListener {

	private static final long serialVersionUID = 3628205713823899810L;
	private JPanel contentPane = new JPanel();
	private String gameName;
	private String userName;
	SocketController socketController = null;
	P2PGameServer gameServer;
	private Socket rivalSocket;
	boolean isReady = false;
	boolean isFirst = false;

	private int listSelected = 0;
	private JButton startButton;
	JPanel listPanel = new JPanel();
	JLabel player1Icon = new JLabel();
	JLabel player2Icon = new JLabel();
	JLabel player1Name = new JLabel();
	JLabel player2Name = new JLabel();

	public GameBootFrame(final SocketController socketController,
			String gameName) {

		this.gameName = gameName;
		this.userName = socketController.getUserName();
		this.socketController = socketController;

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 628, 443);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				isReady = false;
				try {
					socketController.sendRequest(new Request(
							Operation.EXIT_ROOM, userName, userName, null));
					if (gameServer != null)
						gameServer.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		initTitle();
		initUserPanel();
		initUserList();

		initContent();

		socketController.setGameFrame(this);
		socketController.sendRequest(new Request(Operation.ENTER_ROOM,
				userName, gameName, null));

	}

	private void initButton() {
		startButton = new JButton("准备");
		startButton.setForeground(Color.RED);
		startButton.setFont(new Font("幼圆", Font.BOLD, 20));
		startButton.setPreferredSize(new Dimension(125, 80));
		startButton.addMouseListener(this);
	}

	private void initUserList() {
		JTable userList = new JTable(DataModel.tableModel);
		JScrollPane scrollPane = new JScrollPane(userList);
		scrollPane.setPreferredSize(new Dimension(125, 402));
		contentPane.add(scrollPane, BorderLayout.EAST);
	}

	private void initUserPanel() {
		JPanel users = new JPanel();
		users.setLayout(new BorderLayout(0, 0));

		JPanel usersNorth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) usersNorth.getLayout();
		flowLayout.setHgap(125);
		flowLayout.setAlignment(FlowLayout.LEFT);

		// 玩家标识
		JLabel lblNewLabel = new JLabel("玩家1");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("华文楷体", Font.BOLD, 20));
		usersNorth.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("玩家2");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("华文楷体", Font.BOLD, 20));
		usersNorth.add(lblNewLabel_1);
		users.add(usersNorth, BorderLayout.NORTH);

		// 添加用户图片
		JPanel usersCenter = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) usersCenter.getLayout();
		flowLayout_2.setHgap(100);
		flowLayout_2.setAlignment(FlowLayout.LEFT);

		player1Icon = new JLabel(new ImageIcon(
				DataModel.imagePath.get("waitPlayer")));
		usersCenter.add(player1Icon);
		player2Icon = new JLabel(new ImageIcon(
				DataModel.imagePath.get("waitPlayer")));
		usersCenter.add(player2Icon);
		users.add(usersCenter, BorderLayout.CENTER);

		// 玩家状态和用户名
		JPanel usersSouth = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) usersSouth.getLayout();
		flowLayout_1.setHgap(80);
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		player1Name = new JLabel("等待玩家进入");
		player1Name.setForeground(Color.MAGENTA);
		player1Name.setFont(new Font("华文楷体", Font.BOLD, 20));
		usersSouth.add(player1Name);

		player2Name = new JLabel("等待玩家进入");
		player2Name.setForeground(Color.MAGENTA);
		player2Name.setFont(new Font("华文楷体", Font.BOLD, 20));
		usersSouth.add(player2Name);
		users.add(usersSouth, BorderLayout.SOUTH);

		contentPane.add(users, BorderLayout.CENTER);
	}

	private void initContent() {
		JPanel startPanel = new JPanel();
		startPanel.setPreferredSize(new Dimension(10, 100));
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.X_AXIS));

		if (gameName.equals("泡泡堂")) {

			FlowLayout flowLayout_3 = (FlowLayout) listPanel.getLayout();
			flowLayout_3.setAlignment(FlowLayout.LEFT);
			// LAYOUT
			listPanel.setAlignmentX(RIGHT_ALIGNMENT);
			listPanel.setBorder(new TitledBorder("请选择地图："));

			DefaultListModel<String> mapListModel = new DefaultListModel<String>();
			mapListModel.addElement("小区");
			mapListModel.addElement("冰雪世界");

			JList<String> mapList = new JList<String>(mapListModel);
			mapList.setForeground(Color.DARK_GRAY);
			mapList.addListSelectionListener(this);
			mapList.setFont(new Font("微软雅黑", Font.PLAIN, 16));

			listPanel.add(mapList);
			listPanel.setVisible(false);
			startPanel.add(listPanel);

		}
		initButton();
		startPanel.add(startButton);

		contentPane.add(startPanel, BorderLayout.SOUTH);
	}

	private void initTitle() {
		JPanel titlePanel = new JPanel();
		JLabel label = new JLabel(userName + "，欢迎进入" + gameName + "游戏");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("微软雅黑", Font.BOLD, 28));
		titlePanel.add(label);
		contentPane.add(titlePanel, BorderLayout.NORTH);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isFirst) {
			if (!isReady) {
				isReady = true;
				startButton.setText("开始");
			} else {
				if (socketController.getRivalName() == null
						|| socketController.getRivalName().equals(""))
					JOptionPane.showMessageDialog(null, "请等待玩家加入！", "开始失败",
							JOptionPane.ERROR_MESSAGE);
				else {
					rivalSocket = gameServer.tryToConnect();
					if (rivalSocket == null)
						JOptionPane.showMessageDialog(null, "请等待对方就绪！", "开始失败",
								JOptionPane.ERROR_MESSAGE);
					else {
						// 开始游戏
						if (gameName.equals("泡泡堂"))
							bootPaopaotang(rivalSocket, listSelected);
						else if (gameName.equals("你画我猜"))
							bootDrawAnything(rivalSocket);
					}
				}
			}
		} else {
			if (!isReady) {
				isReady = true;
				startButton.setText("取消准备");
			} else {
				isReady = false;
				startButton.setText("准备");
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		Object tmp = arg0.getSource();
		if (tmp instanceof JList) {
			JList<String> source = (JList<String>) arg0.getSource();
			listSelected = source.getSelectedIndex();
			System.out.println("选择了第" + listSelected + "个地图");

		}
	}

	public void bootPaopaotang(final Socket rivalSocket, final int mapNum) {// ////游戏结束后改变状态
		try {
			System.out.println("泡泡堂启动中...");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new MapFrame(rivalSocket, mapNum, isFirst);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connectAsClient(String rivalIP, String gameName) {
		Socket rival;
		try {
			rival = new Socket(rivalIP, DataModel.gamePort.get(gameName));
			System.out.println(rival);
			if (gameName.equals("泡泡堂"))
				bootPaopaotang(rival, -1);
			else if (gameName.equals("你画我猜"))
				bootDrawAnything(rival);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void bootDrawAnything(final Socket rivalSocket) {
		System.out.println("你画我猜启动中...");
		int count = 0;
		final int t = isFirst ? 1 : 0;
		DrawMain drawSth= new DrawMain(rivalSocket, t);
//		while (count < 4) {
//			Runnable frameRun=new Runnable(){
//				public void run() {
//					try {
//						DrawMain drawSth= new DrawMain(rivalSocket, t);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			};
//			EventQueue.invokeLater(frameRun);
//			while (!DrawMain.end)
//				;
//			count++;
//		}
	}

	public void startGameServer() {
		gameServer = new P2PGameServer(socketController, gameName);
	}

	public void player1Enter(String userName) {
		player1Icon
				.setIcon(new ImageIcon(DataModel.imagePath.get("enterRoom")));
		player1Name.setText(userName);
	}

	public void player1Exit() {
		player1Icon
				.setIcon(new ImageIcon(DataModel.imagePath.get("enterRoom")));
		player1Name.setText(userName);
		isFirst = true;
		if (isReady)
			startButton.setText("开始");
		player2Exit();
	}

	public void player2Enter(String userName) {
		player2Icon
				.setIcon(new ImageIcon(DataModel.imagePath.get("enterRoom")));
		player2Name.setText(userName);
	}

	public void player2Exit() {
		player2Icon
				.setIcon(new ImageIcon(DataModel.imagePath.get("waitPlayer")));
		player2Name.setText("等待玩家进入");
	}
}
