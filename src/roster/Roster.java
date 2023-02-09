package roster;

public class Roster {
    private Student[] roster;
    private int size;

    /**
     Method to find the student in the roster
     @param student student that is to be found
     @return index in roster where student is located, or NOT_FOUND = -1 if not found
     */
    private int find (Student student) {
        // For each Student in roster[], use <Student>.equals() to compare
        for (int rIndex = 0; rIndex < size; rIndex++) {
            if (roster[rIndex].equals(student)) {
                // Student found
                return rIndex;
            }
        }
        // Returns -1 if reach end of roster without hit
        return -1;
    }

    /**
     Increases the capacity of the array by 4
     */
    private void grow () {
        // Increase array capacity by 4
        Student[] rosterNew = new Student[size + 4];
        for (int rIndex = 0; rIndex < size; rIndex++) {
            rosterNew[rIndex] = roster[rIndex];
        }
        roster = rosterNew;
    }

    /**
     Add student to the end of the array
     @param student the student to be added to the roster
     @return true if student valid and added successfully, false otherwise
     */
    public boolean add (Student student) {
        return false; // TEMP REMOVE LATER
    }

    /**
     Removes student from array and maintains order
     @param student student to be removed from roster
     @return true if removed successfully, false if otherwise
     */
    public boolean remove (Student student) {
        return false; // TEMP REMOVE LATER
    }

    public boolean contains (Student student) {
        return false; // TEMP REMOVE LATER
    }

    public void print() {
        //
    }

    public void printBySchoolMajor() {
        //
    }

    public void printByStanding() {
        //
    }
}
