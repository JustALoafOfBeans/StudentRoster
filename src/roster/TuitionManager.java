package roster;
import java.util.Scanner;
import java.io.File;

/**
 Management class for a roster and enroll objects which holds student objects.
 This class is run by calling TuitionManager.run() and first initiates a new
 roster object and session. The roster can be manipulated during the session
 but all actions are cleared after running the opcode "Q".
 @author Victoria Chen
 */
public class TuitionManager {
    /**
     * Global instance variable representing the roster object
     */
    Roster studentRoster = new Roster();
    /**
     * Global instance variable representing the enrollment object
     */
    Enrollment enrolledStudents = new Enrollment();
    private static int SINGLE = 1;

    /**
     This method launches the Tuition Manager.
     */
    public void run() {
        Scanner intake = new Scanner(System.in);
        String opCode = "init";
        System.out.println("Tuition manager running...");

        while (!opCode.equals("Q")) {
            String command = intake.nextLine();
            // split on white space
            String[] parameters = command.split("\\s+");
            // ignore empty enters
            if (parameters.length > 0 && parameters[0] != "") {
                opCode = parameters[0];
                takeAction(parameters);
            }
        }
    }

    /**
     Private method, switches action based on the processed opcode.
     * @param parameters Array of strings containing a breakdown of the
     *                   command to process.
     * @return An updated Roster object.
     */
    // MUST BE LESS THAN 50 LINES
    private void takeAction(String[] parameters) {
        String opCode = parameters[0];
        switch (opCode) {
            case "AR": // add resident
                addResident(parameters, true);
                break;
            case "AN": // add nonresident
                addNonResident(parameters, true);
                break;
            case "AT": // add tristate student
                addTristate(parameters, true);
                break;
            case "AI": // add international student
                addInternational(parameters, true);
                break;
            case "R": //remove student
                removeStudent(parameters);
                break;
            case "P": // print roster by last name
                studentRoster.print();
                break;
            case "PS": // print roster by standing
                studentRoster.printByStanding();
                break;
            case "PC": // print roster by school major
                studentRoster.printBySchoolMajor();
                break;
            case "L": // list all students in a school
                studentRoster.print(parameters[1]);
                break;
            case "C": // change a students major
                changeMajor(parameters);
                break;
            case "LS":
                addFromFile(parameters);
                break;
            case "E": //enroll a student
                enrollStudent(parameters);
                break;
            case "D": // drop a student from the roll list
                System.out.println("Drop student");
                break;
            case "S": //award a scholarship
                System.out.println("Award a scholarship");
                break;
            case "PE": // print roll list
                System.out.println("Print roll list");
                break;
            case "PT": // print roll list with tuition
                System.out.println("Print roll list with tuition");
                break;
            case "SE": // end semester
                System.out.println("End semester.");
                break;
            case "Q": // end the program
                System.out.println("Tuition Manager terminated.");
                break;
            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }
    }

    private void enrollStudent(String[] parameters) {
        if (validEnroll(parameters)) {
            System.out.println("valid enroll");
        }
    }

    /**
     Method verifies if a Student can be enrolled to the enroll list.
     * @param parameters String array detailing the students details.
     * @return true if the student can be added to enroll list, false otherwise.
     */
    private boolean validEnroll(String[] parameters) {
        int NUMPARAMS = 5;
        if (parameters.length != NUMPARAMS) {
            System.out.println("Missing data in line command.");
            return false;
        }
        char[] digits = parameters[parameters.length - SINGLE].toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (!Character.isDigit(digits[i])) {
                System.out.println("Credits enrolled is not an integer.");
                return false;
            }
        }
        // if invalid credits
        // if not in roster
        return true;
    }

    /**
     Private method adds a International type student to the Roster object.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     */
    private void addInternational(String[] parameters, boolean prints) {
        int NUMPARAMS = 6;
        int WITHSTATUS = 7;
        boolean studyAbroad = false;
        int adjust = 0;

        if (parameters.length < NUMPARAMS && prints) {
            System.out.println("Missing data in command line.");
            return;
        }
        if (parameters.length == WITHSTATUS) {
            studyAbroad = Boolean.parseBoolean(parameters[NUMPARAMS]);
            adjust = 1;
        }
        String[] noStatus = {parameters[0],parameters[1],parameters[2],
                parameters[3],parameters[4], parameters[5]};
        if (validStudent(noStatus)) {
            String[] noOp = {parameters[1],parameters[2],parameters[3],parameters[4], parameters[5]};
            String studentparam = String.join(" ",noOp);
            International toAdd = new International(studentparam,studyAbroad);
            if(!studentRoster.contains(toAdd)) {
                studentRoster.add(toAdd);
                if (prints) {
                    System.out.println(parameters[1] + " " + parameters[2]
                            + " " + parameters[3] + " added to the " + "roster.");
                }
            } else if (prints){
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3] + " is already in the roster.");
            }
        }
    }

    /**
     Private method adds a TriState type student to the Roster object.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     */
    private void addTristate(String[] parameters, boolean prints) {
        int NUMPARAMS = 7;
        int NOSTATE = 6;

        if (parameters.length == NOSTATE && prints) {
            System.out.println("Missing the state code.");
            return;
        } else if (parameters.length != NUMPARAMS && prints) {
            System.out.println("Missing data in command line.");
            return;
        }
        String state = parameters[6];
        String[] noState = {parameters[0],parameters[1],parameters[2],
                parameters[3],parameters[4], parameters[5]};
        if (validStudent(noState) && validState(state)) {
            String[] noOp = {parameters[1],parameters[2],parameters[3],parameters[4], parameters[5]};
            String studentparam = String.join(" ",noOp);
            TriState toAdd = new TriState(studentparam,state);
            if(!studentRoster.contains(toAdd)) {
                studentRoster.add(toAdd);
                if (prints) {
                    System.out.println(parameters[1] + " " + parameters[2]
                            + " " + parameters[3] + " added to the " + "roster.");
                }
            } else if (prints) {
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3] + " is already in the roster.");
            }
        }
    }

    /**
     Helper method to check if a state code is a valid tristate code.
     * @param state String representing state.
     * @return true if state is New York (NY) or Connecticut (CT).
     */
    private boolean validState(String state) {
        if (state.toUpperCase().equals("NY") || state.toUpperCase().equals(
                "CT")) {
            return true;
        }
        System.out.println(state + ": Invalid state code.");
        return false;
    }

    /**
     Private method adds a NonResident type student to the Roster object.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     */
    private void addNonResident(String[] parameters, boolean prints) {
        int NUMPARAMS = 6;
        if (parameters.length != NUMPARAMS && prints) {
            System.out.println("Missing data in command line.");
            return;
        }
        if (validStudent(parameters)) {
            String[] noOp = {parameters[1],parameters[2],parameters[3],parameters[4], parameters[5]};
            String studentparam = String.join(" ",noOp);
            NonResident toAdd = new NonResident(studentparam);
            if(!studentRoster.contains(toAdd)) {
                studentRoster.add(toAdd);
                if (prints) {
                    System.out.println(parameters[1] + " " + parameters[2]
                            + " " + parameters[3] + " added to the " + "roster.");
                }
            } else if (prints){
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3] + " is already in the roster.");
            }
        }
    }

    /**
     Private method adds a Resident type student to the Roster object.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     */
    private void addResident(String[] parameters, boolean prints) {
        int NUMPARAMS = 6;
        if (parameters.length != NUMPARAMS && prints) {
            System.out.println("Missing data in command line.");
            return;
        }
        if (validStudent(parameters)) {
            String[] noOp = {parameters[1],parameters[2],parameters[3],parameters[4], parameters[5]};
            String studentparam = String.join(" ",noOp);
            Resident toAdd = new Resident(studentparam);
            if(!studentRoster.contains(toAdd)) {
                studentRoster.add(toAdd);
                if (prints){
                    System.out.println(parameters[1] + " " + parameters[2]
                            + " " + parameters[3] + " added to the " + "roster.");
                }
            } else if (prints) {
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3] + " is already in the roster.");
            }
        }
    }

    /**
     Private method which reads input from studentList.txt and adds the
     students to the given roster argument.
     * @return An updated Roster object.
     */
    private void addFromFile(String[] params) {
        try {
            File studentList = getFile(params);
            Scanner intake = new Scanner(studentList);
            String opCode = "init";
            while(intake.hasNextLine()) {
                String command = intake.nextLine();
                String[] parameters = command.split(",");
                if (parameters.length > 0 && parameters[0] != "") {
                    opCode = parameters[0];
                    switch (opCode) {
                        case "R":
                            addResident(parameters, false);
                            break;
                        case "I":
                            addInternational(parameters, false);
                            break;
                        case "T":
                            addTristate(parameters, false);
                            break;
                        case "N":
                            addNonResident(parameters, false);
                            break;
                    }
                }
            }
            System.out.println("Students loaded to the roster.");
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        return;
    }

    /**
     Method returns a valid file given a file name with no path.
     * @param parameters String array containing the op code and file name.
     * @return Valid file with path for the given file name.
     */
    private File getFile(String[] parameters) {
        File studentList = new File("./src/roster/studentList.txt");
        if (parameters.length != SINGLE) {
            File otherList = new File("./src/roster/" + parameters[1]);
            File temp = new File(parameters[1]);
            File list = new File(temp.getAbsolutePath());
            if (list.isFile()) {
                return list;
            } else if (otherList.isFile()) {
                return otherList;
            }
        }
        return studentList;
    }

    /**
     Private method which, give the parameters, changes the major of the
     specified student.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     * @return An updated Roster object.
     */
    private void changeMajor(String[] parameters) {
        if (!validStudent(parameters) || !validMajor(parameters[4])) {
            return;
        }
        // create a profile and then student from that profile
        String student = parameters[1] + " " + parameters[2] + " "
                        + parameters[3];
        Profile toChange = new Profile(student);
        Resident stuObj = new Resident(toChange);
        if (studentRoster.contains(stuObj)) {
            studentRoster.changeMajor(toChange, parameters[4]);
            System.out.println(student + " major changed to " + parameters[4]);
        } else {
            System.out.println(student + " is not in the roster.");
        }
        return;
    }

    /**
     Private method which, given the parameters, makes a student obj and
     removes the student from the roster argument.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     * @return An updated Roster object.
     */
    private void removeStudent(String[] parameters) {
        // create student using parameters with dummy major and credits
        String student =
                parameters[1] + " " + parameters[2] + " "
                        + parameters[3] + " FAKE 50";
        Student toRemove = new Resident(student);
        if (studentRoster.contains(toRemove)) {
            studentRoster.remove(toRemove);
            System.out.println(parameters[1] + " " + parameters[2] + " "
                            + parameters[3] + " removed from the roster.");
        } else {
            System.out.println(parameters[1] + " " + parameters[2] + " "
                    + parameters[3] + " is not in the roster.");
        }
        return;
    }

    /**
     Private method returns whether a string array can be turned into a valid
     student object or not.
     * @param parameters String array in the format [fname, lname, dob,
     *                   major, credits].
     * @return true if student can be a valid Student object, false otherwise.
     */
    private boolean validStudent(String[] parameters) {
        Date dob = new Date(parameters[3]);
        int MJRIND = 4; // index for location of the major code
        int CRDIND = 5; // index for location of the credits
        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob + " not a valid calendar "
                    + "date!");
            return false;
        } else if (!dob.checkIfSixteen()) {
            System.out.println("DOB invalid: " + dob + " younger than 16 " +
                    "years old.");
            return false;
        } else if (parameters.length > MJRIND && !validMajor(parameters[4])) {
            System.out.println("Major code invalid: " + parameters[4]);
            return false;
        } else if (parameters.length > CRDIND && !validCredits(parameters[5])) {
            // print statements are in validCredits method
            return false;
        }
        return true;
    }

    /**
     Private method returns true if the pass major is a valid major.
     * @param major String object.
     * @return true if major is a valid major, false otherwise.
     */
    private boolean validMajor(String major) {
        boolean contains = false;
        major = major.toUpperCase();
        if (major.equals("CS")) {
            contains = true;
        } else if (major.equals("MATH")) {
            contains = true;
        } else if (major.equals("EE")) {
            contains = true;
        } else if (major.equals("ITI")) {
            contains = true;
        } else if (major.equals("BAIT")) {
            contains = true;
        }
        return contains;
    }

    /**
     Private method which returns if a string is a valid value for credits.
     * @param credits String obj.
     * @return true if credits can be a positive integer, false if otherwise.
     */
    private boolean validCredits(String credits) {
        char[] digits = credits.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (i == 0 && digits[0] == '-') {
                continue; // exception made for negative values
            } else if (!Character.isDigit(digits[i])) {
                System.out.println("Credits completed invalid: " +
                        "not an integer!");
                return false;
            }
        }
        if(Integer.parseInt(credits) < 0) {
            System.out.println("Credits completed invalid: " +
                    "cannot be negative!");
            return false;
        }
        return true;
    }
}
