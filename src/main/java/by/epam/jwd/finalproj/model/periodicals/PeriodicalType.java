package by.epam.jwd.finalproj.model.periodicals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rewrite.LoggerNameLevelRewritePolicy;

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
        Logger logger = LogManager.getLogger(PeriodicalType.class);
        for (PeriodicalType type : PeriodicalType.values()){
            if (type.i == id){
                return type;
            }
        }
        return null;
    }
}
