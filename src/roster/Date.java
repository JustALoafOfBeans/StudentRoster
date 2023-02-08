package roster;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     Constructor for Date object using today's date if none given
     Uses Calendar class to retrieve today's date
     */
    public Date () {
        //
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
}
