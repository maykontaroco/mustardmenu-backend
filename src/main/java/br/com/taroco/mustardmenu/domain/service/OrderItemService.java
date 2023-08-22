package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.order.OrderItem;
import br.com.taroco.mustardmenu.infrastructure.persistence.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    public OrderItem findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public OrderItem save(OrderItem orderItem) {
        orderItem.refreshTotal();
        return repository.save(orderItem);
    }

    public void cancel(OrderItem orderItem) {
        orderItem.cancel();
        save(orderItem);
    }

    public OrderItem addQuantity(OrderItem orderItem) {
        orderItem.addQuantity();
        return save(orderItem);
    }

    public OrderItem removeQuantity(OrderItem orderItem) {
        orderItem.removeQuantity();
        return save(orderItem);
    }
}
