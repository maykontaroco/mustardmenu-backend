package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.cashier.Cashier;
import br.com.taroco.mustardmenu.domain.model.cashier.CashierConference;
import br.com.taroco.mustardmenu.infrastructure.persistence.CashierConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CashierConferenceService {

    private final CashierConferenceRepository repository;

    public List<CashierConference> getAll() {
        return repository.findAll();
    }

    public CashierConference save(CashierConference cashierConference) {
        return repository.save(cashierConference);
    }
}
