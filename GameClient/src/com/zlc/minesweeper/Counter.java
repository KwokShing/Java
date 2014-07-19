package com.zlc.minesweeper;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class Counter extends JPanel {
	private ImageIcon[] numSet = { new ImageIcon("mine/c0.gif"),
			new ImageIcon("mine/c1.gif"), new ImageIcon("mine/c2.gif"),
			new ImageIcon("mine/c3.gif"), new ImageIcon("mine/c4.gif"),
			new ImageIcon("mine/c5.gif"), new ImageIcon("mine/c6.gif"),
			new ImageIcon("mine/c7.gif"), new ImageIcon("mine/c8.gif"),
			new ImageIcon("mine/c9.gif"), };
	private JButton[] counter = { new JButton(numSet[0]),
			new JButton(numSet[0]), new JButton(numSet[0]) };

	private int counterNum;
	private Insets space;

	public Counter(int num) {
		this.counterNum = num;
		setSize(23, 39);

		space = new Insets(0, 0, 0, 0);
		for (int i = 0; i < 3; i++) {
			counter[i].setSize(13, 23);
			counter[i].setMargin(space);
			add(counter[i]);
		}

		this.setVisible(true);
		setImage();
	}

	public int getCounterNum() {
		return counterNum;
	}

	private void setCounterNum(int num) {
		this.counterNum = num;
	}

	private void setImage() {
		counter[0].setIcon(numSet[counterNum % 1000 / 100]);
		counter[1].setIcon(numSet[counterNum % 100 / 10]);
		counter[2].setIcon(numSet[counterNum % 10]);
	}

	public void resetCounter(int num) {
		setCounterNum(num);
		setImage();
		repaint();
	}
}
