

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CourseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CourseTest
{
    /**
     * Default constructor for test class CourseTest
     */
    public CourseTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void gpa()
    {
        Course course1 = new Course("GEND", 1000, "A+");
        course1.setLetterGrade("A+");
        assertEquals(4.0, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("A");
        assertEquals(4.0, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("A-");
        assertEquals(3.7, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("B+");
        assertEquals(3.3, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("B");
        assertEquals(3.0, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("B-");
        assertEquals(2.7, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("C+");
        assertEquals(2.3, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("C");
        assertEquals(2.0, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("C-");
        assertEquals(1.7, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("D+");
        assertEquals(1.5, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("D");
        assertEquals(1.0, course1.getGradePoint(), 0.1);
        course1.setLetterGrade("F");
        assertEquals(0.0, course1.getGradePoint(), 0.1);
    }

    @Test
    public void testToString()
    {
        Course course1 = new Course("COMP", 1000, "A");
        assertEquals("COMP 1000  A\t(worth = 0.0)", course1.toString());
        course1.setLetterGrade("A+");
        course1.setNumber(100);
        course1.setSubject("CP");
        assertEquals("CP 0100  A+\t(worth = 0.0)", course1.toString());
        course1.setLetterGrade(" ");
        course1.setNumber(0);
        course1.setSubject("1234");
        assertEquals("1234 0000   \t(worth = 0.0)", course1.toString());
        course1.setLetterGrade("2");
        course1.setNumber(0);
        course1.setSubject("14");
        assertEquals("14 0000  2\t(worth = 0.0)", course1.toString());
    }
}

