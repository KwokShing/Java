package com.zlc.paopaotang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 地图modle 的抽象类。<br>
 * 游戏的设计采用了MVC的设计模式。本类根据Bean的标准编写。使得他能成为一个标准的信息源，或者说是事件源。<br>
 * @author 廖翊康
 * @version 4.0
 */
public abstract class Modle_map implements Serializable {
	public static final int ROW = 13;
	public static final int COL = 15;
	public static final int CS = 50;
	public int mapNum = -1;

	

	/**
	 * 存放地图信息的最重要的数据。这个2维数组的每一个点都表示实际游戏地图上的每一个格。<br>
	 * 2维数组中某元素取不同的整数值代表不同的效果<br>
	 */
	public int[][] map;

	/**2维数组元素的十位信息用来表示食物*/
	final int[] FOOD = { 10, 20, 30 }; // FOOD_BUMP = 10,FOOD_SPEED =20,BUMPPOWE
	/**2维数组元素的百位信息用来表示箱子*/
	final int[] BOX = { 100, 200, 300 }; // WOODBOX = 100;YELLOWBOX =200;REDBOX=300;
	/**2维数组元素的千位信息用来表示泡泡，千位数字的大小表示了泡泡的火力值*/
	final int[] BUMB = { 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000 };
	/**2维数组元素的千万位信息用来表示泡泡爆炸时的效果，千位数字的大小表示了泡泡的火力值*/
	final int[] BUMPING = { 10000, 20000, 30000, 40000, 50000, 60000, 70000,
			80000, 90000 };
	
	/** Utility field used by event firing mechanism. */
	private ArrayList<ActionListener> actionListenerList;

	Modle_map(int mapNum) {
		this.mapNum=mapNum;
	}
	
	/** 获取坐标为（x,y）的地图信息
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public int getPoint(int x, int y) {
		return map[y][x];
	}

	/** 设置坐标为（x,y）的地图信息，并触发事件，更新相应的view
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void setPoint(int x, int y, int v) {
		this.map[y][x] = v;
		// Notify the listener for the change on radius
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"point"));
	}

	/** 设置坐标为（x,y）的点为泡泡。
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void setBomb(int x, int y, int p) {
		// System.out.println("Modle_map.java set bumb!!!!!!!!!!!!!");
		setPoint(x, y, getPoint(x, y) + BUMB[p - 1]);
		System.out.println("" + map[y][x]);
	}

	/** 除去坐标为（x,y）的点的泡泡效果。并触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	
	
	public void removeBomb(int x, int y) {
		int p = bombBit(x, y);
		setPoint(x, y, getPoint(x, y) - BUMB[p - 1]);
	}

	/** 除去坐标为（x,y）的点的泡泡效果。不触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void S_removeBomb(int x, int y) {
		int p = bombBit(x, y);
		map[y][x] = getPoint(x, y) - BUMB[p - 1];
	}

	/** 设置坐标为（x,y）的点的泡泡爆炸效果。不触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public synchronized void setBumping(int x, int y) {
		// 先remove bomb!
		int p = bombBit(x, y);
		map[y][x] = getPoint(x, y) - BUMB[p - 1];
		map[y][x] = getPoint(x, y) + BUMPING[p - 1];

	}

	/** 消除坐标为（x,y）的点的泡泡爆炸效果。触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void removeBumping(int x, int y) {
		int p = bumpingBit(x, y);
		setPoint(x, y, getPoint(x, y) - BUMPING[p - 1]);
	}

	/** 消除坐标为（x,y）的点的泡泡爆炸效果。不触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void S_removeBumping(int x, int y) {
		int p = bumpingBit(x, y);
		map[y][x] = getPoint(x, y) - BUMPING[p - 1];
	}

	
	/** 消除坐标为（x,y）的点的箱子效果。不触发事件
	 * @param x 横坐标
	 * @param y 纵坐标
	 * */
	public void S_removeBox(int x, int y) {
		int p = boxBit(x, y);
		map[y               M o s t   v i s i t e d         �      FL        �      F� �      J��D�M/^��� J��D�9                    � P�O� �:i�� +00� /E:\                   J 1     �DJ& 115 8 	  ��DJ&�DJ&.   �&                   =�� 1 1 5    T 1     �DU& 115com  > 	  ��DJ&�DU&.   �&                   ��% 1 1 5 c o m    Z 1     �DS& chromium  B 	  ��DK&�DS&.   �&                   {Q c h r o m i u m    j 2 9 uD�N  115BRO~1.EXE  N 	  �uD�N�DK&.   �&                       1 1 5 B r o w s e r . e x e      Z            3       Y         �GW�   �¼Ӿ� E:\115\115com\chromium\115Browser.exe  2   h t t p : / / w w w . g o o g l e . c o m / c h r o m e / i n t l / e n / w e l c o m e . h t m l y   	  �   1SPS�XF�L8C���&�m�    Q   1SPS�����Oh�� +'��5             W e l c o m e   t o   1 1 5 OmȉhV          `     �X       kross           F�76�}�K��c�5`~ܶo�����p��F�F�76�}�K��c�5`~ܶo�����p��F�         �      FL        �      F� �      J��D�M/^��� J��D�9                    � P�O� �:i�� +00� /E:\                   J 1     �DJ& 115 8 	  ��DJ&�DJ&.   �&                   =�� 1 1 5    T 1     �DU& 115com  > 	  ��DJ&�DU&.   �&                   ��% 1 1 5 c o m    Z 1     �DS& chromium  B 	  ��DK&�DS&.   �&                   {Q c h r o m i u m    j 2 9 uD�N  115BRO~1.EXE  N 	  �uD�N�DK&.   �&                       1 1 5 B r o w s e r . e x e      Z            3       Y         �GW�   �¼Ӿ� E:\115\115com\chromium\115Browser.exe  )   h t t p s : / / c h r o m e . g o o g l e . c o m / w e b s t o r e ? h l = e n y   	  �   1SPS�XF�L8C���&�m�    Q   1SPS�����Oh�� +'��5             C h r o m e   W e b   S t o r e             `     �X       kross           F�76�}�K��c�5`~ܶo�����p��F�F�76�}�K��c�5`~ܶo�����p��F�    ����           �      FL        �      F� �      J��D�M/^��� J��D�9                    � P�O� �:i�� +00� /E:\                   J 1     �DJ& 115 8 	  ��DJ&�DJ&.   �&                   =�� 1 1 5    T 1     �DU& 115com  > 	  ��DJ&�DU&.   �&                   ��% 1 1 5 c o m    Z 1     �DS& chromium  B 	  ��DK&�DS&.   �&                   {Q c h r o m i u m    j 2 9 uD�N  115BRO~1.EXE  N 	  �uD�N�DK&.   �&                       1 1 5 B r o w s e r . e x e      Z            3       Y         �GW�   �¼Ӿ� E:\115\115com\chromium\115Browser.exe  % E : \ 1 1 5 \ 1 1 5 c o m \ c h r o m i u m \ 1 1 5 B r o w s e r . e x e m   	  �   1SPS�XF�L8C���&�m�    E   1SPS�����Oh�� +'��)             N e w   w i n d o w             `     �X       kross           F�76�}�K��c�5`~ܶo�����p��F�F�76�}�K��c�5`~ܶo�����p��F�         �      FL        �      F� �      J��D�M/^��� J��D�9                    � P�O� �:i�� +00� /E:\                   J 1     �DJ& 115 8 	  ��DJ&�DJ&.   �&                   =�� 1 1 5    T 1     �DU& 115com  > 	  ��DJ&�DU&.   �&                   ��% 1 1 5 c o m    Z 1     �DS& chromium  B 	  ��DK&�DS&.   �&                   {Q c h r o m i u m    j 2 9 uD�N  115BRO~1.EXE  N 	  �uD�N�DK&.   �&                       1 1 5 B r o w s e r . e x e      Z            3       Y         �GW�   �¼Ӿ� E:\115\115com\chromium\115Browser.exe     - - i n c o g n i t o % E : \ 1 1 5 \ 1 1 5 c o m \ c h r o m i u m \ 1 1 5 B r o w s e r . e x e �   	  �   1SPS�XF�L8C���&�m�    Y   1SPS�����Oh�� +'��=             N e w   i n c o g n i t o   w i n d o w             `     �X       kross           F�76�}�K��c�5`~ܶo�����p��F�F�76�}�K��c�5`~ܶo�����p��F�    ����                                                                                                                                                                                                                                                                                                                                                                                                                                                                  map[y][x] < 9999)
			return true;
		return false;
	}

	// 下面三个是在用MVC模式的标准代码，不需要改动
	/** Register an action event listener */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/** Fire TickEvent */
	private void processEvent(ActionEvent e) {
		ArrayList list;

		synchronized (this) {
			if (actionListenerList == null)
				return;
			list = (ArrayList) actionListenerList.clone();

			for (int i = 0; i < list.size(); i++) {
				ActionListener listener = (ActionListener) list.get(i);
				listener.actionPerformed(e);
			}
		}
	}
}
