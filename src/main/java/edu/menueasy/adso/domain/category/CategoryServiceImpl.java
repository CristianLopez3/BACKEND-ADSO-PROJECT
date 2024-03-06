package edu.menueasy.adso.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find category with the given id: "+id));
    }


}
