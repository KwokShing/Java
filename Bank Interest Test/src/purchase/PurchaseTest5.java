package purchase;

import static org.junit.Assert.*;

import org.junit.Test;

public class PurchaseTest5 {

	@Test
	public void test() {
		Purchase purchase = new Purchase();
		String res = purchase.getDiscount(-1);
		assertEquals(res, "invalid");
	}

}
