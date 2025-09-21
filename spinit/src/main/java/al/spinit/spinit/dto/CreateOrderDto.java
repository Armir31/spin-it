package al.spinit.spinit.dto;

import al.spinit.spinit.entity.OrderStatus;
import al.spinit.spinit.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateOrderDto {
    @JsonProperty("order_number")
    private String orderNumber;
    @JsonProperty("order_status")
    private OrderStatus orderStatus;
    @JsonProperty("total_amount")
    private Double totalAmount;
    private User user;
}
