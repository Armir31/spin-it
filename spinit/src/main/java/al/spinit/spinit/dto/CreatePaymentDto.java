package al.spinit.spinit.dto;

import al.spinit.spinit.entity.PaymentMethod;
import al.spinit.spinit.entity.PaymentStatus;
import al.spinit.spinit.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePaymentDto {
    private LocalDateTime paymentDate;
    private String amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private User user;
}
