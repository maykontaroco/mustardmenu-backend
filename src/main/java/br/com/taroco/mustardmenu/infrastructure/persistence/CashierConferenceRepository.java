package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.cashier.CashierConference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierConferenceRepository extends JpaRepository<CashierConference, Long> {
}
