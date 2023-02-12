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
            opCode = parameters[0];

            studentRoster = takeAction(studentRoster, parameters);
        }
    }

    /**
     Private method, switches action based on the processed opcode.
     * @param studentRoster Roster object containing student objects.
     * @param parameters Array of strings containing a breakdown of the
     *                   command to process.
     * @return An updated Roster object
     */
    private Roster takeAction(Roster studentRoster, String[] parameters) {
        String opCode = parameters[0];

        // Course of action based on op code
        switch (opCode) {
            case "A":
                System.out.println("Add student");
                break;
            case "R":
                System.out.println("Remove student");
                break;
            case "P":
                System.out.println("Display roster by last name");
                break;
            case "PS":
                System.out.println("Display roster by standing");
                break;
            case "PC":
                System.out.println("Display roster by school/major");
                break;
            case "L":
                System.out.println("List students in a school");
                break;
            case "C":
                System.out.println("Change a students major");
                break;
            case "Q":
                System.out.println("Roster Manager terminated.");
                break;
            default:
                System.out.println(opCode + " is an invalid command!");
        }
        return studentRoster;
    }
}
