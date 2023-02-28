package roster;

import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    @Test
    public void test_tuitionDue_notAbroad() {
        International notAbroad_student = new International("Jim Smith 12/14/2001 EE 10", false);
        double notAbroad_tuition = notAbroad_student.tuitionDue(12);
        double notAbroad_expected = 35655;
        assertEquals(notAbroad_expected, notAbroad_tuition, 0);
    }

    @Test
    public void test_tuitionDue_abroad() {
        International abroad_student = new International("Jim Smith 12/14/2001 EE 10", true);
        double abroad_tuition = abroad_student.tuitionDue(12);
        double abroad_expected = 5918;
        assertEquals(abroad_expected, abroad_tuition, 0);
    }
}