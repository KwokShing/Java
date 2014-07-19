package com.drawonline.client.buffered;

import java.awt.image.BufferedImage;

public class MyBufferedImage {
	public static BufferedImage getBufferedImage() {
		BufferedImage img = new BufferedImage(600,350,BufferedImage.TYPE_4BYTE_ABGR);
		return img;
	}
}
