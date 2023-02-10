package roster;
import java.util.Calendar;

public class Date  {
    //implements Comparable<Date> ?
    private int year;
    private int month;
    private int day;

    /**
     Constructor for Date object without parameters using today's date
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
     Constructor for Date object using input parameters
     */
    public Date (String month, String day, String year) {
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(day);
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
     * If the day or month is a single digit, add padding.
     * @return String formatted as mm/dd/yyyy
     */
    @Override
    public String toString() {
        String monthAndDay = "";
        String padding = "0";

        if (this.month < 10) {
            monthAndDay += padding + this.month + "/";
        } else {
            monthAndDay += this.month + "/";
        }
        if (this.day < 10) {
            monthAndDay += padding + this.day;
        } else {
            monthAndDay += this.day;
        }
        return monthAndDay + "/" + this.year;
    }

    /**
     * -------------------------------------------------------------------
     * DELETE THIS, FOR TESTING PURPOSES ONLY
     * -------------------------------------------------------------------
     */
    public static void main(String[] args) {
        Date test = new Date("3", "2", "1996");
        System.out.println(test);
    }
}