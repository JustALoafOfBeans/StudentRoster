package roster;
import java.util.Calendar;

public class Date  {
    //implements Comparable<Date> ?
    private int year;
    private int month;
    private int day;

    /**
     Constructor for Date object using today's date if none given
     Uses Calendar class to retrieve today's date
     */
    public Date () {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        // Months are indexed starting at 0, add 1 to adjust
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
    }

    /**
     Constructor for Date object using values from input String
     @param date String describing input date in mm/dd/yyyy format
    */
    public Date (String date) {
        //
    }

    /**
     Method to check if the date is a valid calendar date
     Checks if date is valid for given month, and checks for leap years
     @return true if the date is valid, false if otherwise
     */
    public boolean isValid() {

        return false; // TEMP REMOVE LATER
    }

    /**
     * Overrides the default toString method.
     * @return String formatted as mm/dd/yyyy
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * -------------------------------------------------------------------
     * DELETE THIS, FOR TESTING PURPOSES ONLY
     * -------------------------------------------------------------------
     */
    public static void main(String[] args) {
        System.out.println("Test");
        Date test = new Date();
        System.out.println(test);
    }
}