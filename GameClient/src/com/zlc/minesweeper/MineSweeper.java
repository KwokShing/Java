package com.zlc.minesweeper;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MineSweeper extends JFrame implements MouseListener, ActionListener {
	private MineMap mineMap;
	private MineButton[][] mineButton = new MineButton[10][10];
	private GridBagConstraints constraints = new GridBagConstraints();
	private JPanel pane = new JPanel();;
	private GridBagLayout gridbag = new GridBagLayout();
	private boolean gameStarted = false;
	private Counter mineCounter;
	private Counter timeCounter;

	private Thread timer;
	public int numMine;
	public int flagNum = 0;

	private JMenuBar mb;
	private JMenu mGame;
	private JMenuItem miEasy;
	private JMenuItem miMiddle;
	private JMenuItem miHard;
	private JMenuItem miExit;

	private JPanel controlPane = new JPanel();
	private JButton bTest;

	private ImageIcon[] mineNumIcon = { new ImageIcon("mine/0.gif"),
			new ImageIcon("mine/1.gif"), new ImageIcon("mine/2.gif"),
			new ImageIcon("mine/3.gif"), new ImageIcon("mine/4.gif"),
			new ImageIcon("mine/5.gif"), new ImageIcon("mine/6.gif"),
			new ImageIcon("mine/7.gif"), new ImageIcon("mine/8.gif"),
			new ImageIcon("mine/9.gif"), };
	private ImageIcon[] mineStatus = { new ImageIcon("mine/0.gif"),
			new ImageIcon("mine/flag.gif"), new ImageIcon("mine/question.gif") };
	private ImageIcon[] mineBombStatus = { new ImageIcon("mine/0.gif"),
			new ImageIcon("mine/mine.gif"), new ImageIcon("mine/wrongmine.gif"),
			new ImageIcon("mine/bomb.gif") };
	private ImageIcon[] faceIcon = { new ImageIcon("mine/smile.gif"),
			new ImageIcon("mine/Ooo.gif") };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MineSweeper frame = new MineSweeper();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * 踩到地雷，游戏失败
	 * 
	 * @param row
	 * @param col
	 */
	private void bomb(int row, int col) {
		timer.interrupt();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j].setIcon(mineBombStatus[0]);
				int toShow = mineMap.mine[i][j] != 9 ? 0 : 1;
				mineButton[i][j].setHasChecked(true);
				if (toShow == 1 && (i != row || j != col)) {
					mineButton[i][j].setIcon(mineBombStatus[toShow]);
					mineButton[i][j].setHasChecked(true);
				} else if (toShow == 1 && (i == row && j == col)) {
					mineButton[i][j].setIcon(mineBombStatus[3]);
					mineButton[i][j].setHasChecked(true);
				} else if (toShow == 0 && mineButton[i][j].getFlag() != 1) {
					mineButton[i][j].setEnabled(false);
				} else if (toShow == 0 && mineButton[i][j].getFlag() == 1) {
					mineButton[i][j].setIcon(mineBombStatus[2]);
					mineButton[i][j].setHasChecked(true);
				}
			}
		}
	}

	/**
	 * 胜利条件判断
	 * 
	 * @return
	 */
	private boolean isWin() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mineMap.mine[i][j] == 9 && mineButton[i][j].getFlag() != 1)
					return false;
				if (mineMap.mine[i][j] != 9 && mineButton[i][j].getFlag() == 1)
					return false;
				if (mineMap.mine[i][j] != 9
						&& mineButton[i][j].getHasChecked() == false)
					return false;
			}
		}
		return true;
	}

	/**
	 * 胜利提示
	 */
	private void win() {
		timer.interrupt();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new WinFrame();// 初始化数据
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	// Constructor of the game
	public MineSweeper() {
		super("扫雷 V1.0");
		setSize(250, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Insets space = new Insets(0, 0, 0, 0);

		numMine = 10;

		ImageIcon emptyIcon = new ImageIcon("mine/0.gif");
		pane.setLayout(gridbag);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;

		// Begin Menu Set
		mb = new JMenuBar();
		mGame = new JMenu("游戏");
		miEasy = new JMenuItem("初级");
		miEasy.addActionListener(this);
		miMiddle = new JMenuItem("中级");
		miMiddle.addActionListener(this);
		miHard = new JMenuItem("高级");
		miHard.addActionListener(this);
		miExit = new JMenuItem("退出");
		miExit.addActionListener(this);
		mGame.add(miEasy);
		mGame.add(miMiddle);
		mGame.add(miHard);
		mGame.addSeparator();
		mGame.add(miExit);
		mb.add(mGame);
		setJMenuBar(mb);
		// end of Menu Set

		// Control Panel
		bTest = new JButton(faceIcon[0]);
		bTest.setSize(26, 27);
		bTest.setMargin(space);
		bTest.addMouseListener(this);
		bTest.setPressedIcon(faceIcon[1]);

		mineCounter = new Counter(numMine);
		timeCounter = new Counter(0);

		controlPane.add(mineCounter);
		controlPane.add(bTest);
		controlPane.add(timeCounter);
		buildConstraints(constraints, 0, 0, 10, 2, 100, 100);
		gridbag.setConstraints(controlPane, constraints);
		pane.add(controlPane);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j] = new MineButton(i, j, emptyIcon);
				mineButton[i][j].addMouseListener(this);
				mineButton[i][j].setMargin(space);
				buildConstraints(constraints, j, i + 3, 1, 1, 100, 100);
				gridbag.setConstraints(mineButton[i][j], constraints);
				pane.add(mineButton[i][j]);
			}
		}

		setContentPane(pane);
		setLocation(200, 150);
		setVisible(true);
		timer = new Thread(new Timmer(timeCounter));

	}

	// Set the GUI objects positions
	void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw,
			int gh, int wx, int wy) {
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weighty = wy;
	}

	/**
	 * 检查格中是否有雷
	 * 
	 * @param row
	 *            格子的列号
	 * @param col
	 *            格子的行号
	 */
	void checkMine(int row, int col) {
		int i, j;
		i = row < 0 ? 0 : row;
		i = i > 9 ? 9 : i;
		j = col < 0 ? 0 : col;
		j = j > 9 ? 9 : j;

		if (mineMap.mine[i][j] == 9) {
			bomb(i, j);
		} else if (mineMap.mine[i][j] == 0
				&& mineButton[i][j].getHasChecked() == false) {
			mineButton[i][j].setHasChecked(true);
			showBox(i, j);
			for (int ii = i - 1; ii <= i + 1; ii++)
				for (int jj = j - 1; jj <= j + 1; jj++)
					checkMine(ii, jj);

		} else {
			showBox(i, j);
			mineButton[i][j].setHasChecked(true);
		}
		if (isWin()) {
			win();
		}
	}

	/**
	 * 将雷区清空，恢复到初始化状态
	 */
	private void reset() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j].setFlag(0);
				mineButton[i][j].setHasChecked(false);
				mineButton[i][j].setIcon(mineStatus[0]);
				mineButton[i][j].setEnabled(true);
				mineButton[i][j].setVisible(true);
			}
		}
	}

	// to flag the mine you want to flag out
	void flagMine(int row, int col) {
		if (mineButton[row][col].getFlag() == 0) {
			flagNum++;
		} else if (mineButton[row][col].getFlag() == 1) {
			flagNum--;
		}
		mineCounter
				.resetCounter(numMine - flagNum >= 0 ? numMine - flagNum : 0);
		mineButton[row][col].setFlag((mineButton[row][col].getFlag() + 1) % 3);
		showFlag(row, col);
		if (isWin()) {
			win();
		}
	}

	/**
	 * 显示指定格子的内容，即周围地雷的雷数量或地雷
	 * 
	 * @param row
	 *            指定格子的行号
	 * @param col
	 *            指定格子的列号
	 */
	void showBox(int row, int col) {
		int number = mineMap.mine[row][col];
		if (number != 0) {
			mineButton[row][col].setIcon(mineNumIcon[number]);
			mineButton[row][col].setHasChecked(true);

		} else
			mineButton[row][col].setEnabled(false);
	}

	/**
	 * 使用红旗、问号、空白循环对格子进行标记
	 * 
	 * @param row
	 * @param col
	 */
	void showFlag(int row, int col) {
		mineButton[row][col]
				.setIcon(mineStatus[mineButton[row][col].getFlag()]);
	}

	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * 启动游戏
	 * 
	 * @param num
	 * @param row
	 * @param col
	 */
	private void startNewGame(int num, int row, int col) {
		mineMap = new MineMap(num, row, col);
		gameStarted = true;
		timer = new Thread(new Timmer(timeCounter));
		timer.start();
	}

	/**
	 * 重置游戏
	 * @param num 雷数量
	 */
	public void setNewGame(int num) {
		reset();
		numMine = num;
		flagNum = 0;
		gameStarted = false;
		mineCounter.resetCounter(numMine);
		timeCounter.resetCounter(0);
		timer.interrupt();
	}

	// the event handle to deal with the mouse click
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == bTest) {
			setNewGame(numMine);
			return;
		}
		int row = ((MineButton) e.getSource()).getRow();
		int col = ((MineButton) e.getSource()).getCol();
		if (!gameStarted) 
			startNewGame(numMine, row, col);

		if (!mineButton[row][col].getHasChecked()) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				if (mineButton[row][col].getFlag() == 1)
					return;
				else
					checkMine(row, col);

			} else if (e.getModifiers() == InputEvent.BUTTON3_MASK)
				flagMine(row, col);
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == miEasy) {
			setNewGame(10);
			return;
		}
		if (e.getSource() == miMiddle) {
			setNewGame(20);
			return;
		}
		if (e.getSource() == miHard) {
			setNewGame(30);
			return;
		}
		if (e.getSource() == miExit) 
			this.dispose();
		
	}
}

/**
 * 计时器类
 * 
 * @author 赵景晨
 * 
 */
class Timmer implements Runnable {
	private Counter timeCounter;
	boolean isplaying = true;

	Timmer(Counter time) {
		timeCounter = time;
	}

	public void run() {
		while (isplaying) {
			try {
				Thread.sleep(1000);
				timeCounter.resetCounter(timeCounter.getCounterNum() + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
