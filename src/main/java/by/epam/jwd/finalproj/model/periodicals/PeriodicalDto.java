package by.epam.jwd.finalproj.model.periodicals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PeriodicalDto implements Serializable {
    private int id;
    private PeriodicalType type;
    private String name;
    private String publisher;
    private String author;
    private LocalDate publishDate;
    private BigDecimal subCost;

    public PeriodicalDto(int id, String name, String author, LocalDate publishDate, PeriodicalType type, BigDecimal subCost, String publisher) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalDto that = (PeriodicalDto) o;
        return id == that.id && type == that.type && Objects.equals(name, that.name) && Objects.equals(publisher, that.publisher) && Objects.equals(author, that.author) && Objects.equals(publishDate, that.publishDate) && Objects.equals(subCost, that.subCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, publisher, author, publishDate, subCost);
    }

    @Override
    public String toString() {
        return "PeriodicalDTO{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishDate +
                ", subCost=" + subCost +
                '}';
    }
}
