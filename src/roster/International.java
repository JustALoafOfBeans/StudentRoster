package roster;

public class International extends NonResident {
    private boolean isStudyAbroad;
    private static int CREDITBRKPT = 12;
    private  static int MAXCREDITS = 24;

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
            interSum -= Tuition.FNRTUITION.fee; // International students don't pay tuition
            // if
            // abroad
        }
        return interSum;
    }

    /**
     Method overrides isValid(int creditEnrolled) from Student class. Return
     value is based on the Student's study abroad status and number of
     credits enrolled.
     * @param creditEnrolled integer value of credits the student is
     *                       currently enrolled for.
     * @return true if the student is study abroad and 12 or less credits
     * or if the student is not study abroad and 12 or more credits. False
     * otherwise.
     */
    @Override
    public boolean isValid(int creditEnrolled) {
        if (isStudyAbroad && creditEnrolled <= CREDITBRKPT) {
            return true;
        } else if (!isStudyAbroad && creditEnrolled >= CREDITBRKPT && creditEnrolled <= MAXCREDITS){
            return true;
        }
        return false;
    }

    /**
     Method returns what type of student this object is.
     * @return International as a string. May have study abroad if applicable.
     */
    public String returnType() {
        String ans = "International Student";
        if (isStudyAbroad) {
            ans += "study abroad";
        }
        return ans;
    }
}
