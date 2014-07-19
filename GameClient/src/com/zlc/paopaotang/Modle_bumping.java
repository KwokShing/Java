package com.zlc.paopaotang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Modle_bumping {

	public static final int ROW = 13;
	public static final int COL = 15;
	public static final int CS = 50;
	public static final int C = 1;// bumping center
	public static final int H = 2;// 水平炸的效果
	public static final int V = 3;// 垂直炸的效果
	public static final int LEFT = 4;// 往左边炸第一格
	public static final int RIGHT = 5;// 往右边炸第一格
	public static final int UP = 6;// 往上边炸第一格
	public static final int DOWN = 7;// 往下边炸第一格

	public int[][] bumpingMap = new int[ROW][COL];
	private ArrayList<ActionListener> actionListenerList;

	Modle_bumping() {
		for (int y = 0; y < ROW; y++)
			for (int x = 0; x < COL; x++)
				bumpingMap[y][x] = 0;
	}

	public void bump() {
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "bump"));
	}

	public void removeBumping() {
		for (int y = 0; y < ROW; y++)
			for (int x = 0; x < COL; x++) {
				bumpingMap[y][x] = 0;
			}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"removebump"));
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
			if (actionListenerList == null)
				return;
			list = (ArrayList) actionListenerList.clone();

			for (int i = 0; i < list.size(); i++) {
				ActionListener listener = (ActionListener) list.get(i);
				listener.actionPerformed(e);
			}
		}
	}

}
