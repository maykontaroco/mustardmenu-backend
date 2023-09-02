package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.exception.InvalidPaymentException;
import br.com.taroco.mustardmenu.domain.model.order.Order;
import br.com.taroco.mustardmenu.domain.model.order.OrderPayment;
import br.com.taroco.mustardmenu.domain.service.OrderPaymentService;
import br.com.taroco.mustardmenu.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/order/{id}/payment")
public class OrderPaymentController {

    @Autowired
    private OrderPaymentService orderPaymentService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderPayment>> getPayments() {
        List<OrderPayment> payments = orderPaymentService.getAll();
        if (payments != null && !payments.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(payments);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping
    public ResponseEntity<OrderPayment> create(@RequestBody OrderPayment orderPayment) {
        Order order = orderService.findById(orderPayment.getIdOrder());

        //Valid payment
        if (!order.paymentIsValid(orderPayment.getValue()))
            throw new InvalidPaymentException();

        //Insert payment
        ResponseEntity<OrderPayment> response = ResponseEntity.status(HttpStatus.CREATED).body(orderPaymentService.save(orderPayment));

        //Valid order paid
        if (order.orderIsPaid())
            orderService.finalize(order);

        return response;
    }
}
