package roster;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 Test class for tuitionDue() method of International class
 @author Victoria Chen, Bridget Zhang
 */
public class InternationalTest {

    /**
     * Should return accurate cost: tuition + uni fee + health insurance fee
     */
    @Test
    public void test_tuitionDue_notAbroad() {
        International notAbroad_student = new International("Jim Smith 12/14/2001 EE 10", false);
        double notAbroad_tuition = notAbroad_student.tuitionDue(12);
        double notAbroad_expected = 35655;
        assertEquals(notAbroad_expected, notAbroad_tuition, 0);
    }

    /**
     * Checks that student in study abroad program does not pay tuition
     * Should return accurate cost: uni fee + health insurance fee
     * */
    @Test
    public void test_tuitionDue_abroad() {
        International abroad_student = new International("Jim Smith 12/14/2001 EE 10", true);
        double abroad_tuition = abroad_student.tuitionDue(12);
        double abroad_expected = 5918;
        assertEquals(abroad_expected, abroad_tuition, 0);
    }
}