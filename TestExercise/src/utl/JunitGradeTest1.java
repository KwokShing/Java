package utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunitGradeTest1 {

	@Test
	public void testGrade1() {
	Grades g = new Grades();
	String mark= g.Grade(-1, -1);
	assertEquals("Marks out of range",mark);
	}
}
