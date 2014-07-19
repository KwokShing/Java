package combination;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest3 {

	@Test
	public void test() {
		Combination comb=new Combination();
		int res=comb.getMessage('C', '1');
		assertEquals(res,3);
	}

}
