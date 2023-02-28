package roster;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    /**
     * Should return TRUE for adding International to roster
     */
    @Test
    public void test_add_international_success() {
        Roster test_roster = new Roster();
        String studentStr = "Jim Smith 12/14/2001 EE 10";
        International international_new = new International(studentStr, false);
        assertTrue(test_roster.add(international_new));
    }

    /**
     * Should return FALSE for adding International to roster
     */
    @Test
    public void test_add_international_false() {
        Roster test_roster = new Roster();
        String studentStr = "Jim Smith 12/14/2001 EE 10";
        International international_new = new International(studentStr, false);
        test_roster.add(international_new);
        assertFalse(test_roster.add(international_new));
    }

    /**
     * Should return TRUE for adding Tristate to roster
     */
    @Test
    public void test_add_tristate_success() {
        Roster test_roster = new Roster();
        String studentStr = "Jim Smith 12/14/2001 EE 10";
        String studentState = "NY";
        TriState tristate_new = new TriState(studentStr, studentState);
        assertTrue(test_roster.add(tristate_new));
    }

    /**
     * Should return FALSE for adding Tristate to roster
     */
    @Test
    public void test_add_tristate_false() {
        Roster test_roster = new Roster();
        String studentStr = "Jim Smith 12/14/2001 EE 10";
        String studentState = "NY";
        TriState tristate_new = new TriState(studentStr, studentState);
        test_roster.add(tristate_new);
        assertFalse(test_roster.add(tristate_new));
    }
}