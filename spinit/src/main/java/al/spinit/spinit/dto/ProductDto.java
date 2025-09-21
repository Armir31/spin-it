package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("track_list")
    private String trackList;
    private Double price;
    private String image;
    private Set<Genre> genre = new HashSet<>();
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;
}

