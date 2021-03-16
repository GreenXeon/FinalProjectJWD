package by.epam.jwd.finalproj.model.periodicals;

import java.math.BigDecimal;

public class Periodical {
    int id;
    PeriodicalType type;
    String name;
    String publisher;
    String author;
    int publishYear;
    BigDecimal subCost;

    public Periodical(int id, String name, String author, int publishYear, PeriodicalType type, BigDecimal subCost, String publisher) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.publishYear = publishYear;
        this.subCost = subCost;
    }

    public int getId() {
        return id;
    }

    public PeriodicalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public BigDecimal getSubCost() {
        return subCost;
    }
}
