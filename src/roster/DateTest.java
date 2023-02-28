package roster;

import static org.junit.Assert.*;

public class DateTest {
    @org.junit.Test
    public void test_isValid_feb_leap() {
        Date nonleap_date = new Date("2/29/2001");
        assertFalse(nonleap_date.isValid());
    }

    /**
     * Should return TRUE for any date prior to today on the calendar
     */
    @org.junit.Test
    public void test_isValid_priorCurr() {
        Date prior_date = new Date("10/24/2001");
        assertTrue(prior_date.isValid());
    }

    /**
     * Should return TRUE for 29 days in February if it is a leap year
     */
    @org.junit.Test
    public void test_isValid_leap() {
        Date leap_date = new Date("2/29/2004");
        assertTrue(leap_date.isValid());
    }

    /**
     * Should return FALSE for any birthdate after today
     */
    @org.junit.Test
    public void test_isValid_futurebirthday () {
        Date future_date = new Date("5/18/2030");
        assertFalse(future_date.isValid());
    }

    /**
     * Should return FALSE for any day over 28 in February for a non-leap year
     */
    @org.junit.Test
    public void test_isValid_nonleap() {
        Date nonleap_date = new Date("2/29/2003");
        assertFalse(nonleap_date.isValid());
    }

    /**
     * Should return FALSE if specified month does not have 31 days
     */
    @org.junit.Test
    public void test_isValid_checkmonthlength() {
        Date april_extraday_date = new Date("4/31/2003");
        assertFalse(april_extraday_date.isValid());
    }

    /**
     * Should return FALSE if month number is not between 1 and 12
     */
    @org.junit.Test
    public void test_isValid_bigmonth() {
        Date bigmonth_date = new Date("13/31/2003");
        assertFalse(bigmonth_date.isValid());
    }

    /**
     * Should return FALSE if any month has >31 days
     */
    @org.junit.Test
    public void test_isValid_toomanydays() {
        Date toomanydays_date = new Date("3/32/2003");
        assertFalse(toomanydays_date.isValid());
    }

    /**
     * Should return FALSE if month number is not between 1 and 12
     */
    @org.junit.Test
    public void test_isValid_smallmonth() {
        Date smallmonth_date = new Date("-1/31/2003");
        assertFalse(smallmonth_date.isValid());
    }

}