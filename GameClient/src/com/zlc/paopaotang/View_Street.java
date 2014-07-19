package com.zlc.paopaotang;

import java.awt.Graphics;
import java.awt.Toolkit;

public class View_Street extends View_map {

	//会先调用父类的构造函数
	public View_Street() {

	}

	//载入图像
	protected void loadImage() {
		img_bg[0] = Toolkit.getDefaultToolkit().getImage("image/map/bg.png");
		img_hardware[0] = Toolkit.getDefaultToolkit().getImage(
				"image/map/grass.png");
		img_hardware[1] = Toolkit.getDefaultToolkit().getImage(
				"image/map/road.png");
		img_hardware[2] = Toolkit.getDefaultToolkit().getImage(
				"image/map/tree.png");
		img_hardware[3] = Toolkit.getDefaultToolkit().getImage(
				"image/map/redhouse.png");
		img_hardware[4] = Toolkit.getDefaultToolkit().getImage(
				"image/map/yellowhouse.png");
		img_hardware[5] = Toolkit.getDefaultToolkit().getImage(
				"image/map/bluehouse.png");
		img_hardware[6] = Toolkit.getDefaultToolkit().getImage(
				"image/box/box1.png");
		img_hardware[7] = Toolkit.getDefaultToolkit().getImage(
				"image/box/box2.png");
		img_hardware[8] = Toolkit.getDefaultToolkit().getImage(
				"image/box/box3.png");

		img_bump[0] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump1.png");
		img_bump[1] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump2.png");
		img_bump[2] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump3.png");
		img_bump[3] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump4.png");
		img_bump[4] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump5.png");
		img_bump[5] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump6.png");
		img_bump[6] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump7.png");
		img_bump[7] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump8.png");
		img_bump[8] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump9.png");
		img_bump[9] = Toolkit.getDefaultToolkit().getImage(
				"image/bump/bump10.png");

		img_food[0] = Toolkit.getDefaultToolkit().getImage(
				"image/food/food1.png");
		img_food[1] = Toolkit.getDefaultToolkit().getImage(
				"image/food/food2.png");
		img_food[2] = Toolkit.getDefaultToolkit().getImage(
				"image/food/food3.png");

	}

	protected void drawHardware(Graphics g) {
		;
	}

	protected void drawMap(Graphics g) {
		// System.out.println("drawMap 了");
		for (int y = 0; y < ROW; y++) {
			for (int x = 0; x < COL; x++) {
				switch (modle_map.foodBit(x, y)) {
				//下面的几个case是食物
				case 1:
					g.drawImage(img_food[0], x * CS, y * CS, 50, 50, this);
					break;
				case 2:
					g.drawImage(img_food[1], x * CS, y * CS, 50, 50, this);
					break;
				case 3:
					g.drawImage(img_food[2], x * CS, y * CS, 50, 50, this);
					break;
				default:
					break;
				}
				//箱子
				switch (modle_map.boxBit(x, y)) {
				case 1:
					// g.drawImage(img_hardware[0], x * CS, y * CS, 50,50,this);
					g.drawImage(img_hardware[6], x * CS, y * CS, 50, 50, this);
					break;
				case 2:
					// g.drawImage(img_hardware[0], x * CS, y * CS, 50,50,this);
					g.drawImage(img_hardware[7], x * CS, y * CS, 50, 50, this);
					break;
				case 3:
					// g.drawImage(img_hardware[0], x * CS, y * CS, 50,50,this);
					g.drawImage(img_hardware[8], x * CS, y * CS, 50, 50, this);
					break;
				default:
					break;
				}
				//泡泡bomb
				if (modle_map.isBomb(x, y)) {
					/*
					 * if(modle_map.map[y][x]%10 == 0 )
					 * g.drawImage(img_hardware[0], x * CS, y * CS, this); else
					 * if(modle_map.map[y][x]%10 == 1)
					 * g.drawImage(img_hardware[1], x * CS, y * CS, this); else
					 * System.out.println("view bump error!");
					 */

					g.drawImage(img_bump[0], x * CS, y * CS, 50, 50, this);
				}

			}
		}
	}
}
