package roster;

/**
 An enum class that contains the possible standings of students based on credits.
 @author Victoria Chen, Bridget Zhang
 */

public enum Standing {

    FRESHMAN("Freshman"),
    SOPHOMORE("Sophomore"),
    JUNIOR("Junior"),
    SENIOR("Senior");

    public final String title;

    Standing (String title) {
        this.title = title;
    }
}
