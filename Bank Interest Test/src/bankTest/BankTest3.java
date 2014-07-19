package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest3 {

	@Test
	public void testGetInterest() {
		Bank bank = new Bank();
		String interest = bank.getInterest(2000);
		assertEquals(interest, "7%");
	}

}
