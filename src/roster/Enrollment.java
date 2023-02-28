package roster;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    private static final int STARTSIZE = 0;

    public Enrollment() {
        size = STARTSIZE;
        enrollStudents = new EnrollStudent[size];
    }

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

    private void grow () {
        // Increase array capacity by 4
        EnrollStudent[] enrollStudentsNew = new EnrollStudent[enrollStudents.length + 4];
        for (int ind = 0; ind < size; ind++) {
            enrollStudentsNew[ind] = enrollStudents[ind];
        }
        enrollStudents = enrollStudentsNew;
    }

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

    // Helper method for finding student index, used by add(), remove(), and find()
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

    public boolean contains(EnrollStudent enrollStudent) {
        int containsIndex = find(enrollStudent);
        // Returns "true" if in enrollment at some index >=0 containsIndex
        return (containsIndex >= 0);
    }

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

    public void printTuition(Roster rost) {
        if (size == STARTSIZE) {
            System.out.println("Enrollment is empty!");
            return;
        }
        System.out.println("** Tuition due **");
        // Get array of roster students
        Student[] rostArr = rost.getStudentArr();
        Profile enrollProf;
        int profIndex;
        String tuitionStr;
        double tuitionDouble;
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
}
