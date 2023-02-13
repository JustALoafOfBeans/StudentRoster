package roster;

public class Roster {
    private Student[] roster;
    private int size;

    private static final int STARTSIZE = 0;

    /**
     Constructor for Roster class
     */
    public Roster() {
        size = STARTSIZE;
        roster = new Student[size];
    }

    /**
     Method to find the student in the roster
     * @param student student that is to be found
     * @return index in roster where student is located, or NOT_FOUND = -1 if not found
     */
    private int find (Student student) {
        // For each Student in roster[], use <Student>.equals() to compare
        for (int rIndex = 0; rIndex < size; rIndex++) {
            if (roster[rIndex].getProfile().equals(student.getProfile())) {
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
        for (int rIndex = 0; rIndex < size-1; rIndex++) {
            rosterNew[rIndex] = roster[rIndex];
        }
        roster = rosterNew;
    }

    /**
     Add student to the end of the array
     * @param student the student to be added to the roster
     * @return true if student valid and added successfully, false otherwise
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
     * @param student student to be removed from roster
     * @return true if removed successfully, false if otherwise
     */
    public boolean remove (Student student) {
        int rem = find(student); // Index to find student at
        if (rem < 0) { // Not found
            return false;
        }
        // Move everything after remIndex up (null for last)
        for (int shift = rem + 1; shift < size; shift++) {
            roster[shift - 1] = roster[shift];
            if (shift == roster.length-1 || roster[shift+1] == null) { // Set last to null
                roster[shift] = null;
            }
        }
        size -= 1;
        return true;
    }

    /**
     Checks if target student can be found in roster
     * @param student target student being sought
     * @return true if student in roster, false if not
     */
    public boolean contains (Student student) {
        int foundIndex = find(student);
        return foundIndex >= 0; // If foundIndex < 0, student not found in roster
    }

    /**
     Prints roster sorted by student profiles
     */
    public void print() {
        // Sort by profile
        sortProfile();
        // Print all elements of sorted roster
        for (int ind = 0; ind < size; ind++) {
            System.out.println(roster[ind].toString());
        }
    }

    /**
     Prints roster sorted by student majors (alpahbetical) and profile
     */
    public void printBySchoolMajor() {
        // Sort by standing
        sortStanding();
        // Print all elements of sorted roster
        for (int ind = 0; ind < size; ind++) {
            System.out.println(roster[ind].toString());
        }
    }

    /**
     Prints roster sorted by student standing (alphabetical) and profile
     */
    public void printByStanding() {
        // Sort by school and major
        sortSchoolMajor();
        // Print all elements of sorted roster
        for (int ind = 0; ind < size; ind++) {
            System.out.println(roster[ind].toString());
        }
    }

    /**
     Sorts roster by student Profile
     Called to sort by print()
     */
    public void sortProfile() {
        for (int i = 0; i < size; i++) { // todo change counter names (note size not roster.length)
            for (int j = i-1; j >= 0; j--) {
                // If Profile "less" than prev, swap
                if (roster[j].getProfile().compareTo(roster[j-1].getProfile()) < 0) {
                    swapRoster(j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Changes major of a student given profile
     * @param prof Profile of student whose major should be changed
     * @param strMaj new major as a string
     * @return false if student not found or major invalid, true if successful
     */
    public boolean changeMajor(Profile prof, String strMaj) {
        for (int ind = 0; ind < size; ind++) {
            if (roster[ind].getProfile().compareTo(prof) == 0) {
                if (roster[ind].changeMajor(strMaj)) {
                    // Successfully found student and changed their major
                    return true;
                } else {
                    // Student found but major could not be changed
                    return false;
                }
            }
        }
        return false; // Student not found
    }

    /**
     Sorts roster by student Major then by School (and if same, by Profile)
     Called to sort by printBySchoolMajor()
     */
    public void sortSchoolMajor () {
        int schoolComp, majorComp; // Value of school comparison
        for (int i = 0; i < size; i++) {
            for (int j = i-1; j >= 0; j--) {
                schoolComp = roster[j].getMajor().school.compareTo(roster[j-1].getMajor().school); // todo long
                if (schoolComp < 0) {
                    // School "less" alphabetically, swap
                    swapRoster(j, j-1);
                } else if (schoolComp == 0) {
                    // Schools same, check majors
                    majorComp = roster[j].getMajor().name().compareTo(roster[j-1].getMajor().name());
                    if (majorComp < 0) {
                        // Major "less" alphabetically, swap
                        swapRoster(j, j-1);
                    } else if (majorComp == 0) {
                        // Same major, compare profiles
                        if (roster[j].getProfile().compareTo(roster[j-1].getProfile()) < 0) {
                            swapRoster(j, j-1);
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

    /**
     Sorts roster by student Standing then Profile
     Called to sort by printByStanding()
     */
    public void sortStanding () {
        int standingComp;
        for (int i = 0; i < size; i++) {
            for (int j = i-1; j >= 0; j--) {
                standingComp = roster[j].getStanding().name().compareTo(roster[j-1].getStanding().name());
                if (standingComp < 0) {
                    // If standing of previous greater alphabetically, swap
                    swapRoster(j, j-1);
                } else if (standingComp == 0) {
                    // If standing of current SAME, check profiles
                    if (roster[j].getProfile().compareTo(roster[j-1].getProfile()) < 0) {
                        // If previous profile greater, swap
                        swapRoster(j, j-1);
                    } else {
                        break;
                    }
                } else {
                    // Move to next value of j
                    break;
                }
            }
        }
    }

    /**
     Swaps elements at indices "x" and "y" in roster
     * @param indX index of first element in swap
     * @param indY index of other element in swap
     */
    private void swapRoster(int indX, int indY) {
        Student swapTemp = roster[indX];
        roster[indX] = roster[indY];
        roster[indY] = swapTemp;
    }
}
