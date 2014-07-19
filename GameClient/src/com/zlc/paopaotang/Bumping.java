package com.zlc.paopaotang;

import java.util.TimerTask;

public class Bumping extends TimerTask {

	private int x;
	private int y;
	private int size;
	private Modle_map modle_map;
	private Modle_bumping modle_bumping;
	private Modle_player modle_player;
	boolean readyToBump;

	Bumping(int x, int y, int s, Modle_player p, Modle_map m, Modle_bumping b) {
		this.x = x;
		this.y = y;
		this.size = s;
		this.modle_player = p;
		this.modle_map = m;
		this.modle_bumping = b;
		readyToBump = true;
	}

	private synchronized void bumping(int x, int y) {
		if (readyToBump) {
			modle_map.S_removeBomb(x, y);
			modle_bumping.bumpingMap[y][x] = modle_bumping.C;// 设置成爆炸中心
			setBumpingUp(x, y);
			setBumpingDown(x, y);
			setBumpingRight(x, y);
			setBumpingLeft(x, y);
			modle_player.bumpNumMinus();
		}
	}

	@Override
	public void run() {
		if (modle_map.isBomb(x, y)) {
			bumping(x, y);
			BumpingEffect(); // 逻辑上的效果，炸箱子，炸食物等等
			modle_bumping.bump();// 显示作用
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			modle_bumping.removeBumping();
		}
	}

	private void setBumpingUp(int x, int y) {
		for (int i = 1; i <= size; i++) {
			int nextX = x;
			int nextY = y - i;
			if (!setBumping(nextX, nextY, Modle_bumping.V))
				return;
		}
	}

	private void setBumpingDown(int x, int y) {
		// System.out.println("remove BOX000!!!!!!");
		for (int i = 1; i <= size; i++) {
			int nextX = x;
			int nextY = y + i;
			if (!setBumping(nextX, nextY, Modle_bumping.V))
				return;
		}
	}

	private void setBumpingRight(int x, int y) {
		for (int i = 1; i <= size; i++) {
			int nextX = x + i;
			int nextY = y;
			if (!setBumping(nextX, nextY, Modle_bumping.H))
				return;
		}
	}

	private void setBumpingLeft(int x, int y) {
		for (int i = 1; i <= size; i++) {
			int nextX = x - i;
			int nextY = y;
			if (!setBumping(nextX, nextY, Modle_bumping.H))
				return;
		}
	}

	// 仅仅起一个设置作用,是之后画画用的
	private boolean setBumping(int x, int y, int v) {
		if (modle_map.outBoundary(x, y) || modle_map.isHardware(x, y))
			return false;
		modle_bumping.bumpingMap[y][x] = v;
		if (modle_map.isBomb(x, y)) {
			bumping(x, y);
			return false;
		}
		if (modle_map.isBox(x, y))
			return false;
		return true;
	}

	// 炸这个点
	private void BumpingEffect() {
		for (int y = 0; y < Modle_map.ROW; y++)
			for (int x = 0; x < Modle_map.COL; x++) {
				if (modle_bumping.bumpingMap[y][x] > 0) {
					if (modle_player.getX() == x && modle_player.getY() == y)
						modle_player.setDead();
					if (modle_map.isBox(x, y)) {
						modle_map.S_removeBox(x, y);
						continue;
					}
					if (modle_map.isFood(x, y)) {
						modle_map.S_removeFood(x, y);
					}
				}
			}
	}

}
