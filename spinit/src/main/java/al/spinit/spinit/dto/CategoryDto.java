package al.spinit.spinit.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    @JoinColumn(name = "created_at")
    private LocalDateTime createdAt;
    @JoinColumn(name = "updated_at")
    private LocalDateTime updatedAt;
}
