package by.epam.jwd.finalproj.model.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubscriptionDto {
    private int userId;
    private String userLogin;
    private int periodicalId;
    private String paymentId;
    private BigDecimal subscriptionCost;
    private LocalDateTime subscriptionDate;

    public static class Builder{
        private SubscriptionDto subscriptionDto;

        public Builder(){
            subscriptionDto = new SubscriptionDto();
        }

        public Builder withUserId(int userId){
            subscriptionDto.userId = userId;
            return this;
        }

        public Builder withUserLogin(String userLogin){
            subscriptionDto.userLogin = userLogin;
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

        public Builder withSubscriptionCost(BigDecimal subscriptionCost){
            subscriptionDto.subscriptionCost = subscriptionCost;
            return this;
        }

        public Builder withSubscriptionDate(LocalDateTime subscriptionDate){
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

    public String getUserLogin() {
        return userLogin;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public BigDecimal getSubscriptionCost() {
        return subscriptionCost;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }
}


