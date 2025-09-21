package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderItemDto {
    private Long id;
    @JsonProperty("product_id")
    private Long productId;
    private Integer quantity;
    private Double price;
    @JsonProperty("sub_total")
    private Double subTotal;
    private Order order;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
