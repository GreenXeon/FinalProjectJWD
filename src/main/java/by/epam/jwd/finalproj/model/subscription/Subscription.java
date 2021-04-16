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
}
