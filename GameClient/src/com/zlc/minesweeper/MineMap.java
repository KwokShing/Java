package com.zlc.minesweeper;

public class MineMap {
	/** 10*10雷区方阵 */
	public int[][] mine;

	/**
	 * 构造函数，通过随机数生成雷，构造雷区
	 * @param mineNum 雷的数量
	 * @param row 行数
	 * @param col 列数
	 */
	MineMap(int mineNum, int row, int col) {
		mine = new int[10][10];// 固定大小为10*10
		int mcol = 0, mrow = 0;
		for (int i = 0; i < mineNum; i++) {// 随机生成雷
			mcol = (int) (Math.random() * 100) % 10;
			mrow = (int) (Math.random() * 100) % 10;
			if (mine[mcol][mrow] == 0
					&& (mrow != row || mcol != col || row == 10)) {
				mine[mrow][mcol] = 9;
			} else
				i--;
		}
		setMineNum();
	}
	/**
	 * 计算10*10方阵中每一个的号码
	 */
	private void setMineNum() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
					mine[i][j] = checkMineNum(i, j);
	}
	/**
	 * 计算一格附近雷的个数
	 * @param row 格子的行号
	 * @param col 格子的列号
	 * @return 附近雷的个数。如果该格是雷则直接返回9（9代表雷）
	 */
	private int checkMineNum(int row, int col) {
		if(mine[row][col]==9)//雷格
			return 9;
		int up = row - 1, down = row + 1, left = col - 1, right = col + 1;
		int num = 0;
		if (up < 0)
			up = 0;
		if (down > 9)
			down = 9;
		if (left < 0)
			left = 0;
		if (right > 9)
			right = 9;
		for (int i = up; i <= down; i++) 
			for (int j = left; j <= right; j++) 
				if (mine[i][j] == 9)
					num++;
		return num;
	}
}
