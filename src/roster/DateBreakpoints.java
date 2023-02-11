package roster;

public enum DateBreakpoints {
    MAXDAYS(31),
    SHORTERMONTH(30),
    MAXMONTHS(12),
    MONTHPADDING(1),
    QUADRENNIAL(4),
    CENTENNIAL(100),
    QUATERCENTENNIAL(400);

    public final int num;

    DateBreakpoints(int num) {
        this.num = num;
    }

}
