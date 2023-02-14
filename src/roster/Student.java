package roster;

/**
 The Student class is an abstract class which provides methods to create
 Student objects from a String or Profile object and for manipulating the
 Student fields. The main() method serves as a testbed to display
 functionality of the compareTo() function.
 @author Bridget Zhang
 */
public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;

    private static final int FRESHMANCRED = 30; // Credit cutoff for freshmen
    private static final int SOPHOMORECRED = 60; // Credit cutoff for sophomores
    private static final int JUNIORCRED = 90; // Credit cutoff for juniors
    private static final int ASCIILOWERA = 97;      // ascii value for lower case a
    private static final int ASCIICASEDIFF = 32;    // difference from upper to lower case ascii values

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
                    majorString = stringLower(majorString);
                    assignMajor(majorString);
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
        String strStudent = profile.toString(); // Profile (first, last, DOB)
        strStudent += " (" + major.code + " " + major.name() + " " + major.school + ")"; // Major
        strStudent += " credits completed: " + creditCompleted; // Credits
        strStudent += " (" + getStanding().title + ")";
        return strStudent;
    }

    /**
     Compares this student to another
     Overrides compareTo() from Comparable interface
     * @param compareStudent student that this is being compared to
     * @return 1 if this is greater, 0 if same, -1 if less
    */
    @Override
    public int compareTo(Student compareStudent) {
        // Compare profile
        int compProf = profile.compareTo(compareStudent.getProfile());
        if (compProf != 0) {
            return (compProf < 0) ? -1 : 1;
        }
        // Compare major
        String thisMaj = major.name();
        thisMaj = stringLower(thisMaj);
        String compareMaj = compareStudent.getMajor().name();
        compareMaj = stringLower(compareMaj);
        int compMaj = thisMaj.compareTo(compareMaj);
        if (compMaj != 0) {
            return (compMaj < 0) ? -1 : 1;
        }
        // Finally, compare credits (if same, will return 0)
        int compCredit = creditCompleted - compareStudent.getCreditCompleted();
        if (compCredit == 0) {
            return compCredit;
        } else {
            return (compCredit < 0) ? -1 : 1;
        }
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
     Helper method that assigns major from string
     */
    private void assignMajor(String majStr) {
        switch (majStr) {
            case "ee":
                major = Major.EE;
                break;
            case "iti":
                major = Major.ITI;
                break;
            case "bait":
                major = Major.BAIT;
                break;
            case "cs":
                major = Major.CS;
                break;
            case "math":
                major = Major.MATH;
                break;
        }
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
        String newMajLowercase = stringLower(newMaj);
        switch (newMajLowercase) {
            case "ee":
                major = Major.EE;
                break;
            case "iti":
                major = Major.ITI;
                break;
            case "bait":
                major = Major.BAIT;
                break;
            case "cs":
                major = Major.CS;
                break;
            case "math":
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

    /**
     Converts input string to all lowercase, for non-case-sensitive majors
     * @param strIn String passed in
     * @return lowercase value of String
     */
    private String stringLower (String strIn) {
        char[] charsIn = strIn.toCharArray();
        for (int currInd = 0; currInd < charsIn.length; currInd++) {
            int currChar = charsIn[currInd];
            if (currChar < ASCIILOWERA) {
                // Value is capital, convert to lowercase
                charsIn[currInd] = (char) (charsIn[currInd] + ASCIICASEDIFF);
            }
        }
        return String.valueOf(charsIn);
    }

    /**
     Testbed main() to test the functionality of the Student class when creating
     Student objects. Method will test valid and invalid arguments.
     * @param args default String array of multiple arguments. Not used.
     */
    public static void main(String[] args) {
        // Test dates
        Student base = new Student("John Doe 9/2/2002 BAIT 10");
        Student nameLess = new Student("Ivy Doe 9/2/2002 BAIT 10"); // Test 1
        Student nameGreater = new Student("John Evans 9/2/2002 BAIT 10"); // Test 2
        Student same = new Student("John Doe 9/2/2002 BAIT 10"); // Test 3
        Student nameCase = new Student("jOhN DOe 9/2/2002 BAIT 10"); // Test 4
        Student majGreater = new Student("John Doe 9/2/2002 EE 10"); // Test 5
        Student majCase = new Student("John Doe 9/2/2002 bAIt 10"); // Test 6
        Student credMore = new Student("John Doe 9/2/2002 BAIT 11"); // Test 7

        System.out.println(nameLess.compareTo(base)); // Test 1, expect -1
        System.out.println(nameGreater.compareTo(base)); // Test 2, expect 1
        System.out.println(same.compareTo(base)); // Test 3, expect 0
        System.out.println(nameCase.compareTo(base)); // Test 4, expect 0
        System.out.println(majGreater.compareTo(base)); // Test 5, expect 1
        System.out.println(majCase.compareTo(base)); // Test 6, expect 0
        System.out.println(credMore.compareTo(base)); // Test 7, expect 1
    }
}
