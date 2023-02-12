package roster;

public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    private static final int ASCIILOWERA = 97;      // ascii value for lower case a
    private static final int ASCIICASEDIFF = 32;    // difference from upper to lower case ascii values

    /**
     Constructor for Profile class
     @param profLast the last name for the new profile, given as String
     @param profFirst the first name for the new profile, given as String
     @param profDate the date of birth for the new profile, given as a Date
     */
    public Profile(String profLast, String profFirst, Date profDate) {
        lname = profLast;
        fname = profFirst;
        dob = profDate;
    }

    /**
     Method to give Profile in String format
     Overrides toString() from Comparable interface
     @return profile as a String in format [first name] [last name] [dob]
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }

    /**
     Method to compare two Profiles
     Overrides compareTo() from Comparable interface
     @param compareProfile the Profile to be compared to
     @return 1 if this profile is "greater" and -1 if it is "less" than compared // todo valid?
     */
    public int compareTo(Profile compareProfile) {
        int compVal;
        // First compare last names
        compVal = lexicoCompare(this.lname, compareProfile.getLname());
        if (compVal != 0) {
            return (compVal < 0) ? -1 : 1;
        }

        // Last names same, next compare first names
        compVal = lexicoCompare(this.fname, compareProfile.getFname());
        if (compVal != 0) {
            return (compVal < 0) ? -1 : 1;
        }

        // First names same, compare DOBs
        compVal = dob.compareTo(compareProfile.getDob());
        if (compVal != 0) {
            return (compVal < 0) ? -1 : 1; //todo make sure this matches how Vic does Date compare
        }

        return 0; // Profiles same //todo
    }

    /**
     Method to check if two Profiles are equal
     Overrides equals() from Object class
     @param equalObject the Profile whose equality is checked against
     @return true if equal, false if not
     */
    public boolean equals(Object equalObject) {
        if (equalObject instanceof Profile) { // Check if Profile and cast
            Profile equalProfile = (Profile) equalObject;
            if (compareTo(equalProfile) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     Helper method that returns lname
     Used to get attributes for compared Profile objects
     @return lname of Profile object
     */
    public String getLname() {
        return lname;
    }

    /**
     Helper method that returns fname
     Used to get attributes for compared Profile objects
     @return fname of Profile object
     */
    public String getFname() {
        return fname;
    }

    /**
     Helper method that returns dob
     Used to get attributes for compared Profile objects
     @return dob of Profile object
     */
    public Date getDob() {
        return dob;
    }

    /**
     Helper method to compare two Strings lexicographically
     @param wordOne first String being compared
     @param wordTwo second String being compared
     @return 1 if wordOne "greater", -1 if it is "less", and 0 if equal to wordTwo
     */
    private int lexicoCompare(String wordOne, String wordTwo) {
        // Find length of shorter and compare through
        int shortWord = 1;
        int shortLen = wordOne.length();
        if (wordTwo.length() < shortLen) { // second word shorter
            shortLen = wordTwo.length();
            shortWord = 2;
        } else if (wordTwo.length() == shortLen) { // words equal length
            shortWord = 0;
        }

        int asciiOne, asciiTwo;
        for (int lIndex = 0; lIndex < shortLen; lIndex++) {
            // Get ascii values for chars, case-insensitive
            asciiOne = asciiLower((int) wordOne.charAt(lIndex));
            asciiTwo = asciiLower((int) wordTwo.charAt(lIndex));

            // Compare, if one larger then return
            if (asciiOne < asciiTwo) {
                return -1; // wordOne less
            } else if (asciiTwo < asciiOne) {
                return 1; // wordTwo less
            }
        }

        // Count longer word as greater
        if (shortWord == 0) {
            return 0; // Same length words, chars all equal
        } else if (shortWord == 1) {
            return -1; // wordTwo longer
        } else {
            return 1; // wordOne longer
        }
    }

    /**
     Helper method to convert ASCII values to lowercase for case-insensitive comparison
     @param asciiIn ASCII value in
     @return lowercase value of character
     */
    private int asciiLower (int asciiIn) {
        if (asciiIn < ASCIILOWERA) {
            // Value is capital, return lowercase
            return asciiIn + ASCIICASEDIFF;
        }
        // Value already lowercase, return as is
        return asciiIn;
    }
}
