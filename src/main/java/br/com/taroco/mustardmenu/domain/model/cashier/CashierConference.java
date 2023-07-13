package br.com.taroco.mustardmenu.domain.model.cashier;

import br.com.taroco.mustardmenu.domain.enumerator.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_cashier_conference")
public class CashierConference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_cashier", nullable = false)
    private Long idCashier;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;
}
