package roster;

public class NonResident extends Student {

    private static final int PARTTIMECREDCOST = 966;
    private static final int FULLTIMECREDCOST = 29737;
    private static final int FULLTIMECREDCOUNT = 16;
    private static final int UNIFEE = 3268;
    private static final int HEALTHFEE = 2650;

    public NonResident(String studentParam) { // NonResident constructor, using Student
        // todo check constructor, should have no additional instance vars
        super(studentParam);
    }

    public double tuitionDue(int creditsEnrolled) { // From Abstract Student
        double tuitionSum = 0;
        if (creditsEnrolled < 12) { // Considered parttime
            tuitionSum += PARTTIMECREDCOST * creditsEnrolled;
            tuitionSum += UNIFEE * 0.8; // University fee
        } else { // Considered full time
            tuitionSum += FULLTIMECREDCOST;
            if ((creditsEnrolled - FULLTIMECREDCOUNT) > 0) { // Over 16 credits
                tuitionSum += (creditsEnrolled - FULLTIMECREDCOUNT) * PARTTIMECREDCOST;
            }
            tuitionSum += (UNIFEE + HEALTHFEE); // University and Health Insurance fees
        }

        return tuitionSum;
    }

    public boolean isResident() { // From Abstract Student
        return false;
    }

}
