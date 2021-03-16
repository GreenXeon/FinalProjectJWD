package by.epam.jwd.finalproj.model.periodicals;

import java.math.BigDecimal;
import java.util.Objects;

public class PeriodicalDto {
    PeriodicalType type;
    String name;
    String publisher;
    String author;
    int publishYear;
    BigDecimal subCost;

    public PeriodicalDto(String name, String author, int publishYear, PeriodicalType type, BigDecimal subCost, String publisher) {
        this.type = type;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.publishYear = publishYear;
        this.subCost = subCost;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalDto that = (PeriodicalDto) o;
        return publishYear == that.publishYear && type == that.type && name.equals(that.name) && publisher.equals(that.publisher) && author.equals(that.author) && subCost.equals(that.subCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, publisher, author, publishYear, subCost);
    }

    @Override
    public String toString() {
        return "PeriodicalDTO{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", subCost=" + subCost +
                '}';
    }
}
