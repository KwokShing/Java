package com.zlc.paopaotang;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Modle类：游戏玩家。<br>
 * 游戏的设计采用了MVC的设计模式。本类根据Bean的标准编写。使得他能成为一个标准的信息源，或者说是事件源。<br>
 * 与之相关的视图类是View_player,控制类是Control_Player
 * 
 * @author 廖翊康
 * @version 4.0
 */
public class Modle_player {
	private int x;
	private int y;
	private int nextX;
	private int nextY;
	private boolean isdead = false;
	private int direction = 1;
	private int bumpMax = 1;
	private int bumpNum = 0;
	private int speed = 1;
	private int bumpSize = 1;
	private Modle_map modle_map;
	private Modle_bumping modle_bumping;
	public Image[] img_player = new Image[5];

	public final int DOWN = 1, UP = 0, LEFT = 2, RIGHT = 3;

	/** Utility field used by event firing mechanism. */
	private ArrayList<ActionListener> actionListenerList;

	
	/**
	 * 构造方法。根据相应的参数初始化玩家的位置和图像。
	 * @param pos 玩家的位置
	 * @param m 玩家所在的地图
	 * @bumping 与玩家相关的泡泡爆炸信息。
	 * */
	Modle_player(int playerNum, Modle_map m, Modle_bumping b) {
		if (playerNum == 1) {
			this.x = 0;
			this.y = 0;
			loadP1Image();
		} else if (playerNum == 2) {
			this.x = 14;
			this.y = 12;
			loadP2Image();
		} else
			System.out.println("Player's position error!");
		modle_map = m;
		modle_bumping = b;
	}
	/*读取和一号玩家相关的图片**/
	private void loadP1Image() {
		img_player[0] = Toolkit.getDefaultToolkit().getImage("image/player/player2/player41.png");// up
		img_player[1] = Toolkit.getDefaultToolkit().getImage("image/player/player2/player1.png");// down
		img_player[2] = Toolkit.getDefaultToolkit().getImage("image/player/player2/player31.png");// left
		img_player[3] = Toolkit.getDefaultToolkit().getImage("image/player/player2/player21.png");// right
		img_player[4] = Toolkit.getDefaultToolkit().getImage("image/die/paopao1.png");// die
	}
	
	/*读取和二号玩家相关的图片**/
	private void loadP2Image() {
		img_player[0] = Toolkit.getDefaultToolkit().getImage("image/player/player1/player41.png");
		img_player[1] = Toolkit.getDefaultToolkit().getImage("image/player/player1/player1.png");
		img_player[2] = Toolkit.getDefaultToolkit().getImage("image/player/player1/player31.png");
		img_player[3] = Toolkit.getDefaultToolkit().getImage("image/player/player1/player21.png");
		img_player[4] = Toolkit.getDefaultToolkit().getImage("image/die/paopao1.png");//die
	}
	
	/**获取玩家的横坐标信息*/
	public int getX() {
		return x;
	}
	/**获取玩家的纵坐标信息*/
	public int getY() {
		return y;
	}
	/**获取玩家的火力信息*/
	public int getBumpsize() {
		return bumpSize;
	}
	/**设置玩家的方向
	 * @param d 玩家所处的方向
	 * 
	 */
	public void setDirection(int d) {
		this.direction = d;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**获取玩家的目前的方向*/
	public int getDirection() {
		return this.direction;
	}
	/**设置玩家状态为死亡*/
	public void setDead() {
		isdead = true;
	}
	/**判断玩家是否为死亡*/
	public boolean isDead() {
		return isdead;
	}
	/**设置玩家可放泡泡数减一*/
	public void bumpNumMinus() {
		bumpNum--;
	}
	/**设置玩家向上移动一格*/
	public void moveUp() {
		nextX = x;
		nextY = y - 1;
		setDirection(UP);
		if (!(modle_map.outBoundary(nextX, nextY) || modle_map.isObstacle(
				nextX, nextY))) {
			x = nextX;
			y = nextY;
			eatFood(nextX, nextY);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**设置玩家向下移动一格*/
	public void moveDown() {
		nextX = x;
		nextY = y + 1;
		setDirection(DOWN);
		if (!(modle_map.outBoundary(nextX, nextY) || modle_map.isObstacle(
				nextX, nextY))) {
			x = nextX;
			y = nextY;
			eatFood(nextX, nextY);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**设置玩家向右移动一格*/
	public void moveRight() {
		nextX = x + 1;
		nextY = y;
		setDirection(RIGHT);
		if (!(modle_map.outBoundary(nextX, nextY) || modle_map.isObstacle(
				nextX, nextY))) {
			x = nextX;
			y = nextY;
			eatFood(nextX, nextY);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**设置玩家向左移动一格*/
	public void moveLeft() {
		nextX = x - 1;
		nextY = y;
		setDirection(LEFT);
		if (!(modle_map.outBoundary(nextX, nextY) || modle_map.isObstacle(
				nextX, nextY))) {
			x = nextX;
			y = nextY;
			// System.out.println("������֮��");
			eatFood(nextX, nextY);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**玩家放泡泡*/
	public void setBumb() {
		if (modle_map.isBomb(x, y) || bumpNum >= bumpMax)
			return;

		bumpNum++;
		modle_map.setBomb(x, y, bumpSize);
		Timer timer = new Timer();
		timer.schedule(new Bumping(x, y, bumpSize, this, modle_map,
				modle_bumping), 4000);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "x"));
	}
	/**设置玩家吃食物*/
	private void eatFood(int x, int y) {
		if (modle_map.isFood(x, y)) {
			switch (modle_map.foodBit(x, y)) {
			case 1:
				if (bumpMax < 9)
					bumpMax++;
				break;
			case 2:
				if (speed < 9)
					speed++;
				break;
			case 3:
				if (bumpSize < 9)
					bumpSize++;
				break;
			default:
				System.out.println("modle_map, Food Bit error!");
				break;
			}
			modle_map.removeFood(x, y);
		}
	}

	// 下面三个是在用MVC模式的标准代码，不需要改动
	/** Register an action event listener */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/** Fire TickEvent */
	private void processEvent(ActionEvent e) {
		ArrayList list;
		synchronized (this) {
			if (actionListenerList == null) {
				System.out.println("action listerner is null");
				return;
			}
			list = (ArrayList) actionListenerList.clone();
		}
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener) list.get(i);
			listener.actionPerformed(e);
		}
	}
}
