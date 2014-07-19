package utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunitGradeTest2 {

	@Test
	public void test() {
		Grades g = new Grades();
		String mark= g.Grade(50, 50);
		assertEquals("Pass,C",mark);
	}

}
