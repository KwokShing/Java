package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest4 {

	@Test
	public void testGetInterest() {
		Bank bank = new Bank();
		String interest = bank.getInterest(-1);
		assertEquals(interest, "invalid");
	}

}
