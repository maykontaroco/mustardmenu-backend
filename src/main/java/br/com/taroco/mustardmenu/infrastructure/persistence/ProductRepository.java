package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
