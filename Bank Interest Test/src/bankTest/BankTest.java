package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void testGetInterest() {
		Bank bank = new Bank();
		String interest = bank.getInterest(50);
		assertEquals(interest, "3%");
	}

}
