package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
