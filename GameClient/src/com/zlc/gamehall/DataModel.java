package com.zlc.gamehall;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.zlc.common.Config;

/**
 * 与主窗体内容有关的数据，MVC中的model
 * 
 * @author 赵景晨
 * 
 */
public class DataModel implements Config {
	/** 新闻列表的数据内容 */
	static DefaultListModel<String> newsListModel = new DefaultListModel<String>();
	/** 用户列表的数据内容 */
	static DefaultTableModel tableModel = new DefaultTableModel(
			new Object[][] {}, new String[] { "\u7528\u6237\u540D",
					"\u72B6\u6001" });
	/** 游戏目录树的内容 */
	static DefaultMutableTreeNode gameTreeRootModel = new DefaultMutableTreeNode();
	/** 消息版的默认提示语 */
	static String messageNote = "【聊天区域，请使用文明用语。】";
	/** 游戏名与游戏简介图片路径的对应map */
	static Map<String, String> imagePath = new HashMap<String, String>();
	/** 游戏名与游戏简介信息的对应map */
	static Map<String, String> gameInfo = new HashMap<String, String>();
	/** 游戏名与游戏服务器接口的对应map */
	static Map<String, Integer> gamePort = new HashMap<String, Integer>();
	/** 游戏名与游戏状态枚举类型值的对应map */
	static Map<String, GameState> gameState = new HashMap<String, GameState>();

	/**
	 * 无参构造函数，进行数据初始化
	 */
	public DataModel() {
		initNewsList();
		initgameTree();
		initImagePathMap();
		initGameInfoMap();
		initGamePortMap();
		initGameStateMap();
	}

	/**
	 * 初始化游戏名与游戏状态枚举类型值的对应map
	 */
	private void initGameStateMap() {
		gameState.put("泡泡堂", GameState.泡泡堂);
		gameState.put("你画我猜", GameState.你画我猜);
		gameState.put("扫雷", GameState.扫雷);
		gameState.put("俄罗斯方块", GameState.俄罗斯方块);

	}

	/**
	 * 初始化游戏名与游戏服务器接口的对应map
	 */
	private void initGamePortMap() {
		gamePort.put("泡泡堂", PPT_PORT);
		gamePort.put("你画我猜", NHWC_PORT);
		gamePort.put("扫雷", SL_PORT);
		gamePort.put("俄罗斯方块", ELSFK_PORT);

	}

	/**
	 * 初始化游戏名与游戏简介信息的对应map
	 */
	private void initGameInfoMap() {
		gameInfo.put(
				"泡泡堂",
				"<html>《泡泡堂》英文简写BNB，全名Crazy Arcade，是韩国Nexon公司模仿经典红白机游戏《炸弹人》制作而成的一款家庭休闲网游，中国由盛大网络代理。游戏可以一机双人操作，控制简便，只要使用四个方向键控制方向，再加上一个空格键放置泡泡CTRL键使用特殊道具，6个按键就可以进行游戏了。<br/>通常，游戏是一个利用水泡把对手困住后（称为泡封）再刺破困住玩家的水泡后（称为击破）获得胜利。然而不同的游戏模式有不同的取胜方法。依照房间设定可以选择对等人数（所有队伍人数均相同）游戏或无限制方式（队伍的人数数量不限制）游戏。</html>");
		gameInfo.put(
				"你画我猜",
				"<html>你画我猜是一款画图猜单词的小游戏。玩家根据软件提示，以速写的方式画一张图片来描述某个单词，如“猫”、“汽车”等。随后，这幅图片会被发送至另一个玩家，后者将根据图片猜测并拼写单词。如果解答正确，两位玩家均会获得“金币”奖励。</html>");
		gameInfo.put(
				"扫雷",
				"<html>扫雷是一款相当大众的小游戏，游戏目标是在最短的时间内根据点击格子出现的数字找出所有非雷格子，同时避免踩雷。扫雷最初的流行伴随着1992年发布的windows 3.1，之后迅速成为了各种操作系统中必不可少的一款游戏，也出现于一些智能手机平台（比如Android系统的Minesweeper Classic，苹果ios系统的Minesweeper Q）。同时，Minesweeper Clone等功能更为丰富的“专业”扫雷软件也应时而生。</html>");
		gameInfo.put(
				"俄罗斯方块",
				"<html>俄罗斯方块是一款风靡全球的电视游戏机和掌上游戏机游戏，它由俄罗斯人阿列克谢•帕基特诺夫发明，故得此名。俄罗斯方块的基本规则是移动、旋转和摆放游戏自动输出的各种方块，使之排列成完整的一行或多行并且消除得分。由于上手简单、老少皆宜，从而家喻户晓，风靡世界.</html>");
		gameInfo.put(
				"帝国时代",
				"<html>《帝国时代》（英语：Age of Empires，简称AoE；港台地区译为“世纪帝国”）是一个根据历史而制作的即时战略游戏。游戏由全效工作室开发，微软公司于1997年发行到Windows和Macintosh平台。这款可以让玩家操纵历史上某个真实民族的游戏有着许多优点：低配置需求、变化性、趣味性，常常使玩家乐此不疲，受到电玩市场热烈的欢迎。<br/>《帝国时代》的基本玩法是要求玩家控制一个文明，并从狩猎收集时代发展成一个庞大的铁器时代帝国。为了确保胜利，玩家必须收集尽可能多的资源以生产新的单位、建筑并研发更高级的科技。</html>");
		gameInfo.put("诛仙",
				"<html>由北京完美时空网络技术有限公司根据萧鼎所著同名仙侠小说改编开发的网络游戏《诛仙》，后又发行了《诛仙2》。</html>");
		gameInfo.put(
				"红色警戒",
				"<html>命令与征服：红色警戒，英文名称Red Alert，又译“红色警报”，是美国艺电游戏公司（EA，Electronic Arts）（红色警戒2原版前是Westwood Studio）为个人电脑（PC）推出的一系列即时战略游戏，玩家通常简称为红警或RA。</html>");
		gameInfo.put(
				"魔兽世界",
				"<html>《魔兽世界》（World of Warcraft）是由著名游戏公司暴雪娱乐所制作的第一款网络游戏，属于大型多人在线角色扮演游戏。游戏以该公司出品的即时战略游戏《魔兽争霸》的剧情为历史背景，依托魔兽争霸的历史事件和英雄人物，魔兽世界有着完整的历史背景时间线。玩家在魔兽世界中冒险、完成任务、新的历险、探索未知的世界、征服怪物等。</html>");

	}

	/** 初始化游戏名与游戏简介图片路径的对应map */
	private void initImagePathMap() {
		imagePath.put("泡泡堂", "hallImage/ppt.jpg");
		imagePath.put("你画我猜", "hallImage/nhwc.jpg");
		imagePath.put("扫雷", "hallImage/sl.jpg");
		imagePath.put("俄罗斯方块", "hallImage/elsfk.jpg");
		imagePath.put("帝国时代", "hallImage/dgsd.jpg");
		imagePath.put("诛仙", "hallImage/zx.jpg");
		imagePath.put("红色警戒", "hallImage/hsjj.jpg");
		imagePath.put("魔兽世界", "hallImage/mssj.jpg");
		imagePath.put("log", "hallImage/log.png");
		imagePath.put("exit", "hallImage/exit.png");
		imagePath.put("sendMessage", "hallImage/message.png");
		imagePath.put("enterRoom", "hallImage/enter.gif");
		imagePath.put("waitPlayer", "hallImage/wait.gif");

	}

	/** 初始化用户列表 */
	void initNewsList() {
		newsListModel.addElement("【置顶】ZJU游戏大厅V3.0Beta 正式公测");
		newsListModel.addElement("\n");
		newsListModel.addElement("(2013-12-27)泡泡堂V 4.0 正式上线");
		newsListModel.addElement("(2013-12-25)你画我猜V 2.0 正式上线");
		newsListModel.addElement("(2013-12-21)扫雷V 1.0 正式上线");
		newsListModel.addElement("(2013-12-20)ZJU游戏大厅V2.0 正式上线");
		newsListModel.addElement("(2013-12-20)泡泡堂V 3.0 正式上线");
		newsListModel.addElement("(2013-12-18)ZJU游戏大厅V1.5 开启公测");
	}

	/** 初始化游戏目录树 */
	void initgameTree() {
		DefaultMutableTreeNode xxjj, yzmj, jsby, jszl;
		xxjj = new DefaultMutableTreeNode("休闲竞技");
		yzmj = new DefaultMutableTreeNode("益智敏捷");
		jsby = new DefaultMutableTreeNode("角色扮演");
		jszl = new DefaultMutableTreeNode("即时战略");
		gameTreeRootModel = new DefaultMutableTreeNode("游戏目录");

		xxjj.add(new DefaultMutableTreeNode("泡泡堂"));
		xxjj.add(new DefaultMutableTreeNode("你画我猜"));
		yzmj.add(new DefaultMutableTreeNode("扫雷"));
		yzmj.add(new DefaultMutableTreeNode("俄罗斯方块"));
		jsby.add(new DefaultMutableTreeNode("魔兽世界"));
		jsby.add(new DefaultMutableTreeNode("诛仙"));
		jszl.add(new DefaultMutableTreeNode("红色警戒"));
		jszl.add(new DefaultMutableTreeNode("帝国时代"));

		gameTreeRootModel.add(xxjj);
		gameTreeRootModel.add(yzmj);
		gameTreeRootModel.add(jsby);
		gameTreeRootModel.add(jszl);
	}
}
