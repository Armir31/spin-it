package al.spinit.spinit.dto;

import al.spinit.spinit.entity.OrderStatus;
import al.spinit.spinit.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private Long userId;
    private List<CreateOrderItemDto> orderItems;
}
