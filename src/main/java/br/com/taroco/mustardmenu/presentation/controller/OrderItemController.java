package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.order.OrderItem;
import br.com.taroco.mustardmenu.domain.service.OrderItemService;
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

    @GetMapping
    public ResponseEntity<List<OrderItem>> getItems() {
        List<OrderItem> items = orderItemService.getAll();
        if (items != null && !items.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(items);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<OrderItem> getItem(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findById(id);
        if (orderItem != null)
            return ResponseEntity.status(HttpStatus.OK).body(orderItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping()
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.save(orderItem));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        if (orderItem == null || !orderItem.getId().equals(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.save(orderItem));
    }

    @PutMapping("/{itemId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findById(id);
        if (orderItem != null) {
            orderItemService.cancel(orderItem);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
