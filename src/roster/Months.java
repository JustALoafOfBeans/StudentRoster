package roster;

/**
 An enum class containing the months and their corresponding integer value.
 Call values using "Months.<SUBJECT>.num". For example
 "Months.JAN.num" will return "01".
 @author Victoria Chen, Bridget Zhang
 */
public enum Months {
    JAN ("01"),
    FEB("02"),
    MARCH("03"),
    APRIL("04"),
    MAY("05"),
    JUNE("06"),
    JULY("07"),
    AUG("08"),
    SEP("09"),
    OCT("10"),
    NOV("11"),
    DEC("12");

    public final String num;

    Months(String num) {
        this.num = num;
    }

}
