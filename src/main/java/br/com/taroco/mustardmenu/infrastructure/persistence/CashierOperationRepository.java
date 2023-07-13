package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.cashier.CashierOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierOperationRepository extends JpaRepository<CashierOperation, Long> {
}
