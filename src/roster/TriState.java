package roster;

public class TriState extends NonResident {

    private String state;

    public TriState(String studentParam, String studentState) { // TriState constructor, using Student
        // todo this constructor too
        super(studentParam);
        state = studentState;
    }

    public int getCreditCompleted() {
        return super.getCreditCompleted();
    }

    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        int PARTTTIME = 12;
        double tristateSum = super.tuitionDue(creditsEnrolled);
        if (creditsEnrolled >= PARTTTIME) {
            if (state.toUpperCase().compareTo("NY") == 0) { // NY tristate discount
                tristateSum -= 4000;
            } else if (state.toUpperCase().compareTo("CT") == 0) { // CT tristate discount // todo check abbrev
                tristateSum -= 5000;
            }
        }
        return tristateSum;
    }

}
