package al.spinit.spinit.dto;

import al.spinit.spinit.entity.PaymentMethod;
import al.spinit.spinit.entity.PaymentStatus;
import al.spinit.spinit.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;
    private LocalDateTime paymentDate;
    private String amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
