package com.zlc.minesweeper;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WinFrame extends JFrame{
	private JPanel winPane;
	private JLabel msg;


	public WinFrame() {
		super("你获胜了！！");
		setSize(150, 200);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		winPane = new JPanel();
		msg = new JLabel("  恭喜你！游戏胜利！  ");
		msg.setFont(new Font("微软雅黑", Font.BOLD, 20));
		winPane.add(msg);

		setContentPane(winPane);
		setBounds(250, 220, 300, 100);
		setVisible(true);
	}
}
