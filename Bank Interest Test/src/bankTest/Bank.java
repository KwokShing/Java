package bankTest;

public class Bank {
	public static String getInterest(int money)
	{
		if(money<0)
			return "invalid";
		else if(money>=0&&money<=100)
			return "3%";
		else if(money>100&&money<=1000)
			return "5%";
		else
			return "7%";		
	}

}
