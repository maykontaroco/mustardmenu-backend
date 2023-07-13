package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.cashier.CashierOperation;
import br.com.taroco.mustardmenu.infrastructure.persistence.CashierOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CashierOperationService {

    private final CashierOperationRepository repository;

    public List<CashierOperation> getAll() {
        return repository.findAll();
    }

    public CashierOperation save(CashierOperation cashierOperation) {
        return repository.save(cashierOperation);
    }
}
