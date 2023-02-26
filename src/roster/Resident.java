package roster;

/**
 The Resident class is a subclass of Student which provides methods to create
 Resident objects using no parameters and for manipulating the Resident
 fields.
 @author Victoria Chen
 */
public class Resident extends Student {
    private int scholarship;
    private int INITVALUE = 0;

    /**
     Constructs a new Student Resident initialized with 0 scholarship.
     */
    public Resident() {
        scholarship = INITVALUE;
    }

    /**
     Assigns a scholarship amount to a Student Resident.
     * @param scholarshipValue integer value of how much scholarship to assign.
     */
    public void assignScholarship(int scholarshipValue) {
        scholarship = scholarshipValue;
    }

    /**
     Returns how much scholarship a Student Resident currently has.
     * @return integer value of scholarship
     */
    public int checkScholarship() {
        return scholarship;
    }

    /**
     Returns the amount of tuition due for the student. Tuition is calculated
     based on the student's residential status and scholarship amount if
     applicable.
     * @param creditsEnrolled integer value of how many credits the student
     *                        is enrolled for.
     * @return double representing how much tuition the student has.
     */
    public double tuitionDue(int creditsEnrolled) {
        int PARTTTIME = 12;
        int FULLTIME = 16;
        int tuitionSum = INITVALUE;

        if (creditsEnrolled < PARTTTIME) {
            tuitionSum += (Tuition.PTRTUITION.fee * creditsEnrolled)
                    + Tuition.PTUNI.fee;
        } else if (creditsEnrolled < FULLTIME) {
            tuitionSum += Tuition.FRTUITION.fee + Tuition.FUNI.fee - scholarship;
        } else {
            tuitionSum += Tuition.FRTUITION.fee + Tuition.FUNI.fee - scholarship
                    + (Tuition.PTRTUITION.fee * (creditsEnrolled - FULLTIME));
        }
        return tuitionSum;
    }

    /**
     Method to tell if a student is valid given their number of credits.
     * @param creditEnrolled integer value of credits the student is
     *                       currently enrolled for.
     * @return true if the student has a valid amount of credits.
     */
    public boolean isValid(int creditEnrolled) {
        if (creditEnrolled < 3 || creditEnrolled > 24) {
            return false;
        }
        return true;
    }

    /**
     Method always returns true to indicate a Resident student is classified
     as resident.
     * @return true always.
     */
    public boolean isResident() {
        return true;
    }

}
