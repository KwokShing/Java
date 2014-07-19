package purchase;

import static org.junit.Assert.*;

import org.junit.Test;

public class PurchaseTest4 {

	@Test
	public void test() {
		Purchase purchase = new Purchase();
		String res = purchase.getDiscount(600);
		assertEquals(res, "15%");
	}

}
