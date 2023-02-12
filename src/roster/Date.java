package roster;
import java.util.Calendar;

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
     If the day or month is a single digit, add padding.
     @return String formatted as mm/dd/yyyy.
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
}