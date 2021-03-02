package by.epam.jwd.finalproj.model.periodicals;

public enum PeriodicalType {
    BOOK(1),
    NEWSPAPER(2),
    MAGAZINE(3);

    private final int i;

    PeriodicalType(int i) {
        this.i = i;
    }
}
