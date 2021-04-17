package by.epam.jwd.finalproj.model.subscription;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SubscriptionDto implements Serializable {
    private int id;
    private int userId;
    private int periodicalId;
    private String paymentId;
    private String periodicalName;
    private BigDecimal subscriptionCost;
    private Timestamp subscriptionDate;

    public static class Builder{
        private SubscriptionDto subscriptionDto;

        public Builder(){
            subscriptionDto = new SubscriptionDto();
        }

        public Builder withUserId(int userId){
            subscriptionDto.userId = userId;
            return this;
        }

        public Builder withId(int id){
            subscriptionDto.id = id;
            return this;
        }

        public Builder withPeriodicalId(int periodicalId){
            subscriptionDto.periodicalId = periodicalId;
            return this;
        }

        public Builder withPaymentId(String paymentId){
            subscriptionDto.paymentId = paymentId;
            return this;
        }

        public Builder withPeriodicalName(String periodicalName){
            subscriptionDto.periodicalName = periodicalName;
            return this;
        }

        public Builder withSubscriptionCost(BigDecimal subscriptionCost){
            subscriptionDto.subscriptionCost = subscriptionCost;
            return this;
        }

        public Builder withSubscriptionDate(Timestamp subscriptionDate){
            subscriptionDto.subscriptionDate = subscriptionDate;
            return this;
        }

        public SubscriptionDto build(){
            return subscriptionDto;
        }
    }

    public int getUserId() {
        return userId;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSubscriptionCost() {
        return subscriptionCost;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionDto that = (SubscriptionDto) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (periodicalId != that.periodicalId) return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (periodicalName != null ? !periodicalName.equals(that.periodicalName) : that.periodicalName != null)
            return false;
        if (subscriptionCost != null ? !subscriptionCost.equals(that.subscriptionCost) : that.subscriptionCost != null)
            return false;
        return subscriptionDate != null ? subscriptionDate.equals(that.subscriptionDate) : that.subscriptionDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + periodicalId;
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (periodicalName != null ? periodicalName.hashCode() : 0);
        result = 31 * result + (subscriptionCost != null ? subscriptionCost.hashCode() : 0);
        result = 31 * result + (subscriptionDate != null ? subscriptionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubscriptionDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", periodicalId=" + periodicalId +
                ", paymentId='" + paymentId + '\'' +
                ", periodicalName='" + periodicalName + '\'' +
                ", subscriptionCost=" + subscriptionCost +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}


