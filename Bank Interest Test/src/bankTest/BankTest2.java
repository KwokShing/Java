package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest2 {

	@Test
	public void test() {
		Bank bank = new Bank();
		String interest = bank.getInterest(500);
		assertEquals(interest, "5%");
	}

}
