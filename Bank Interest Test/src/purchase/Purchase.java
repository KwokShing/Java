package purchase;

public class Purchase {
	public static String getDiscount(float money) {
		if (money <= 0)
			return "invalid";
		else if (money > 0 && money <= 50)
			return "no discount";
		else if (money > 50 && money <= 200)
			return "5%";
		else if (money > 200 && money <= 500)
			return "10%";
		else
			return "15%";
	}
}
