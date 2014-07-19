package com.zlc.paopaotang;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


/**
 * 视图类，抽象类，与之对应的modle是地图。<br>
 * 游戏的设计采用了MVC的设计模式。这个类的主要工作就是根据modle_map展示出相应效果（图片）。<br>
 * @author 廖翊康
 * @version 4.0
 */

public abstract class View_map extends JPanel implements ActionListener {

	/**窗体的宽*/
	protected static final int WIDTH = 750;
	/**窗体的高*/
	protected static final int HEIGHT = 650;
	/**游戏地图横向的方格数*/
	protected static final int ROW = 13;
	/**游戏地图横向的纵向数*/
	protected static final int COL = 15;
	/**游戏地图方格的size*/
	protected static final int CS = 50;
	/**view 所以依赖的地图modle*/
	protected Modle_map modle_map;
	/**view 所以依赖的泡泡爆炸的modle*/
	protected Modle_bumping modle_bumping;
	/**view 游戏的地图的背景图片*/
	Image[] img_bg = new Image[1];
	/**view 游戏的地图的静态障碍物图片*/
	Image[] img_hardware = new Image[11]; // background image
	/**view 游戏的地图的泡泡图片*/
	Image[] img_bump = new Image[10];
	/**view 游戏的地图的食物图片*/
	Image[] img_food = new Image[3];
	/**view 游戏的地图的箱子图片*/
	Image[] img_box = new Image[5];

	/**view 构造方法。在构造方法中把需要用到的图片读取进来，并设置paneld的大小和位置*/
	public View_map() {
		loadImage();
		setBounds(0, 0, WIDTH, HEIGHT);
	}

	/**设置View_map依赖的两个model,一个是地图modle,一个是泡泡爆炸时候的modle。<br>
	 * @param m 地图的modle<br>
	 * @param p 泡泡爆炸的modle<br>
	 *
	 */
	void setModle(Modle_map m, Modle_bumping b) {
		// System.out.println("set modle");
		modle_map = m;
		modle_bumping = b;
		modle_map.addActionListener(this);
		modle_bumping.addActionListener(this);
		repaint();
	}

	/**描绘窗体，此处在默认JPanel 基础上构建底层地图*/
	public void paintComponent(Graphics g) {
		// System.out.println("paintComponet了");
		super.paintComponent(g);

		g.drawImage(img_bg[0], 0, 0, WIDTH, HEIGHT, this);
		drawMap(g);
		drawBump(g);
		drawHardware(g);
		// drawPlayer(g);
	}

	/**载入图像*/
	protected abstract void loadImage();
	/**画障碍物*/
	protected abstract void drawHardware(Graphics g);

	/**抽象方法，用来画静态的背景和障碍物*/
	protected abstract void drawMap(Graphics g);

	/**描绘泡泡爆炸时候的效果*/
	private void drawBump(Graphics g) {
		// System.out.println("drwaBump 了");
		for (int y = 0; y < ROW; y++)
			for (int x = 0; x < COL; x++) {
				switch (modle_bumping.bumpingMap[y][x]) {
				case Modle_bumping.C:
					g.drawImage(img_bump[3], x * CS, y * CS, 50, 50, this);
					break;
				case Modle_bumping.V:
					g.drawImage(img_bump[8], x * CS, y * CS, 50, 50, this);
					break;
				case Modle_bumping.H:
					g.drawImage(img_bump[9], x * CS, y * CS, 50, 50, this);
					break;
				default:
					break;
				}
			}
	}

	
	/**每当和view相关的modle，map或者bump改动的了时候就触发次函数，产生的效果就是重画游戏地图*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("repant 了");///////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////待会可能需要改动///////////////////////////////////////
		repaint();
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
		}
	}

}
