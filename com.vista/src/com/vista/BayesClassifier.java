package com.vista;

import com.vista.ClassConditionalProbability;
import com.vista.PriorProbability;
import com.vista.TrainingDataManager;
import com.vista.StopWordsHandler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
* 朴素贝叶斯分类器
*/
public class BayesClassifier 
{
	private TrainingDataManager tdm;//训练集管理器
	private String trainnigDataPath;//训练集路径
	private static double zoomFactor = 10.0f;
	/**
	* 默认的构造器，初始化训练集
	*/
	public BayesClassifier() 
	{
		tdm =new TrainingDataManager();
	}

	/**
	* 计算给定的文本属性向量X在给定的分类Cj中的类条件概率
	* <code>ClassConditionalProbability</code>连乘值
	* @param X 给定的文本属性向量
	* @param Cj 给定的类别
	* @return 分类条件概率连乘值，即<br>
	*/
	float calcProd(String[] X, String Cj) 
	{
		float ret = 0.0F;
		// 类条件概率连乘
		for (int i = 0; i <X.length; i++)
		{
			String Xi = X[i];
			//因为结果过小，因此在连乘之前放大10倍，这对最终结果并无影响，因为我们只是比较概率大小而已
			ret += Math.log(ClassConditionalProbability.calculatePxc(Xi, Cj));
			//ret *=ClassConditionalProbability.calculatePxc(Xi, Cj)*zoomFactor;
		}
		// 再乘以先验概率
		//ret *= PriorProbability.calculatePc(Cj);
		return ret;
	}
	/**
	* 去掉停用词
	* @param text 给定的文本
	* @return 去停用词后结果
	*/
	public String[] DropStopWords(String[] oldWords)
	{
		Vector<String> v1 = new Vector<String>();
		for(int i=0;i<oldWords.length;++i)
		{
			if(StopWordsHandler.IsStopWord(oldWords[i])==false)
			{//不是停用词
				v1.add(oldWords[i]);
			}
		}
		String[] newWords = new String[v1.size()];
		v1.toArray(newWords);
		return newWords;
	}
	/**
	* 对给定的文本进行分类
	* @param text 给定的文本
	* @return 分类结果
	*/
	@SuppressWarnings("unchecked")
	public String classify(String text) 
	{
		String[] terms = null;
		terms= text.split(" ");//中文分词处理(分词后结果可能还包含有停用词）
		terms = DropStopWords(terms);//去掉停用词，以免影响分类
		
		String[] Classes = tdm.getTraningClassifications();//分类
		float probility = 0.0F;
		List<ClassifyResult> crs = new ArrayList<ClassifyResult>();//分类结果
		for (int i = 0; i <Classes.length; i++) 
		{
			String Ci = Classes[i];//第i个分类
			probility = calcProd(terms, Ci);//计算给定的文本属性向量terms在给定的分类Ci中的分类条件概率
			//保存分类结果
			ClassifyResult cr = new ClassifyResult();
			cr.classification = Ci;//分类
			cr.probility = probility;//关键字在分类的条件概率
			System.out.println("In process....");
			System.out.println(Ci + "：" + probility);
			crs.add(cr);
		}
		//对最后概率结果进行排序
		java.util.Collections.sort(crs,new Comparator() 
		{
			public int compare(final Object o1,final Object o2) 
			{
				final ClassifyResult m1 = (ClassifyResult) o1;
				final ClassifyResult m2 = (ClassifyResult) o2;
				final double ret = m1.probility - m2.probility;
				if (ret < 0) 
				{
					return 1;
				} 
				else 
				{
					return -1;
				}
			}
		});
		//返回概率最大的分类
		return crs.get(0).classification;
	}
	
	public static void main(String[] args)
	{
		String text = "年 月 日 来源 搜狐 体育 作者 橙色 郁金香 每日 邮报 本 特纳 想 去 皇马 巴萨 吉 鲁本 赛季 火爆 的 状态 让 诸多 阿森纳 球迷 感到 非常 幸福 但 有 一个 人 却 很 不 高兴 他 就是 本 特纳 英超 已经 激战 了 轮 丹麦 人 仅仅 出场 次 总计 分钟 没有 为 球队 贡献 粒 进球 或 次 助攻 本 特纳 对此 显然 很 不 开心 在 接受 采访 时 丹麦 人 表示 对于 我 来说 踢球 最 重要 的 不是 金钱 而是 在 那里 我能 找到 踢球 的 快乐 我 希望 可以 不断 地 进球 和 球队 一起 赢得 什么 东西 我 不想 一直 坐在 板凳 上 我 喜欢 踢 许多许多 的 比赛 虽然 在 枪手 枯坐 替补席 但是 欧洲 不乏 有 球队 对 丹麦 人 表达 了 浓厚 的 兴趣 但是 本 特纳 高达 万英镑 的 周薪 是 阻碍 其 转会 的 重要 障碍 丹麦 人 也 因此 被 人 诟病 为 贪图 金钱 的 球员 当 被 问及 希望 在 哪支 球队 继续 自己 的 职业生涯 时 本 特纳 一语惊人 皇家 马德里 或者 巴塞罗那 看来 这名 自诩 世界 第一 前锋 的 丹麦 人 自信心 还 不是 一般 的 强 本赛季 罗在 场 俱乐部 赛事 中 打入 粒 进球 而梅西 虽然 饱受 伤病 困扰 但 依然 在 场 比赛 中 打入 球 对于 如今 毫无 斩获 的 本 特纳 来说 转会 皇马 亦 或是 巴萨 又 能 有何 作为 难不成 是 继续 去 看 饮水机 么";
		BayesClassifier classifier = new BayesClassifier();//构造Bayes分类器
		String result = classifier.classify(text);//进行分类
		System.out.println("此项属于["+result+"]");
	}
}