package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.order.Order;
import br.com.taroco.mustardmenu.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getAll();
        if (orders != null && !orders.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null)
            return ResponseEntity.status(HttpStatus.OK).body(order);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create());
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            orderService.cancel(order);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}/addition")
    public ResponseEntity<Void> addition(@PathVariable Long id, @RequestBody BigDecimal value) {
        Order order = orderService.findById(id);
        if (order != null) {
            orderService.addition(order, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}/discount")
    public ResponseEntity<Void> discount(@PathVariable Long id, @RequestBody BigDecimal value) {
        Order order = orderService.findById(id);
        if (order != null) {
            orderService.discount(order, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
