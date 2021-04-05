package by.epam.jwd.finalproj.model.payment;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PaymentDto {
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
}
