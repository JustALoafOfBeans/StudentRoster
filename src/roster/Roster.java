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
        // todo check if student is valid
        // Check if student already in roster
        int lastFullIndex = size - 1; // Assume full to start
        for (int rostInd = 0; rostInd < size; rostInd++) {
            if (roster[rostInd] == null) {
                lastFullIndex = rostInd - 1; // Track last full index
                break;
            }
            if (roster[rostInd].equals(student)) {
                return false; // todo do we need some message to say already there?
            }
        }
        // Is valid and not yet in roster, add accordingly
        if (lastFullIndex != (size - 1)) {
            // Resize of last index full (roster filled)
            grow();
        }
        roster[lastFullIndex + 1] = student;
        return true;
    }

    /**
     Removes student from array and maintains order
     @param student student to be removed from roster
     @return true if removed successfully, false if otherwise
     */
    public boolean remove (Student student) {
        // Seek target student in roster
        boolean found = false;
        int rem = 0; // remove index
        for (rem = 0; rem < size; rem++) {
            if (roster[rem].equals(student)) {
                found = true;
                break;
            }
        }
        // Move everything after remIndex up (null for last)
        if (found) {
            for (int shift = rem + 1; shift < size; shift++) {
                if (roster[shift] == null) { // If null, roster complete
                    roster[shift - 1] = null;
                    break;
                }
                if (shift == size - 1) { // Set last to null
                    roster[shift] = null;
                }
                roster[shift - 1] = roster[shift];
            }
        }

        // If not found, return false
        return found;
    }

    public boolean contains (Student student) {
        return false; // TEMP REMOVE LATER
    }

    public void print() {
        // todo sorted last, first, DOB
    }

    public void printBySchoolMajor() {
        // todo sorted by standing (alphabetical)
    }

    public void printByStanding() {
        // todo sorted by school, major
    }
}
