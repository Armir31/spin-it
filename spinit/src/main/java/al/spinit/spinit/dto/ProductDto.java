package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Genre;
import al.spinit.spinit.entity.ProductType;
import al.spinit.spinit.entity.Track;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private ProductType type;
    @JsonProperty("track_list")
    private List<Track> trackList;
    private Double price;
    private String image;
    private Set<Genre> genre = new HashSet<>();
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

    @Data
    public static class TrackRequest {
        private int trackNumber;
        private String title;
        private String duration;
    }
}

