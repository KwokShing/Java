package combination;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest6 {

	@Test
	public void test() {
		Combination comb=new Combination();
		int res=comb.getMessage('B', 'A');
		assertEquals(res,2);
	}

}
