package com.zlc.gamehall;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.zlc.exception.NotAuthoredException;
import com.zlc.minesweeper.MineSweeper;

public class TabKeeper extends MouseAdapter {

	JLabel tabCloseLabel = new JLabel();
	JPanel tabTitlePanel = new JPanel();
	JTabbedPane gameTabbedPane;
	JButton startGameButton;
	String gameName;
	HallFrame hallFrame;

	TabKeeper(HallFrame hallFrame, JTabbedPane gameTabbedPane,
			String imagePath, String title, String gameInfo) {
		this.gameTabbedPane = gameTabbedPane;
		gameName = title;
		this.hallFrame = hallFrame;
		try {
			JPanel tabPanel = new JPanel();
			tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
			// 开始游戏按钮初始化
			startGameButton = new JButton("开始游戏");
			startGameButton.addMouseListener(this);
			tabPanel.add(startGameButton);
			// 标签内容Panel
			tabPanel.add(new JLabel(new ImageIcon(imagePath)));
			JLabel jlabel = new JLabel(gameInfo);
			jlabel.setPreferredSize(new Dimension(100, 100));
			tabPanel.add(jlabel);
			JScrollPane scrollTabPanel = new JScrollPane(tabPanel);

			// 表现关闭实现代码
			JLabel tmpLabel = new JLabel(title);
			tmpLabel.setBackground(SystemColor.inactiveCaption);
			tabTitlePanel.setLayout(new GridLayout(1, 1, 10, 0));
			tmpLabel.setHorizontalAlignment(JLabel.LEFT);

			tabCloseLabel.setHorizontalAlignment(JLabel.RIGHT);
			tabCloseLabel.addMouseListener(this);

			tabTitlePanel.add(tmpLabel);
			tabTitlePanel.add(tabCloseLabel);

			gameTabbedPane.addTab(title, scrollTabPanel);
			gameTabbedPane.setTabComponentAt(
					gameTabbedPane.indexOfComponent(scrollTabPanel),
					tabTitlePanel);

			gameTabbedPane.setSelectedComponent(scrollTabPanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tabCloseLabel)
			gameTabbedPane.remove(gameTabbedPane
					.indexOfTabComponent(tabTitlePanel));
		else if (arg0.getSource() == startGameButton)
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (hallFrame.socketController.isLogged()==false)
							throw new NotAuthoredException();
						//启动游戏窗口或游戏房间
						if (gameName.equals("扫雷")) {
							MineSweeper mineSwepper = new MineSweeper();
							mineSwepper.setVisible(true);
						} else {
							GameBootFrame frame = new GameBootFrame(
									hallFrame.socketController, gameName);
							hallFrame.socketController.setGameFrame(frame);
							frame.setVisible(true);
						}
					} catch (NotAuthoredException e) {
						e.action();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		tabCloseLabel.setText("x");
	}

	public void mouseExited(MouseEvent arg0) {
		tabCloseLabel.setText("");
	}
}
