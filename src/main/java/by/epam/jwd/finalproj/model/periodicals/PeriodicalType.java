package by.epam.jwd.finalproj.model.periodicals;

public enum PeriodicalType {
    NEWSPAPER(1),
    MAGAZINE(2),
    COMICS(3);

    private final int i;

    PeriodicalType(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static PeriodicalType findById(int id){
        for (PeriodicalType type : PeriodicalType.values()){
            if (type.i == id){
                return type;
            }
        }
        return null;
    }
}
