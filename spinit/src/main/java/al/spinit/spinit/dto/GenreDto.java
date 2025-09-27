package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class GenreDto {
    private Long id;
    private String name;
    private Set<Product> product;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
