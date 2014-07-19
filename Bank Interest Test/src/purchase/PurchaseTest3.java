package purchase;

import static org.junit.Assert.*;

import org.junit.Test;

public class PurchaseTest3 {

	@Test
	public void test() {
		Purchase purchase = new Purchase();
		String res = purchase.getDiscount(300);
		assertEquals(res, "10%");
	}

}
