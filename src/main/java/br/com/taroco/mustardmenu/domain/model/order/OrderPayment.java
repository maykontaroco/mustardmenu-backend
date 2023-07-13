package br.com.taroco.mustardmenu.domain.model.order;

import br.com.taroco.mustardmenu.domain.enumerator.PaymentTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_order_payment")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPayment that = (OrderPayment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
