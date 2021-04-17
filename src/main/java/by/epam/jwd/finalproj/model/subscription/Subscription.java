package by.epam.jwd.finalproj.model.subscription;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Subscription {
    private int id;
    private int userId;
    private int periodicalId;
    private String periodicalName;
    private String paymentId;
    private BigDecimal subscriptionCost;
    private Timestamp subscriptionDate;

    public static class Builder{
        private Subscription subscription;

        public Builder(){
            subscription = new Subscription();
        }

        public Builder withId(int id){
            subscription.id = id;
            return this;
        }

        public Builder withUserId(int userId){
            subscription.userId = userId;
            return this;
        }

        public Builder withPeriodicalName(String periodicalName){
            subscription.periodicalName = periodicalName;
            return this;
        }

        public Builder withPeriodicalId(int periodicalId){
            subscription.periodicalId = periodicalId;
            return this;
        }

        public Builder withPaymentId(String paymentId){
            subscription.paymentId = paymentId;
            return this;
        }

        public Builder withSubscriptionCost(BigDecimal subscriptionCost){
            subscription.subscriptionCost = subscriptionCost;
            return this;
        }

        public Builder withSubscriptionDate(Timestamp subscriptionDate){
            subscription.subscriptionDate = subscriptionDate;
            return this;
        }

        public Subscription build(){
            return subscription;
        }
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public String getPaymentId() {
        return paymentId;
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

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (periodicalId != that.periodicalId) return false;
        if (periodicalName != null ? !periodicalName.equals(that.periodicalName) : that.periodicalName != null)
            return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (subscriptionCost != null ? !subscriptionCost.equals(that.subscriptionCost) : that.subscriptionCost != null)
            return false;
        return subscriptionDate != null ? subscriptionDate.equals(that.subscriptionDate) : that.subscriptionDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + periodicalId;
        result = 31 * result + (periodicalName != null ? periodicalName.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (subscriptionCost != null ? subscriptionCost.hashCode() : 0);
        result = 31 * result + (subscriptionDate != null ? subscriptionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", periodicalId=" + periodicalId +
                ", periodicalName='" + periodicalName + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", subscriptionCost=" + subscriptionCost +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
