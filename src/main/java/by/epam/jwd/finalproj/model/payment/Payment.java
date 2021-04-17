package by.epam.jwd.finalproj.model.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (userId != payment.userId) return false;
        if (paymentId != null ? !paymentId.equals(payment.paymentId) : payment.paymentId != null) return false;
        if (paymentTime != null ? !paymentTime.equals(payment.paymentTime) : payment.paymentTime != null) return false;
        return paymentCost != null ? paymentCost.equals(payment.paymentCost) : payment.paymentCost == null;
    }

    @Override
    public int hashCode() {
        int result = paymentId != null ? paymentId.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (paymentTime != null ? paymentTime.hashCode() : 0);
        result = 31 * result + (paymentCost != null ? paymentCost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", userId=" + userId +
                ", paymentTime=" + paymentTime +
                ", paymentCost=" + paymentCost +
                '}';
    }
}
