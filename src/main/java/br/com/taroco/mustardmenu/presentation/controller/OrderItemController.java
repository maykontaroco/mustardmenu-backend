package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.order.Order;
import br.com.taroco.mustardmenu.domain.model.order.OrderItem;
import br.com.taroco.mustardmenu.domain.service.OrderItemService;
import br.com.taroco.mustardmenu.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/order/{id}/item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getItems() {
        List<OrderItem> items = orderItemService.getAll();
        if (items != null && !items.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(items);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<OrderItem> getItem(@PathVariable Long itemId) {
        OrderItem orderItem = orderItemService.findById(itemId);
        if (orderItem != null)
            return ResponseEntity.status(HttpStatus.OK).body(orderItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping()
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem) {
        OrderItem item = orderItemService.save(orderItem);
        Order order = orderService.findById(orderItem.getIdOrder());
        orderService.refreshTotal(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItem> update(@PathVariable Long itemId, @RequestBody OrderItem orderItem) {
        if (orderItem == null || !orderItem.getId().equals(itemId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        OrderItem item = orderItemService.save(orderItem);
        Order order = orderService.findById(orderItem.getIdOrder());
        orderService.refreshTotal(order);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/{itemId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long itemId) {
        OrderItem orderItem = orderItemService.findById(itemId);
        if (orderItem == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        orderItemService.cancel(orderItem);
        Order order = orderService.findById(orderItem.getIdOrder());
        orderService.refreshTotal(order);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{itemId}/quantity/add")
    public ResponseEntity<OrderItem> addQuantity(@PathVariable Long itemId) {
        OrderItem orderItem = orderItemService.findById(itemId);
        if (orderItem == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        OrderItem item = orderItemService.addQuantity(orderItem);
        Order order = orderService.findById(orderItem.getIdOrder());
        orderService.refreshTotal(order);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/{itemId}/quantity/remove")
    public ResponseEntity<OrderItem> removeQuantity(@PathVariable Long itemId) {
        OrderItem orderItem = orderItemService.findById(itemId);
        if (orderItem == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        OrderItem item = orderItemService.removeQuantity(orderItem);
        Order order = orderService.findById(orderItem.getIdOrder());
        orderService.refreshTotal(order);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }
}
