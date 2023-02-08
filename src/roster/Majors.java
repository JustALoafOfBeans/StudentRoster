package roster;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 An enum class containing all the majors and corresponding codes and schools.
 Class still needs to be tested but Imma go to bed for now.
 @author Victoria Chen, Bridget Zhang
 */
public enum Majors {
    CS ("01:198", "SAS"),
    MATH ("01:640", "SAS"),
    EE ("14:332", "SOE"),
    ITI ("04:332", "SC&I"),
    BAIT ("33:136", "RBS");

    private final String code;
    private final String school;

    Majors(String code, String school) {
        this.code = code;
        this.school = school;
    }
}
