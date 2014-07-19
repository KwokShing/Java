package com.zlc.minesweeper;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MineButton extends JButton {
	private int col;
	private int row;
	private int flag = 0;
	private boolean hasChecked = false;

	MineButton(int row, int col, ImageIcon icon) {
		super(icon);
		this.row = row;
		this.col = col;
	}

	public boolean getHasChecked() {
		return hasChecked;
	}

	public void setHasChecked(boolean toSet) {
		hasChecked = toSet;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}
}