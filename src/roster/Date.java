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
     Constructor for Date object using values from input String
     @param date String describing input date in mm/dd/yyyy format
    */
    public Date (String date) {
        String[] dateBreakdwn = date.split("/", 3);
        this.month = Integer.parseInt(dateBreakdwn[0]);
        this.day = Integer.parseInt(dateBreakdwn[1]);
        this.year = Integer.parseInt(dateBreakdwn[2]);
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
     * Returns a string representation of the given date.
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
     * Compares this Date object to the specified Object
     * @param obj String describing input date in mm/dd/yyyy format
     * @return true if the objects refer to the same date, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        // first check if object type is correct
        if(obj instanceof Date) {
            Date date = (Date) obj; //casting
            boolean matchMonth = date.month == this.month;
            boolean matchDay = date.day == this.day;
            boolean matchYear = date.year == this.year;
            return (matchMonth && matchDay && matchYear);
        }
        return false;
    }

    /**
     * -------------------------------------------------------------------
     * DELETE THIS, FOR TESTING PURPOSES ONLY
     * -------------------------------------------------------------------
     */
    public static void main(String[] args) {
        Date test = new Date("16/5/2023");
        Date test2 = new Date("16/000005/2023");
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test.equals("test2"));
    }
}