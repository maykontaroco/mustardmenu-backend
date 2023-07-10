package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
