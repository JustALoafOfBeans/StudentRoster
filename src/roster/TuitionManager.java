package roster;
import java.util.Arrays;
import java.util.ResourceBundle;
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
                addResident(parameters);
                break; /**
            case "AN": // add nonresident
                if (validStudent(parameters)) {
                    studentRoster = addStudent(studentRoster, parameters);
                }
                break;
            case "AT": // add tristate student
                if (validStudent(parameters)) {
                    studentRoster = addStudent(studentRoster, parameters);
                }
                break;
            case "AI": // add international student
                if (validStudent(parameters)) {
                    studentRoster = addStudent(studentRoster, parameters);
                }
                break;
            case "R": //remove student
                if (validStudent(parameters)) {
                    studentRoster = removeStudent(studentRoster, parameters);
                }
                break; */
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
                if (validStudent(parameters)) {
                    changeMajor(parameters);
                }
                break;
            case "LS":
                addFromFile();
                break;
            case "E": //enroll a student
                System.out.println("Enroll student");
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

    /**
     Private method adds a Resident type student to the Roster object.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     */
    private void addResident(String[] parameters) {
        int NUMPARAMS = 6;
        if (parameters.length != NUMPARAMS) {
            System.out.println("Missing data in command line.");
            return;
        }
        if (validStudent(parameters)) {
            String[] noOp = Arrays.copyOfRange(parameters,1,parameters.length);
            String studentparam = String.join(" ",noOp);
            Resident toAdd = new Resident(studentparam);
            if(!studentRoster.contains(toAdd)) {
                studentRoster.add(toAdd);
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3] + " added to the " + "roster.");
            } else {
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
    private void addFromFile() {
        try {
            File studentList = new File("./src/roster/studentList.txt");
            Scanner intake = new Scanner(studentList);
            String opCode = "init";

            while(intake.hasNextLine()) {
                String command = intake.nextLine();
                System.out.println(command);
                String[] parameters = command.split(",");
                if (parameters.length > 0 && parameters[0] != "") {
                    opCode = parameters[0];
                    switch (opCode) {
                        case "R":
                            System.out.println("R");
                            break;
                        case "I":
                            System.out.println("I");
                            break;
                        case "T":
                            System.out.println("T");
                            break;
                        case "N":
                            System.out.println("N");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        return;
    }

    /**
     Private method which, give the parameters, changes the major of the
     specified student.
     * @param parameters Array of strings containing a breakdown of the
     *                   student to process.
     * @return An updated Roster object.
     */
    private void changeMajor(String[] parameters) {
        if (!validMajor(parameters[4])) {
            return;
        }
        // create a profile and then student from that profile
        String student = parameters[1] + " " + parameters[2] + " "
                        + parameters[3];
        Profile toChange = new Profile(student);
        String[] noOp = Arrays.copyOfRange(parameters,1,parameters.length);
        String studentparam = String.join(" ",noOp);
        //Student stuObj = new Student(toChange);
        Resident stuObj = new Resident(studentparam);
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
        //Student toRemove = new Student(student);
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
