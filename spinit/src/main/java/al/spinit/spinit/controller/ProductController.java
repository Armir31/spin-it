package al.spinit.spinit.controller;

import al.spinit.spinit.dto.CreateProductDto;
import al.spinit.spinit.dto.ProductDto;
import al.spinit.spinit.entity.Product;
import al.spinit.spinit.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    public static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto createProductDto) {
        log.info("Creating product...");
        Product product = this.productService.create(createProductDto);
        return new ResponseEntity<>(modelMapper.map(product, ProductDto.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getList(){
        log.info("Getting list of product...");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id){
        Product product = productService.getById(id);
        return new ResponseEntity<>(modelMapper.map(product, ProductDto.class), HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id, @RequestBody CreateProductDto updateProductDto){
        log.info("Updating product...");
        Product saved = productService.update(updateProductDto, id);
        return new ResponseEntity<>(modelMapper.map(saved, ProductDto.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
        log.warn("Deleting product with ID: " + id);
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}