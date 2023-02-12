package roster;
import java.util.Scanner;

public class RosterManager {
    public void run() {
        Scanner intake = new Scanner(System.in);
        // Roster studentRoster = new Roster();
        String opCode = "init";
        System.out.println("Roster manager running...");

        while (!opCode.equals("Q")) {
            String command = intake.nextLine();
            System.out.println(command);
            String[] parameters = command.split(" ");
            opCode = parameters[0];
            System.out.println("Opcode is " + opCode);

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
        }

    }
}
