package br.com.taroco.mustardmenu.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "canceled", nullable = false)
    private boolean canceled = false;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity = BigDecimal.ONE;

    @Column(name = "unit_value", nullable = false)
    private BigDecimal unitValue;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "cost_value")
    private BigDecimal costValue;

    public OrderItem(Long idOrder, Long idProduct, BigDecimal unitValue) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.unitValue = unitValue;
        this.refreshTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void refreshTotal() {
        this.totalValue = this.quantity.multiply(this.unitValue);
    }

    public void addQuantity() {
        this.quantity = this.quantity.add(BigDecimal.ONE);
        refreshTotal();
    }

    public void removeQuantity() {
        if (this.quantity.compareTo(BigDecimal.ONE) <= 0)
            return;
        this.quantity = this.quantity.subtract(BigDecimal.ONE);
        refreshTotal();
    }

    public void cancel() {
        this.canceled = true;
    }
}
