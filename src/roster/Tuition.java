package roster;

/**
 An enum class containing all tuitions and fees for students (both Resident
 and NonResident). Codes for FullTime students begin with an F and PartTime
 students a PT. Resident students have an R and NonResident students have NR.
 Call values using "Tuition.code.fee". For example "Tuition.FUNI.fee" will
 return 3268 as an integer representing the University fee for a FullTime
 student.
 @author Victoria Chen
 */
public enum Tuition {
    /**
     * FullTime Resident tuition
     */
    FRTUITION (12536),
    /**
     * FullTime NonResident tuition
     */
    FNRTUITION (29737),
    /**
     * FullTime University fee for Resident and NonResident
     */
    FUNI (3268),
    /**
     * FullTime International student health insurance fee
     */
    FIHEALTH (2650),
    /**
     * PartTime Resident tuition (per credit)
     */
    PTRTUITION (404),
    /**
     * PartTime NonResident tuition (per credit)
     */
    PTNRTUITION (966),
    /**
     * PartTime University fee for Resident and NonResident
     */
    PTUNI (2614);

    /**
     * Integer value for the fee
     */
    public final int fee;

    /**
     * Attributes of Tuition that designate the values to fee
     * @param fee designates how much the fee is
     */
    Tuition(int fee) {
        this.fee = fee;
    }

}
