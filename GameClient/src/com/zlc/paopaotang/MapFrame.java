package com.zlc.paopaotang;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * 游戏正式开始时生成的Frame。游戏的设计采用了MVC的设计模式。<br>
 * 这个类主要是做一些初始化的工作。根据构造函数的信息生成了组成游戏的4个model。1个View_map和一个View_player<br>
 * 这4个model分别是：<br>
 * （1）Modle_map ---------存储有关地图的信息。<br>
 * （2）Modle_player localPlayer ---------存储有关本地Player的信息<br>
 * （3）Modle_player remotePlayer---------存储有关对方的Player的信息<br>
 * （3）Modle_bumping ---------存储有关泡泡的信息<br>
 * 
 * @author 廖翊康
 * @version 4.0
 */

public class MapFrame extends JFrame {

	private String title = "泡泡堂_by 廖翊康 陈国成 赵景晨";
	private Modle_map map = null;
	// private Modle_player player2 = new Modle_player(14,12);
	private View_map view_map = null;
	private Server server;

	/**
	 * 构造方法，建立一个新的Frame<br>
	 * 
	 * @param rivalSocket
	 *            用于和对方传递信息的socket<br>
	 * @param mapNum
	 *            地图编号，根据这个信息，初始化不同的地图<br>
	 * @param roleNum
	 *            角色编号，根据这个信息，初始化不同的角色<br>
	 * @param isFirst
	 *            用来判断谁是房主，房主会把初始化好的地图发给对方<br>
	 */
	public MapFrame(Socket rivalSocket, int mapNum, boolean isFirst)
			throws IOException {

		// 设置和游戏密切相关的4个model
		server = new Server(rivalSocket);

		if (isFirst) {
			System.out.println("isfirst");
			if (mapNum == 0) {
				map = new Modle_Street(0);
				System.out.println("send map 0");

				System.out.println("send map1");
				view_map = new View_Street();
			}

			else if (mapNum == 1) {
				System.out.println("send map 0");

				map = new Modle_Snowfield(1);
				System.out.println("send map 1");
				view_map = new View_Snowfield();
			}
			server.sendMap(map);
		}

		else {
			System.out.println("issecond");
			try {
				System.out.println("Get Map0");
				map = server.getMap();
				System.out.println("Get Map1");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (map.mapNum == 0)
				view_map = new View_Street();
			else if (map.mapNum == 1)
				view_map = new View_Snowfield();

		}

		server.listen();
		Modle_bumping bumping = new Modle_bumping();
		view_map.setModle(map, bumping);
		Modle_player localPlayer = null, remotePlayer = null;
		if (isFirst) {
			localPlayer = new Modle_player(1, map, bumping);
			remotePlayer = new Modle_player(2, map, bumping);
		} else {
			localPlayer = new Modle_player(2, map, bumping);
			remotePlayer = new Modle_player(1, map, bumping);
		}

		Control_Player ctr_player = new Control_Player();
		ctr_player.setOpaque(false);
		ctr_player.setModle(localPlayer, remotePlayer, server);

		// this.setLayout(null);
		this.setBounds(0, 0, 900, 900);
		this.setTitle(title);
		this.setDefaultCloseOperation(MapFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JLayeredPane layeredPane = new JLayeredPane();

		layeredPane.add(view_map, new Integer(0));
		layeredPane.add(ctr_player, new Integer(1));
		this.add(layeredPane);

		view_map.repaint();
		
		
		URL cb;
		File f=new File("sound/12.wav");
		cb=f.toURL();
		AudioClip clip = (AudioClip)Applet.newAudioClip(cb);
		clip.loop();
	}
}
