package al.spinit.spinit.dto;

import al.spinit.spinit.entity.OrderStatus;
import al.spinit.spinit.entity.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    private OrderStatus orderStatus;
    private Double totalAmount;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
