package combination;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest1 {

	@Test
	public void test() {
		Combination comb=new Combination();
		int res=comb.getMessage('B', '1');
		assertEquals(res,1);
	}

}
