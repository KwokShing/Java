package utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunitGradesTest3 {

	@Test
	public void testGrade() {
		Grades g = new Grades();
		String mark = g.Grade(90, 30);
		assertEquals("Pass,A", mark);
	}

}
