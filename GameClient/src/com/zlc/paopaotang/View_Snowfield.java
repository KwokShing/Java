package com.zlc.paopaotang;

import java.awt.Graphics;
import java.awt.Toolkit;

public class View_Snowfield extends View_map {

	//会先调用父类的构造函数
	public View_Snowfield() {

	}

	// 载入图像
	protected void loadImage() {

		img_bg[0] = Toolkit.getDefaultToolkit().getImage("image/map2/bg.png");
		// img_hardware[0] =
		// Toolkit.getDefaultToolkit().getImage("image/map2/0.png");
		img_hardware[1] = Toolkit.getDefaultToolkit().getImage(
				"image/map2/1.png");
		img_hardware[2] = Toolkit.getDefaultToolkit().getImage(
				"image/map2/2.png");
		img_hardware[3] = Toolkit.getDefaultToolkit().getImage(
				"image/map2/3.png");
		img_hardware[4] = Toolkit.getDefaultToolkit().getImage(
				"image/map2/4.png");
		img_hardware[5] = Toolkit.getDefaultToolkit().getImage(
				"image/map2/5.png");

		img_box[0] = Toolkit.getDefaultToolkit()
				.getImage("image/map2/box1.png");
		img_box[1] = Toolkit.getDefaultToolkit()
				.getImage("image/map2/box2.png");

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
		for (int y = 0; y < ROW; y++) {
			for (int x = 0; x < COL; x++) {
				switch (modle_map.map[y][x]) {
				case 7:
					g.drawImage(img_hardware[1], x * CS, y * CS, this);
					break;
				case 2:
					g.drawImage(img_hardware[2], x * CS, y * CS, this);
					break;
				case 3:
					g.drawImage(img_hardware[3], x * CS, y * CS, this);
					break;
				case 4:
					g.drawImage(img_hardware[4], x * CS, y * CS, this);
					break;

				case 6:
					g.drawImage(img_hardware[5], x * CS, y * CS, this);
					break;

				default:
					break;
				}
			}
		}

	}

	protected void drawMap(Graphics g) {
		// System.out.println("drawMap了");
		for (int y = 0; y < ROW; y++) {
			for (int x = 0; x < COL; x++) {
				switch (modle_map.foodBit(x, y)) {
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
				// 箱子
				switch (modle_map.boxBit(x, y)) {
				case 1:
					g.drawImage(img_box[0], x * CS, y * CS, 50, 50, this);
					break;
				case 2:
					g.drawImage(img_box[1], x * CS, y * CS, 50, 50, this);
					break;
				default:
					break;
				}
				//泡泡bomb
				if (modle_map.isBomb(x, y))
					g.drawImage(img_bump[0], x * CS, y * CS, 50, 50, this);
			}
		}
	}
}
