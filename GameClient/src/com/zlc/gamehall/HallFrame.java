package com.zlc.gamehall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.zlc.common.Config;

public class HallFrame extends JFrame implements Config {

	private static final long serialVersionUID = 4878864093833706543L;
	private JPanel contentPane;
	// private boolean logState;

	JTextPane textPane = new JTextPane();
	JTextArea messageShowArea = new JTextArea(DataModel.messageNote);
	JTabbedPane gameTabPanel = new JTabbedPane(JTabbedPane.TOP);
	JScrollPane messageAreaScrollPane;
	JScrollPane scrollTabPanel;
	JLabel tipsLabel;
	JButton logButton;
	JTable userListTable;
	JMenuItem logMenuItem = new JMenuItem("登录");
	JPanel pptPanel, nhwcPanel, slPanel, elsfkPanel;

	SocketController socketController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DataModel();// 初始化数据
					HallFrame frame = new HallFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HallFrame() {
		new ListenController(this);// 生成监听器
		initFrame();
		initMenu();
		initGameContent();
		initUserList();
		initTitle();
		initMessage();
		initTab();

	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("ZJU游戏大厅V3.0 by 赵景晨 廖翊康 陈国成");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("操作");
		menuBar.add(menu);

		logMenuItem.addMouseListener(ListenController.loginButtonListener);
		JMenuItem menuItem4 = new JMenuItem("退出");
		menuItem4.addMouseListener(ListenController.exitButtonListener);
		menu.add(logMenuItem);
		menu.add(menuItem4);

		JMenu menu1 = new JMenu("帮助");
		menuBar.add(menu1);

		JMenuItem menuItem = new JMenuItem("帮助");
		JMenuItem menuItem1 = new JMenuItem("关于");
		menu1.add(menuItem);
		menu1.add(menuItem1);
	}

	private void initTitle() {

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new CompoundBorder());
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tipsLabel = new JLabel("请登录后再使用~");
		tipsLabel.setForeground(Color.RED);
		tipsLabel.setFont(new Font("华文楷体", Font.PLAIN, 16));
		titlePanel.add(tipsLabel);

		JLabel lblzju = new JLabel(
				"\u6B22\u8FCE\u6765\u5230ZJU\u6E38\u620F\u5927\u5385");
		lblzju.setForeground(Color.BLUE);
		lblzju.setFont(new Font("微软雅黑", Font.BOLD, 25));
		titlePanel.add(lblzju);

		logButton = new JButton(new ImageIcon(DataModel.imagePath.get("log")));
		logButton.setToolTipText("用户登录");
		logButton.setText("\u767B\u5F55");
		logButton.setBounds(0, 0, 32, 32);
		logButton.addMouseListener(ListenController.loginButtonListener);
		titlePanel.add(logButton);

		JButton exitButton = new JButton(new ImageIcon(
				DataModel.imagePath.get("exit")));
		exitButton.setToolTipText("退出游戏大厅");
		exitButton.setText("\u9000\u51FA");
		exitButton.addMouseListener(ListenController.exitButtonListener);
		titlePanel.add(exitButton);
	}

	private void initGameContent() {
		JTree gameTree = new JTree(DataModel.gameTreeRootModel);

		gameTree.addTreeSelectionListener(ListenController.treeSelectedListener);
		gameTree.setPreferredSize(new Dimension(150, 90));
		contentPane.add(new JScrollPane(gameTree), BorderLayout.WEST);
	}

	private void initUserList() {
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JLabel jlabel = new JLabel("在线用户列表");
		jlabel.setForeground(SystemColor.textHighlight);
		jlabel.setFont(new Font("楷体", Font.BOLD, 18));
		panel_2.add(jlabel);

		userListTable = new JTable();
		userListTable.setEnabled(false);
		userListTable.setFont(new Font("华文楷体", Font.PLAIN, 12));
		userListTable.setBackground(UIManager.getColor("Button.background"));

		userListTable.setModel(DataModel.tableModel);
		userListTable.getColumnModel().getColumn(1).setPreferredWidth(62);

		JScrollPane scrollPane_1 = new JScrollPane(userListTable);
		scrollPane_1.setFont(new Font("华文楷体", Font.PLAIN, 12));
		scrollPane_1.setPreferredSize(new Dimension(100, 402));
		panel_2.add(scrollPane_1);
	}

	private void initMessage() {
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setPreferredSize(new Dimension(500, 100));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		messageShowArea.setRows(3);

		messageShowArea.setEditable(false);
		messageAreaScrollPane = new JScrollPane(messageShowArea);
		panel_4.add(messageAreaScrollPane);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 15));
		panel_3.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		textPane.setPreferredSize(new Dimension(6, 15));
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_5.add(textPane);

		JButton sendMessageButton = new JButton(new ImageIcon(
				DataModel.imagePath.get("sendMessage")));

		sendMessageButton.setText("发送");
		sendMessageButton.setToolTipText("发送聊天室消息");
		sendMessageButton
				.addMouseListener(ListenController.sendMessageButonListener);

		panel_5.add(sendMessageButton);

	}

	private void initTab() {

		contentPane.add(gameTabPanel, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setLayout(null);

		JList<String> list = new JList<String>();
		list.setOpaque(false);
		list.setBounds(0, 0, 600, 600);
		list.setBackground(SystemColor.inactiveCaption);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		list.setFont(new Font("华文楷体", Font.PLAIN, 23));
		list.setForeground(Color.DARK_GRAY);
		list.setModel(DataModel.newsListModel);

		gameTabPanel.addTab("欢迎", null, panel, null);
		panel.add(list);

	}


	/*
	 * public void setLogState(boolean logState) { this.logState = logState; }
	 */

	public void addGameTab(String imagePath, String title, String gameInfo) {
		new TabKeeper(this, gameTabPanel, imagePath, title, gameInfo);
	}

	public void logout() {
		tipsLabel.setText("请登录后再使用~");
		tipsLabel.setForeground(Color.RED);
		tipsLabel.setFont(new Font("华文楷体", Font.PLAIN, 15));
		logButton.setText("登录");
		logButton.removeMouseListener(ListenController.logoutButtonListener);
		logButton.addMouseListener(ListenController.loginButtonListener);
	}

	public void login(String userName) {
		tipsLabel.setText(userName + "欢迎您,");
		tipsLabel.setForeground(Color.BLUE);
		tipsLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
		logButton.setText("注销");
		logButton.setToolTipText("用户注销");
		logMenuItem.setText("注销");
		logMenuItem.removeMouseListener(ListenController.loginButtonListener);
		logMenuItem.addMouseListener(ListenController.logoutButtonListener);
		logButton.removeMouseListener(ListenController.loginButtonListener);
		logButton.addMouseListener(ListenController.logoutButtonListener);
	}
}
