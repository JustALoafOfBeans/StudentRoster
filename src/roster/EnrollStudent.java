package roster;

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent(String enrollInput) { // Constructor
        // todo check input, takes in String eg. "Emma Miller 2/28/2003 8"
        // Expect profile to conclude just before 3rd space
        String profileStr = "", creditStr = ""; // todo is this syntax legal for Chang
        int spaceCount = 0;
        for (int ind = 0; ind < enrollInput.length(); ind++) {
            if (enrollInput.charAt(ind) == ' ') { // Look for " " delimiter
                spaceCount += 1;
            }
            if (spaceCount == 3) { // Encountered 3rd space
                profileStr = enrollInput.substring(0, ind);
                // Expect credits after space following profile, goes to end
                creditStr = enrollInput.substring(ind+1);
                break;
            }
        }
        profile = new Profile(profileStr);
        creditsEnrolled = Integer.parseInt(creditStr);
    }

    @Override
    public String toString() {
        // Format: [profile as FIRST LAST DOB]: credits enrolled: [credits]
        String strEnroll = profile.toString();
        strEnroll += ": credits enrolled: ";
        strEnroll += creditsEnrolled;
        return strEnroll;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getCredits() {
        return creditsEnrolled;
    }

    public void setCredits(int newCredits) {
        creditsEnrolled = newCredits;
    }

    public static void main (String[] args) {
        EnrollStudent testEnroll = new EnrollStudent("Emma Miller 2/28/2003 8");
        System.out.println(testEnroll.toString());
    }
}
