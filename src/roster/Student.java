package roster;

public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;

    /**
     Constructor for a Student object from user's input
     @param studentInput as with tokens as string
     */
    public Student(String studentInput) {
        int tokenStart, tokenEnd; // Tracks beginning and end of each token
        tokenStart = 0;
        int tokenNum = 0; // Number of tokens retrieved

        // todo check if student valid (check date)

        String firstName = "", lastName = "", dateOfBirth = ""; // For Profile (convert DOB to Date)
        //John Doe 9/2/2022 EE 10 //todo remove example

        // Parse string, extracting tokens as encounter " " delimiter
        for (int ind = 0; ind < studentInput.length(); ind++) { // todo var name could be better mm
            if (studentInput.charAt(ind) == ' ') {
                tokenEnd = ind; // Space is end (non-inclusive) of token
                // Set appropriate token
                if (tokenNum == 0) {
                    firstName = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 1) {
                    lastName = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 2) {
                    dateOfBirth = studentInput.substring(tokenStart, tokenEnd);
                } else if (tokenNum == 3) {
                    // todo what how major
                } else {
                    creditCompleted = Integer.parseInt(studentInput.substring(tokenStart, tokenEnd));
                }

                tokenNum += 1; // Incr num of tokens retrieved
                tokenStart = tokenEnd + 1; // Reset start of token
            }
        }

        Date profDate = new Date(dateOfBirth);
        profile = new Profile(lastName, firstName, profDate);
    }

    @Override
    public String toString() {
        return profile.toString() + " " + major.toString() + " " + creditCompleted;
        // todo major toString sus do we use major.code or sm
    }

    @Override
    public int compareTo(Student compareStudent) {
        return 1; // todo
    }

    /**
     Overrides equals to check if two students are equal
     @param equalObject student to be compared to
     @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object equalObject) { // todo wack
        if (equalObject instanceof Student)
        {
            Student equalStudent = (Student) equalObject; // Cast into Student if can
            return true; // todo return true if equal check names DOB blahblah
        }
        return false; // Not of type student, invalid comparison
    }

    /**
     Method that changes a student's major
     @param newMaj major that Student changed to
     */
    public void changeMajor(Major newMaj) {
        // todo do we need a boolean return and isValid()?
        this.major = newMaj;
    }
}
