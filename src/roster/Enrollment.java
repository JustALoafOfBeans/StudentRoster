package roster;

public class Enrollment {
    /**
     * Array of students in enrollment
     */
    private EnrollStudent[] enrollStudents;
    /**
     * Number of students currently enrolled
     */
    private int size;
    /**
     * Start size for enrollment array
     */
    private static final int STARTSIZE = 0;

    /**
     Constructor for Enrollment class
     */
    public Enrollment() {
        size = STARTSIZE;
        enrollStudents = new EnrollStudent[size];
    }

    /**
     Adds a new student to the enrollStudents array
     * @param enrollStudent new student to add
     */
    public void add(EnrollStudent enrollStudent) {
        // Check if student in roster (if not, print error) // todo allowed to print here??
        // Check to see if student already enrolled
        int foundIndex = find(enrollStudent);
        if (foundIndex >= 0) {
            // Student found in enrollment, update credits but do not add
            enrollStudents[foundIndex].setCredits(enrollStudent.getCredits());
        } else {
            if (enrollStudents.length == size) { // Enrollment full, resize
                grow();
            }
            enrollStudents[size] = enrollStudent; // Add to end
            size += 1; // Increase count
        }
    }

    /**
     Method that increases capacity of enrollStudents array
     */
    private void grow () {
        // Increase array capacity by 4
        EnrollStudent[] enrollStudentsNew = new EnrollStudent[enrollStudents.length + 4];
        for (int ind = 0; ind < size; ind++) {
            enrollStudentsNew[ind] = enrollStudents[ind];
        }
        enrollStudents = enrollStudentsNew;
    }

    /**
     Removes a specified student from the enrollment list
     * @param enrollStudent target student to remove
     */
    public void remove(EnrollStudent enrollStudent) {
        int remIndex = find(enrollStudent); // Index to remove at
        if (remIndex < 0) { // Not in enrollment, nothing to remove
            return; // todo error?
        }
        size -= 1; // Decrease enrollment count
        // For every index at and beyond remIndex, move up
        for (int ind = remIndex; ind < enrollStudents.length; ind++) {
            if (ind == enrollStudents.length-1 || enrollStudents[ind + 1] == null) {
                // At end (of array or of non-null indices in array)
                enrollStudents[ind] = null;
                break;
            }
            enrollStudents[ind] = enrollStudents[ind+1];
        }
    }

    /**
     Helper method that finds target student
     * Used by add(), remove(), and find() methods
     * @param enrollStudent target student
     * @return index at which target student found, or -1 if not found
     */
    private int find(EnrollStudent enrollStudent) {
        Profile seekProf = enrollStudent.getProfile(); // Profile of enrollStudent sought
        for (int ind = 0; ind < size; ind++) {
            if (seekProf.compareTo(enrollStudents[ind].getProfile()) == 0) {
                // Found, return index
                return ind;
            }
        }
        return -1; // Not found
    }

    /**
     Method that checks if a target student is contained in the enrollment list
     * @param enrollStudent target student that is being checked for
     * @return true if found in array, false if not
     */
    public boolean contains(EnrollStudent enrollStudent) {
        int containsIndex = find(enrollStudent);
        // Returns "true" if in enrollment at some index >=0 containsIndex
        return (containsIndex >= 0);
    }

    /**
     Method that prints out enrollment in order of addition
     */
    public void print() {
        if (size == STARTSIZE) {
            System.out.println("Enrollment is empty!");
            return;
        }
        System.out.println("** Enrollment **");
        for (int ind = 0; ind < size; ind++) {
            System.out.println(enrollStudents[ind].toString());
        }
        System.out.println("* end of enrollment *");
    }

    /**
     Method that prints out enrollment as well as each student's tuition
     */
    public void printTuition(Roster rost) {
        // Get array of roster students
        Student[] rostArr = rost.getStudentArr();
        if (rostArr.length == STARTSIZE) {
            System.out.println("Student roster is empty!");
            return;
        } else if (size == STARTSIZE) {
            System.out.println("Enrollment is empty!");
            return;
        }
        Profile enrollProf;
        int profIndex;
        String tuitionStr;
        double tuitionDouble;
        System.out.println("** Tuition due **");
        // For each student in enrollArr, find profile in Roster and print details
        for (int eInd = 0; eInd < size; eInd++) {
            enrollProf = enrollStudents[eInd].getProfile();
            // Find student in roster that matches profile
            profIndex = findProfile(enrollProf, rostArr); // todo should not be -1 (not found in roster)

            // Print tuition
            tuitionStr = rostArr[profIndex].getProfile().toString() + " (";
            tuitionStr += rostArr[profIndex].returnType() + ") enrolled ";
            tuitionStr += enrollStudents[eInd].getCredits() + " credits: tuition due: $";
            tuitionDouble = rostArr[profIndex].tuitionDue(enrollStudents[eInd].getCredits());
            tuitionStr += String.format("%,.2f", tuitionDouble);

            System.out.println(tuitionStr);
        }
        System.out.println("* end of tuition due *");
    }

    /**
     Method to find the student in the roster
     * @param profile profile that should be found and matched
     * @return index in roster where student with profile is located, or NOT_FOUND = -1 if not found
     */
    private int findProfile (Profile profile, Student[] roster) {
        // For each Student in roster[], check if profile equal
        for (int rIndex = 0; rIndex < roster.length; rIndex++) {
            if (roster[rIndex] == null) {
                break;
            }
            if (roster[rIndex].getProfile().equals(profile)) {
                // Profile found
                return rIndex;
            }
        }
        // Returns -1 if reach end of roster without hit
        return -1;
    }

    /**
     Method returns the EnrollStudent object given a dummy student obj as
     long as the profile matches.
     * @param enrollStudent EnrollStudent object to find.
     * @return Full EnrollStudent object.
     */
    public EnrollStudent getStudent(EnrollStudent enrollStudent) {
        int NOTFOUND = -1;
        int foundIndex = find(enrollStudent);
        if (foundIndex == NOTFOUND) {
            return null;
        }
        return enrollStudents[foundIndex];
    }

    /**
     Method ends the semester and adds the enrolled credits to credits
     completed, printing all students with 120 or more credits.
     * @param studentRoster Roster object containing all students
     */
    public void findGraduates(Roster studentRoster) {
        int GRADCREDITS = 120;
        System.out.println("** list of students eligible for graduation **");
        for (int ind = 0; ind < size; ind++) {
            String studentDetails = enrollStudents[ind].toString();
            String profile[] = studentDetails.split(": ");

            Profile tempProfile = new Profile(profile[0]);
            Student tempStudent = new Resident(tempProfile);
            Student studentR = studentRoster.getStudent(tempStudent);
            EnrollStudent studentE = new EnrollStudent(profile[0] + " " + profile[2]);

            if (studentR.getCreditCompleted() + studentE.getCredits() >= GRADCREDITS) {
                int credits = studentR.getCreditCompleted() + studentE.getCredits();
                String details = studentR.toString();
                String[] studentName = details.split(": ");
                String[] studentStatus = studentName[1].split(" ");
                String strStudent = studentName[0] + ": " + credits + " ";
                strStudent += studentStatus[1] + " ";

                if (studentR.isResident()) {
                    strStudent += "(resident)";
                } else {
                    strStudent += "(non-resident) ";
                    if (studentR.returnType().equals("International Student")) {
                        strStudent += "(international)";
                    } else {
                        String needState = studentR.returnType();
                        String[] state = needState.split(" ");
                        strStudent += "(tri-state:" + state[1] + ")";
                    }
                }
                System.out.println(strStudent);
            }
        }
    }
}
