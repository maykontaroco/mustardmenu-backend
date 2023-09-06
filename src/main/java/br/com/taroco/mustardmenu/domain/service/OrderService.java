package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.order.Order;
import br.com.taroco.mustardmenu.infrastructure.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Order create() {
        //TODO localizar idCashier e idUser
        Order order = new Order(1L, 1L);
        return save(order);
    }

    public void cancel(Order order) {
        order.cancel();
        save(order);
    }

    public void addition(Order order, BigDecimal value) {
        order.addition(value);
        save(order);
    }

    public void discount(Order order, BigDecimal value) {
        order.discount(value);
        save(order);
    }

    public void finalize(Order order) {
        order.conclude();
        save(order);
    }

    public void refreshTotal(Order order) {
        order.refreshTotal();
        save(order);
    }
}
