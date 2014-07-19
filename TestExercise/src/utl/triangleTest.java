package utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class triangleTest {

	@Test
	public void testIsEquilateral() {
		Triangle t=new Triangle();
		boolean result= t.isEquilateral(2,2,2);
		assertTrue(result);
	}

}
