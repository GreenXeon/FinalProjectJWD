package by.epam.jwd.finalproj.model.periodicals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Periodical {
    int id;
    PeriodicalType type;
    String name;
    String publisher;
    String author;
    LocalDate publishDate;
    BigDecimal subCost;

    public Periodical(int id, String name, String author, LocalDate publishDate, PeriodicalType type, BigDecimal subCost, String publisher) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.publishDate = publishDate;
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public BigDecimal getSubCost() {
        return subCost;
    }
}
