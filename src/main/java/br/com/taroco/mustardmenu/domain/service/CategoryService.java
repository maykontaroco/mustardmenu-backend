package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.product.Category;
import br.com.taroco.mustardmenu.infrastructure.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        return repository.save(category);
    }
}

