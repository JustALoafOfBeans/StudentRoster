package roster;

/**
 An enum class containing all the majors and corresponding codes and schools
 both as Strings. Call values using "Major.<MAJOR>.<code/school>". For example
 "Major.MATH.code" will return "01:198" as a String.
 @author Victoria Chen, Bridget Zhang
 */
public enum Major {
    CS ("01:198", "SAS"),
    MATH ("01:640", "SAS"),
    EE ("14:332", "SOE"),
    ITI ("04:547", "SC&I"),
    BAIT ("33:136", "RBS");

    public final String code;
    public final String school;

    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }
}
