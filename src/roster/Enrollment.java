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
        for (int ind = 0; ind < enrollStudents.length; ind++) {
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
        for (int ind = 0; ind < enrollStudents.length; ind++) {
            System.out.println(enrollStudents[ind].toString());
        }
    }
}
