package roster;

/**
 The main method to launch the whole Student Roster project.
 To run, first compile the whole project. After compilation, run this class
 and a new instance of the rostermanager will launch. Close the roster
 manager with the command "Q".
 @author Victoria Chen
 */
public class RunProject1 {
    /**
     Main method to run the whole roster project.
     * @param args String array, unused.
     */
    public static void main(String[] args) {
        new RosterManager().run();
    }
}
