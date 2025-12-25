package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Genre;
import al.spinit.spinit.entity.ProductType;
import al.spinit.spinit.entity.Track;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CreateProductDto {
    private String name;
    private String description;
    private ProductType type;
    @JsonProperty("track_list")
    private List<Track> trackList;
    private Double price;
    private String image;
    private Set<Genre> genre = new HashSet<>();

    @Data
    public static class TrackRequest {
        private Integer trackNumber;
        private String title;
        private String duration;
    }
}
