package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.cashier.Cashier;
import br.com.taroco.mustardmenu.infrastructure.persistence.CashierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CashierService {

    private final CashierRepository repository;

    public List<Cashier> getAll() {
        return repository.findAll();
    }

    public Cashier findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cashier save(Cashier cashier) {
        return repository.save(cashier);
    }
}
