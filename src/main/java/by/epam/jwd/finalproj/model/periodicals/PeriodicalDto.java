package by.epam.jwd.finalproj.model.periodicals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    public PeriodicalDto() {
    }

    public static class Builder{
        private PeriodicalDto periodicalDto;

        public Builder(){
            periodicalDto = new PeriodicalDto();
        }

        public Builder withId(int id){
            periodicalDto.id = id;
            return this;
        }

        public Builder withType(PeriodicalType type){
            periodicalDto.type = type;
            return this;
        }

        public Builder withName(String name){
            periodicalDto.name = name;
            return this;
        }

        public Builder withPublisher(String publisher){
            periodicalDto.publisher = publisher;
            return this;
        }

        public Builder withAuthor(String author){
            periodicalDto.author = author;
            return this;
        }

        public Builder withPublishDate(LocalDate publishDate){
            periodicalDto.publishDate = publishDate;
            return this;
        }

        public Builder withCost(BigDecimal cost){
            periodicalDto.subCost = cost;
            return this;
        }

        public PeriodicalDto build(){
            return periodicalDto;
        }
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

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        return subCost != null ? subCost.equals(that.subCost) : that.subCost == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (subCost != null ? subCost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PeriodicalDto{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", subCost=" + subCost +
                '}';
    }
}
