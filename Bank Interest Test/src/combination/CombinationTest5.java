package combination;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest5 {

	@Test
	public void test() {
		Combination comb=new Combination();
		int res=comb.getMessage('A', 'A');
		assertEquals(res,2);
	}

}
