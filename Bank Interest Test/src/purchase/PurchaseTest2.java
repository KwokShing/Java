package purchase;

import static org.junit.Assert.*;

import org.junit.Test;

public class PurchaseTest2 {

	@Test
	public void test() {
		Purchase purchase = new Purchase();
		String res = purchase.getDiscount(100);
		assertEquals(res, "5%");
	}

}
