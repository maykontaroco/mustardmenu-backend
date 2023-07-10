package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.product.Product;
import br.com.taroco.mustardmenu.infrastructure.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }
}
