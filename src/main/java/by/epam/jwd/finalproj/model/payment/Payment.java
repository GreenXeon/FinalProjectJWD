package by.epam.jwd.finalproj.model.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private String paymentId;
    private int userId;
    private Timestamp paymentTime;
    private BigDecimal paymentCost;

    public static class Builder{
        private Payment payment;

        public Builder(){
            payment = new Payment();
        }

        public Builder withId(String paymentId){
            payment.paymentId = paymentId;
            return this;
        }

        public Builder withUserId(int userId){
            payment.userId = userId;
            return this;
        }

        public Builder withPaymentTime(Timestamp paymentTime){
            payment.paymentTime = paymentTime;
            return this;
        }

        public Builder withPaymentCost(BigDecimal paymentCost){
            payment.paymentCost = paymentCost;
            return this;
        }

        public Payment build(){
            return payment;
        }
    }

    public String getPaymentId() {
        return paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public BigDecimal getPaymentCost() {
        return paymentCost;
    }
}
