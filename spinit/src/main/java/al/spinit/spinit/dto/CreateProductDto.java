package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductDto {
    private String name;
    private String description;
    @JsonProperty("track_list")
    private String trackList;
    private Double price;
    private String image;
    private Set<Genre> genre = new HashSet<>();
}
