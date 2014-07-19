package com.zlc.paopaotang;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


/**
 * 控制类：控制游戏玩家。<br>
 * 游戏的设计采用了MVC的设计模式。与之相关的视图类是View_player,Modle类是Modle_player<br>
 * @author 廖翊康
 * @version 4.0
 */
public class Control_Player extends View_player {

	/**
	 * 构造方法。构造方法中添加了对键盘事件的监听。并根据监听结果控制本地玩家
	 */
	Control_Player() {
		// control_player = new Control_player();
		this.setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				ctr_lp = e.getKeyCode();
				try {
					server.sendInt(ctr_lp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				control_player(ctr_lp, localPlayer);
			}
		});
	}
	
	/**
	 * 根据接收到的监听事件做出相应的处理。
	 * @param e  这个时间的来源有两个：<br>
	 * （1）localPlayer 和 remotePlayer 这两个的Modle信息改变了的话，会导致view重画。<br>
	 * （2）server 从socket接受控制remotePlayre的控制信息。用来控制remotePlayer.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == localPlayer || e.getSource() == remotePlayer)
			repaint();
		else if (e.getSource() == server) {
			ctr_rp = server.getOperation();
			control_player(ctr_rp, remotePlayer);
		} 
		else
			System.out.println("no such operation!");
	}
	/**
	 * 控制Player做相应的动作。
	 * @param opr 对playre的控制信号。
	 * @param p 控制的玩家
	 */
	private void control_player(int opr, Modle_player p) {
		switch (opr) {
		case KeyEvent.VK_DOWN:
			p.moveDown();
			break;
		case KeyEvent.VK_UP:
			p.moveUp();
			break;
		case KeyEvent.VK_LEFT:
			p.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			p.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			p.setBumb();
			break;
		default:
			break;
		}
	}

}
