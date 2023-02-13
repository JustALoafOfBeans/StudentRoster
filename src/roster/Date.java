package roster;
import java.util.Calendar;

/**
 The Date class is an abstract class which provides methods to create Date
 objects from the current date or specified date and for manipulating the
 Date fields. Dates are only accepted in the mm/dd/yyyy format.
 @author Victoria Chen
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     Constructor for Date object without parameters using today's date.
     Uses Calendar class to retrieve today's date.
     */
    public Date () {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        // Months are indexed starting at 0, add 1 to adjust
        month = calendar.get(Calendar.MONTH) + DateBreakpoints.MONTHPADDING.num;
        day = calendar.get(Calendar.DATE);
    }

    /**
     Constructor for Date object using values from input String.
     @param date String describing input date in mm/dd/yyyy format.
    */
    public Date (String date) {
        // do we have to deal with incorrect formatted dates?
        String[] dateBreakdwn = date.split("/");
        this.month = Integer.parseInt(dateBreakdwn[0]);
        this.day = Integer.parseInt(dateBreakdwn[1]);
        this.year = Integer.parseInt(dateBreakdwn[2]);
    }

    /**
     Method to check if the date is a valid calendar date.
     Checks if date is valid for given month, and checks for leap years.
     @return true if the date is valid, false if otherwise.
     */
    public boolean isValid() {
        if (this.outOfBounds()) { // date is out of bounds
            return false;
        }

        if (this.month ==
                (Calendar.FEBRUARY + DateBreakpoints.MONTHPADDING.num)) {
            //leap year process
            if (this.isLeapYear()) {
                if ( this.day > 29 ) {
                    return false;
                }
            } else if ( this.day > 28 ) {
                return false;
            }
        } else if (this.shorterMonth() &&
                this.day > DateBreakpoints.SHORTERMONTH.num) {
            // April, June, September, and November have max 30 days
            return false;
        }
        // Any other month has 31 days
        return true;
    }

    /**
     Private method, checks if the year of this date is a leap year.
     * @return true if this year is a leap year, false is otherwise.
     */
    private boolean isLeapYear() {
        if (this.year % DateBreakpoints.QUADRENNIAL.num != 0) {
            return false;
        } else if (this.year % DateBreakpoints.CENTENNIAL.num != 0) {
            return true;
        } else if (this.year % DateBreakpoints.QUATERCENTENNIAL.num == 0) {
            return true;
        }
        return false;
    }

    /**
     Private method, checks if this month is a month with 30 days.
     Either April, June, September, or November.
     * @return true if this month is a month with 30 days.
     */
    private boolean shorterMonth() {
        if (this.month == (Calendar.APRIL + DateBreakpoints.MONTHPADDING.num)) {
            return true;
        } else if (this.month == (Calendar.JUNE
                + DateBreakpoints.MONTHPADDING.num)) {
            return true;
        } else if (this.month == (Calendar.SEPTEMBER
                + DateBreakpoints.MONTHPADDING.num)) {
            return true;
        } else if (this.month == (Calendar.NOVEMBER
                + DateBreakpoints.MONTHPADDING.num)) {
            return true;
        }
        return false;
    }

    /**
     Private method, checks if the numerical values of this date are possible.
     Days must be between 1 and 31; Months must be between 1 and 12; Years
     must be between 1 and the current year.
     * @return true if this date is out of bounds, false otherwise.
     */
    private boolean outOfBounds() {
        Calendar calendar = Calendar.getInstance();
        int lowerBound = 1;

        // check if date is in the future or above max possible values
        boolean aboveDay = this.day > DateBreakpoints.MAXDAYS.num;
        boolean aboveMonth = this.month > DateBreakpoints.MAXMONTHS.num;
        boolean futureYear = this.year > calendar.get(Calendar.YEAR);
        if ( aboveDay || aboveMonth || futureYear ) {
            return true;
        }

        // check if date is lower than possible (lower bound)
        boolean negDay = this.day < lowerBound;
        boolean negMonth = this.month < lowerBound;
        boolean negYear = this.year < lowerBound;
        if ( negDay || negMonth || negYear ) {
            return true;
        }

        return false;
    }

    /**
     Method to check if this date occurred 16 or more years ago.
     @return true if this date is 16 or more years ago, false otherwise.
     */
    public boolean checkIfSixteen() {
        int validAge = 16;
        Date today = new Date(); //date of today
        today.year = today.year - validAge;

        // if this date is exactly 16 years ago, still return true
        if (this.compareTo(today) <= 0) {
            return true;
        }
        return false;
    }

    /**
     Returns a string representation of the given date.
     @return String formatted as mm/dd/yyyy.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     Compares this Date object to the specified Object.
     @param obj String describing input date in mm/dd/yyyy format.
     @return true if the objects refer to the same date, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
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
     Compares the month, day, and year represented by the two Date objects.
     @param date the Date object to be compared.
     @return 0 if the dates are equal; a value less than 0 if the time
     of this Date is before the Date represented by the argument; and a
     value greater than 0 if the time of this Date is after the Date
     represented by the argument.
     */
    @Override
    public int compareTo(Date date) {
        int SAME = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (this.equals(date)) {
            return SAME; // compared dates are the same
        }
        if(this.year < date.year) { // compare years
            return BEFORE;
        } else if (this.year > date.year) {
            return AFTER;
        }
        if(this.month < date.month) { // compare months
            return BEFORE;
        } else if (this.month > date.month) {
            return AFTER;
        }
        if(this.day < date.day) { // compare days
            return BEFORE;
        } else if (this.day > date.day) {
            return AFTER;
        }
        return AFTER;
    }

    /**
     Testbed main() to test the functionality of the Date class when creating
     Date objects. Method will test valid and invalid arguments.
     * @param args default String array of multiple arguments. Not used.
     */
    public static void main(String[] args) {
        // invalid dates
        String[] invalid = new String[] {"2/29/2003", "10/24/2001", "4/31" +
                "/2003", "13/31/2003", "3/32/2003", "-1/31/2003", "2/29/2004"
                , "5/18/2030"};
        for (int i = 0; i < invalid.length; i++) {
            Date dob = new Date(invalid[i]);
            if (!dob.isValid()) { //valid DoB
                System.out.println("DOB invalid: " + dob + " not a valid calendar "
                        + "date!");
            } else if (!dob.checkIfSixteen()) { // over 16 years old
                System.out.println("DOB invalid: " + dob
                        + " younger than 16 years old.");
            } else {
                System.out.println(dob + " is a valid date.");
            }
        }

    }
}