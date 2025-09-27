package al.spinit.spinit.dto;


import al.spinit.spinit.entity.Product;
import lombok.Data;

import java.util.Set;

@Data
public class CreateGenreDto {
    private String name;
    private Set<Product> product;
}
