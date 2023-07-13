package br.com.taroco.mustardmenu.domain.model.cashier;

import br.com.taroco.mustardmenu.domain.enumerator.CashierOperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_cashier_operation")
public class CashierOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_cashier", nullable = false)
    private Long idCashier;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CashierOperationTypeEnum type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
