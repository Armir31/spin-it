package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Product;
import lombok.Data;

@Data
public class CreateTrackDto {
    private Integer trackNumber;
    private String title;
    private String duration;
    private Product product;
}
