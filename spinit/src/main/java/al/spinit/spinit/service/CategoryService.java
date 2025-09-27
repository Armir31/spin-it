package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreateCategoryDto;
import al.spinit.spinit.entity.Category;
import al.spinit.spinit.repository.CategoryRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Category create (CreateCategoryDto createCategoryDto){
        Category category = modelMapper.map(createCategoryDto, Category.class);
        return categoryRepository.save(category);
    }
    public Category getById (Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category" + id + "not found!"));
    }
    public Category update (CreateCategoryDto createCategoryDto, Long id){
        Category existingCategory = getById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createCategoryDto, existingCategory);
        return categoryRepository.save(existingCategory);
    }
    public void deleteById (Long id) {
        categoryRepository.deleteById(id);
    }
    public List<Category> getList () {
        return categoryRepository.findAll();
    }
}
