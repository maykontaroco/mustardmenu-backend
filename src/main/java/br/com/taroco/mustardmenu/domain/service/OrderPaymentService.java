package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.order.OrderPayment;
import br.com.taroco.mustardmenu.infrastructure.persistence.OrderPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderPaymentService {

    private final OrderPaymentRepository repository;

    public List<OrderPayment> getAll() {
        return repository.findAll();
    }

    public OrderPayment save(OrderPayment orderPayment) {
        return repository.save(orderPayment);
    }
}
