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
        Student[] rosterNew = new Student[roster.length + 4];
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
        if (!student.isValid()) { // Student not valid, can not be added
            return false;
        }
        // Check if student already in roster
        int lastFullIndex = roster.length - 1; // Assume full to start
        for (int rostInd = 0; rostInd < roster.length; rostInd++) {
            if (roster[rostInd] == null) {
                lastFullIndex = rostInd - 1; // Track last full index
                break;
            }
            if (roster[rostInd].equals(student)) {
                return false; // todo do we need some message to say already there?
            }
        }
        // Is valid and not yet in roster, add accordingly
        if (lastFullIndex == (roster.length - 1)) {
            // Resize of last index full (roster filled)
            grow();
        }
        roster[lastFullIndex + 1] = student;
        size += 1;
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
        for (rem = 0; rem < roster.length; rem++) {
            if (roster[rem].equals(student)) {
                found = true;
                break;
            }
        }
        // Move everything after remIndex up (null for last)
        if (found) {
            for (int shift = rem + 1; shift < roster.length; shift++) {
                // todo can optimize knowing "size" holds size not capacity
                if (roster[shift] == null) { // If null, roster complete
                    roster[shift - 1] = null;
                    break;
                }
                if (shift == roster.length - 1) { // Set last to null
                    roster[shift] = null;
                }
                roster[shift - 1] = roster[shift];
            }
            size -= 1;
        }
        // If not found, return false
        return found;
    }

    public boolean contains (Student student) {
        for (int ind = 0; ind < size; ind++) { // todo please change counter name
            if (roster[ind].compareTo(student) == 0) {
                return true;
            }
        }
        return false; // Student not found in roster
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

    //
    public void sortProfile() {
        Student swapTemp;
        for (int i = 0; i < size; i++) { // todo change counter names (note size not roster.length)
            for (int j = i-1; j >= 0; j--) {
                // If Profile "less" than prev, swap
                if (roster[j].getProfile().compareTo(roster[j-1].getProfile()) < 0) {
                    swapTemp = roster[j];
                    roster[j] = roster[j-1];
                    roster[j-1] = swapTemp;
                } else {
                    break;
                }
            }
        }
    }

    // sorts by school (alphabetical) then major (alphabetical), called by printBySchoolMajor()
    public void sortSchoolMajor () {
        Student swapTemp;
        int schoolComp, majorComp; // Value of school comparison
        for (int i = 0; i < size; i++) {
            for (int j = i-1; j >= 0; j--) {
                schoolComp = roster[j].getMajor().school.compareTo(roster[j-1].getMajor().school); // todo long
                if (schoolComp < 0) {
                    // School "less" alphabetically, swap
                    swapTemp = roster[j];
                    roster[j] = roster[j-1];
                    roster[j-1] = swapTemp;
                } else if (schoolComp == 0) {
                    // Schools same, check majors
                    majorComp = roster[j].getMajor().name().compareTo(roster[j-1].getMajor().name());
                    if (majorComp < 0) {
                        // Major "less" alphabetically, swap
                        swapTemp = roster[j];
                        roster[j] = roster[j-1];
                        roster[j-1] = swapTemp; // todo extract repeat code
                    } else if (majorComp == 0) {
                        // Same major, compare profiles
                        if (roster[j].getProfile().compareTo(roster[j-1].getProfile()) < 0) {
                            swapTemp = roster[j];
                            roster[j] = roster[j-1];
                            roster[j-1] = swapTemp;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    // School "greater" alphabetically
                    break;
                }
            }
        }
    }

    // Sorts by standing (alphabetical) then profile
    public void sortStanding () {
    }
}
