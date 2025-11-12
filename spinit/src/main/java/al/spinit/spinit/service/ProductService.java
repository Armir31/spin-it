package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreateProductDto;
import al.spinit.spinit.entity.Product;
import al.spinit.spinit.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Product> getList () {
        return (productRepository.findAll());
    }
    public Product getById (Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException
                        ("Product with ID" + id + "not found!"));
    }
    public void deleteById (Long id) {
        productRepository.deleteById(id);
    }
    public Product create (CreateProductDto createProductDto) {
        Product product = modelMapper.map(createProductDto, Product.class);
        return productRepository.save(product);
    }
    public Product update (CreateProductDto createProductDto, Long id){
        Product existingProduct = getById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createProductDto, existingProduct);
        return productRepository.save(existingProduct);
    }
}
