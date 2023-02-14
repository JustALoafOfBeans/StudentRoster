package roster;

/**
 An enum class that contains the possible standings of students based on
 their credits.
 @author Victoria Chen, Bridget Zhang
 */

public enum Standing {

    /**
     * Standing for freshmen with less than 30 credits
     */
    FRESHMAN("Freshman"),
    /**
     * Standing for sophomore with between 30 and 60 credits
     */
    SOPHOMORE("Sophomore"),
    /**
     * Standing for juniors with between 60 and 90 credits
     */
    JUNIOR("Junior"),
    /**
     * Standing for seniors with 90 or more credits
     */
    SENIOR("Senior");

    public final String title;

    Standing (String title) {
        this.title = title;
    }
}
