package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GenreDto {
    private Long id;
    private String name;
    private Set<Product> product = new HashSet<>();

}
