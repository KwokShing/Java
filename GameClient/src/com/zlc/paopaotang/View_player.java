package com.zlc.paopaotang;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class View_player extends JPanel implements ActionListener {

	protected static final int WIDTH = 750;
	protected static final int HEIGHT = 650;
	private Image[] img_player = new Image[10];

	protected static final int UP = 1;
	protected static final int DOWN = 2;
	protected static final int LEFT = 3;
	protected static final int RIGHT = 4;

	private static final int ROW = 13;
	private static final int COL = 15;
	protected static final int CS = 50;

	protected int ctr_rp;
	protected int ctr_lp;
	protected Modle_player localPlayer;
	protected Modle_player remotePlayer;
	protected Server server;
	private int direction; // 新增变量，用以确认角色所对方向,对应按键触发

	public View_player() {
		//loadImage();
		setBounds(0, 0, WIDTH, HEIGHT);
		// direction = DOWN;
	}

	void setModle(Modle_player p1, Modle_player p2, Server s) {
		// System.out.println("set modle");

		localPlayer = p1;
		remotePlayer = p2;
		server = s;
		localPlayer.addActionListener(this);
		remotePlayer.addActionListener(this);
		System.out.println("view_playe.java,server add");
		server.addActionListener(this);
		System.out.println("view_playe.java,server add_end");

		repaint();
	}

	public void paintComponent(Graphics g) {
		// System.out.println("paintComponet 了");
		super.paintComponent(g);
		drawPlayer(g);
	}
/*
	private void loadImage() {
		img_player[0] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player2/player41.png");// up
		img_player[1] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player2/player1.png");// down
		img_player[2] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player2/player31.png");// left
		img_player[3] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player2/player21.png");// right

		img_player[4] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player1/player41.png");
		img_player[5] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player1/player1.png");
		img_player[6] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player1/player31.png");
		img_player[7] = Toolkit.getDefaultToolkit().getImage(
				"image/player/player1/player21.png");

		img_player[8] = Toolkit.getDefaultToolkit().getImage(
				"image/die/paopao1.png");// right
		img_player[9] = Toolkit.getDefaultToolkit().getImage(
				"image/die/paopao2.png");// right

	}
*/
	private void drawPlayer(Graphics g) {

		if (localPlayer.isDead() && !remotePlayer.isDead()) {
			g.drawImage(localPlayer.img_player[4], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			// JOptionPane.showMessageDialog(null, "Player1 Win");
			return;
		}
		if (!localPlayer.isDead() && remotePlayer.isDead()) {
			g.drawImage(remotePlayer.img_player[4], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			// JOptionPane.showMessageDialog(null, "Player2 Win");
			// return ;
		}
		if (localPlayer.isDead() && remotePlayer.isDead()) {
			g.drawImage(localPlayer.img_player[4], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			g.drawImage(remotePlayer.img_player[4], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			// JOptionPane.showMessageDialog(null, "ƽ�֣�");
			return;

		}

		switch (localPlayer.getDirection()) {
		case 0:
			g.drawImage(localPlayer.img_player[0], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			break;
		case 1:
			g.drawImage(localPlayer.img_player[1], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			break;
		case 2:
			g.drawImage(localPlayer.img_player[2], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			break;
		case 3:
			g.drawImage(localPlayer.img_player[3], localPlayer.getX() * CS,
					localPlayer.getY() * CS, 50, 50, this);
			break;
		default:
			System.out.println("view_player.java direction error!");
			break;
		}
		switch (remotePlayer.getDirection()) {
		case 0:
			g.drawImage(remotePlayer.img_player[0], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			break;
		case 1:
			g.drawImage(remotePlayer.img_player[1], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			break;
		case 2:
			g.drawImage(remotePlayer.img_player[2], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			break;
		case 3:
			g.drawImage(remotePlayer.img_player[3], remotePlayer.getX() * CS,
					remotePlayer.getY() * CS, 50, 50, this);
			break;
		default:
			System.out.println("view_player.java direction error!");
			break;
		}
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

}
