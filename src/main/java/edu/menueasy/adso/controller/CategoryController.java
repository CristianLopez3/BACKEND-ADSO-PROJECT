package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.category.Category;
import edu.menueasy.adso.domain.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

  private CategoryRepository categoryRepository;

  @PostMapping()
  public ResponseEntity<Category> createCategory(@RequestBody() Category category){
      return ResponseEntity.ok(categoryRepository.save(category));
  }

  @GetMapping()
  public ResponseEntity<List<Category>> getCategories(){
    return ResponseEntity.ok(categoryRepository.findAll());
  }



}
