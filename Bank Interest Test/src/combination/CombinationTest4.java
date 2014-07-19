package combination;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest4 {

	@Test
	public void test() {
		Combination comb=new Combination();
		int res=comb.getMessage('C', 'A');
		assertEquals(res,4);
	}

}
