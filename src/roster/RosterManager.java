package roster;
import java.util.Scanner;

/**
 Management class for a roster object which holds student objects.
 This class is run by calling RosterManager.run() and first initiates a new
 roster object and session. The roster can be manipulated during the session
 but all actions are cleared after running the opcode "Q".
 @author Victoria Chen, Bridget Zhang
 */
public class RosterManager {
    public void run() {
        Scanner intake = new Scanner(System.in);
        Roster studentRoster = new Roster();
        String opCode = "init";
        System.out.println("Roster manager running...");

        while (!opCode.equals("Q")) {
            String command = intake.nextLine();
            // split white space
            String[] parameters = command.split("\\s+");

            // ignore white space
            if (parameters.length > 0 && parameters[0] != "") {
                opCode = parameters[0];
                studentRoster = takeAction(studentRoster, parameters);
            }
        }
    }

    /**
     Private method, switches action based on the processed opcode.
     * @param studentRoster Roster object containing student objects.
     * @param parameters Array of strings containing a breakdown of the
     *                   command to process.
     * @return An updated Roster object.
     */
    private Roster takeAction(Roster studentRoster, String[] parameters) {
        String opCode = parameters[0];

        // Course of action based on op code
        switch (opCode) {
            case "A": // add student
                studentRoster = addStudent(studentRoster, parameters);
                break;
            case "R": //remove student
                System.out.println("Remove student");
                /** use parameters to make student obj
                if (roster.contains(student)) {
                    roster.remove(student);
                    System.out.println(fname lname dob removed from the
                 roster.);
                } else {
                 System.out.println(fname lname dob is not in the roster.);
                } */
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
                System.out.println("List students in a school");
                break;
            case "C": // change a students major
                System.out.println("Change a students major");
                break;
            case "Q": // end the program
                System.out.println("Roster Manager terminated.");
                break;
            default:
                System.out.println(opCode + " is an invalid command!");
                break;
        }
        return studentRoster;
    }

    /**
     Private method which checks if a student is valid and adds them to the
     roster.
     * @param studentRoster Roster object containing student objects.
     * @param parameters Array of strings containing a breakdown of the
     *                   command to process.
     * @return An updated Roster object.
     */
    private Roster addStudent(Roster studentRoster, String[] parameters) {
        Date dob = new Date(parameters[3]);
        // check dob, major code, and credits completed
        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob + " not a valid calendar "
                    + "date!");
        } else if (!dob.checkIfSixteen()) {
            System.out.println("DOB invalid: " + dob
                    + " younger than 16 years old.");
        } else if (!validMajor(parameters[4])) {
            System.out.println("Major code invalid: " + parameters[4]);
        } else if (!validCredits(parameters[5])) {
            // print statements are in validCredits method
        } else {
            System.out.println("legal dob");
            String student =
                    parameters[1] + " " + parameters[2] + " "
                            + parameters[3] + " " + parameters[4] + " "
                            + parameters[5];
            Student toAdd = new Student(student);
            // check if student exists
            if(!studentRoster.contains(toAdd)) {
                System.out.println("Add student");
                /** studentRoster.add(toAdd);
                 System.out.println(parameters[1] + " " + parameters[2]
                 + " " + parameters[3] + " added to the " +
                 "roster."); */
            } else {
                System.out.println(parameters[1] + " " + parameters[2]
                        + " " + parameters[3]
                        + " is already in the roster.");
            }
        }
        return studentRoster;
    }

    /**
     Private method returns true if the pass major is a valid major.
     * @param major String object.
     * @return true if major is a valid major, false otherwise.
     */
    private boolean validMajor(String major) {
        boolean contains = false;
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
                continue;
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
