package roster;

/**
 An enum class containing the months and their corresponding integer value.
 Call values using "Months.<SUBJECT>.num". For example
 "Months.JAN.num" will return "01".
 @author Victoria Chen, Bridget Zhang
 */
public enum Months {
    JAN (1),
    FEB(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUG(8),
    SEP(9),
    OCT(10),
    NOV(11),
    DEC(12),
    QUADRENNIAL(4),
    CENTENNIAL(100),
    QUATERCENTENNIAL(400);

    public final int num;

    Months(int num) {
        this.num = num;
    }

}
