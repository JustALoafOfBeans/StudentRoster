package roster;

public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;

    private static final int FRESHMANCRED = 30; // Credit cutoff for freshmen
    private static final int SOPHOMORECRED = 60; // Credit cutoff for sophomores
    private static final int JUNIORCRED = 90; // Credit cutoff for juniors

    /**
     Constructor for a Student object from user's input
     @param studentInput as with tokens as string
     */
    public Student(String studentInput) {
        int tokenStart, tokenEnd; // Tracks beginning and end of each token
        tokenStart = 0;
        int tokenNum = 0; // Number of tokens retrieved

        String firstName = "", lastName = "", dateOfBirth = ""; // For Profile (convert DOB to Date)

        // Parse string, extracting tokens as encounter " " delimiter
        for (int ind = 0; ind < studentInput.length(); ind++) {
            if (studentInput.charAt(ind) == ' ') { // Look for closing " " delimiter
                tokenEnd = ind; // Space is end (non-inclusive) of token
                // Set appropriate token
                if (tokenNum == 0) {
                    firstName = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 1) {
                    lastName = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 2) {
                    dateOfBirth = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 3) {
                    String majorString = studentInput.substring(tokenStart, tokenEnd);
                    System.out.println("read in major " + majorString);
                    switch (majorString) {
                        case "EE":
                            major = Major.EE;
                            break;
                        case "ITI":
                            major = Major.ITI;
                            break;
                        case "BAIT":
                            major = Major.BAIT;
                            break;
                        case "CS":
                            major = Major.CS;
                        case "MATH":
                            major = Major.MATH;
                    }
                }

                tokenNum += 1; // Incr num of tokens retrieved
                tokenStart = tokenEnd + 1; // Reset start of token
            }
            if (ind == studentInput.length() - 1) { // Last char, end of credits
                tokenEnd = ind + 1;
                creditCompleted = Integer.parseInt(studentInput.substring(tokenStart, tokenEnd));
            }
        }

        profile = new Profile(firstName + " " + lastName + " " + dateOfBirth);
    }

    /**
     Dummy constructor for Student used for Find() in Roster
     Used when only given profile information for removal or search
     * @param profInput given profile information
     */
    public Student(Profile profInput) { // todo take in String? or profInput
        profile = profInput;
    }

    /**
     Converts Student object to String format
     Overrides toString() from Comparable interface
     * @return Student in String format for print
     */
    @Override
    public String toString() {
        return profile.toString() + " " + major.name() + " " + creditCompleted;
    }

    /**
     Compares this student to another
     Overrides compareTo() from Comparable interface
     * @param compareStudent student that this is being compared to
     * @return positive integer if this is greater, 0 if same, negative if less
    */
    @Override
    public int compareTo(Student compareStudent) {
        // Compare profile
        int compProf = profile.compareTo(compareStudent.getProfile());
        if (compProf != 0) {
            return compProf;
        }
        // Compare major
        String thisMaj = major.name();
        String compareMaj = compareStudent.getMajor().name();
        int compMaj = thisMaj.compareTo(compareMaj);
        if (compMaj != 0) {
            return compMaj;
        }
        // Finally, compare credits (if same, will return 0)
        return creditCompleted - compareStudent.getCreditCompleted(); // todo this legal? for code std
    }

    /**
     Checks ir two students are considered "equal"
     Overrides equals() from Object class
     * @param equalObject student to be compared to
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object equalObject) {
        if (equalObject instanceof Student)
        {
            Student equalStudent = (Student) equalObject; // Cast into Student if can
            return (this.compareTo(equalStudent) == 0);
        }
        return false; // Not of type student, invalid comparison
    }

    /**
     Helper method that returns profile for comparison
     * @return Profile attached to Student object
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     Helper method that returns major for comparison
     * @return Major value attached to Student object
     */
    public Major getMajor() {
        return major;
    }

    /**
     Helper method that returns credit completed
     * @return credits completed by Student
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }

    /**
     Helper method that returns standing based on credits completed
     * @return value of Standing enum
     */
    public Standing getStanding() {
        if (creditCompleted < FRESHMANCRED) { // Less than 30 credits
            return Standing.FRESHMAN;
        } else if (creditCompleted < SOPHOMORECRED) { // 30 to 59 credits
            return Standing.SOPHOMORE;
        } else if (creditCompleted < JUNIORCRED) { // 60 to 89 credits
            return Standing.JUNIOR;
        } else {
            return Standing.SENIOR;
        }
    }

    /**
     Method that changes a student's major
     * @param newMaj major that Student changed to
     * @return true if changed successfully, false if new major invalid
     */
    public boolean changeMajor(String newMaj) {
        switch (newMaj) {
            case "EE":
                major = Major.EE;
                break;
            case "ITI":
                major = Major.ITI;
                break;
            case "BAIT":
                major = Major.BAIT;
                break;
            case "CS":
                major = Major.CS;
                break;
            case "MATH":
                major = Major.MATH;
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     Helper method that checks if Student is valid
     * @return true if valid, false if not
     */
    public boolean isValid () {
        if (!profile.getDob().checkIfSixteen()) { // Under 16 years old
            return false;
        }
        if (!profile.getDob().isValid()) {
            return false;
        }
        return true;
        // todo think only need to check date?
    }

    // todo testing remove later
    public static void main(String[] args) {
        Student testStudent = new Student("John Doe 9/2/2022 EE 10");
        System.out.println(testStudent.getProfile().toString());
        //System.out.println(major.code);
        System.out.println("Credits completed: " + testStudent.getCreditCompleted());
    }
}
