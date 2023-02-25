package roster;

public class International extends NonResident {
    private boolean isStudyAbroad;
    private static final int TUITION = 29737;

    public International (String studentParam, boolean studentAbroad) { // International constructor, using Student
        // todo this constructor too
        super(studentParam);
        isStudyAbroad = studentAbroad;

        // todo should we check cred requirements for international students?
    }

    public int getCreditCompleted() {
        return super.getCreditCompleted();
    }

    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        double interSum = super.tuitionDue(getCreditCompleted());
        if (isStudyAbroad) {
            interSum -= TUITION; // International students don't pay tuition if abroad
        }
        return interSum;
    }
}
