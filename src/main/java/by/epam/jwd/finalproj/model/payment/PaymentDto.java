package by.epam.jwd.finalproj.model.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PaymentDto implements Serializable {
    private String paymentId;
    private int userId;
    private Timestamp paymentTime;
    private BigDecimal paymentCost;

    public static class Builder{
        private PaymentDto paymentDto;

        public Builder(){
            paymentDto = new PaymentDto();
        }

        public Builder withId(String paymentId){
            paymentDto.paymentId = paymentId;
            return this;
        }

        public Builder withUserId(int userId){
            paymentDto.userId = userId;
            return this;
        }

        public Builder withPaymentTime(Timestamp paymentTime){
            paymentDto.paymentTime = paymentTime;
            return this;
        }

        public Builder withPaymentCost(BigDecimal paymentCost){
            paymentDto.paymentCost = paymentCost;
            return this;
        }

        public PaymentDto build(){
            return paymentDto;
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

        PaymentDto that = (PaymentDto) o;

        if (userId != that.userId) return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (paymentTime != null ? !paymentTime.equals(that.paymentTime) : that.paymentTime != null) return false;
        return paymentCost != null ? paymentCost.equals(that.paymentCost) : that.paymentCost == null;
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
        return "PaymentDto{" +
                "paymentId='" + paymentId + '\'' +
                ", userId=" + userId +
                ", paymentTime=" + paymentTime +
                ", paymentCost=" + paymentCost +
                '}';
    }
}
