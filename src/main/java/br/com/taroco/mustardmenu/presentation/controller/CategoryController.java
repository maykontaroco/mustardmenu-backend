package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.product.Category;
import br.com.taroco.mustardmenu.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category) {
        if (category.getId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        if (category.getId() != null && !category.getId().equals(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }
}
