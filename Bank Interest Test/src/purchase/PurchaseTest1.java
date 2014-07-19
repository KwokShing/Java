package purchase;

import static org.junit.Assert.*;

import org.junit.Test;

public class PurchaseTest1 {

	@Test
	public void test() {
		Purchase purchase = new Purchase();
		String res = purchase.getDiscount(25);
		assertEquals(res, "no discount");
	}

}
