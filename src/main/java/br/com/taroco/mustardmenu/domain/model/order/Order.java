package br.com.taroco.mustardmenu.domain.model.order;

import br.com.taroco.mustardmenu.domain.enumerator.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status = OrderStatusEnum.OPEN;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "id_cashier", nullable = false)
    private Long idCashier;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "addition")
    private BigDecimal addition;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "observation")
    private String observation;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_order")
    private List<OrderPayment> payments = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_order")
    private List<OrderItem> items = new ArrayList<>();

    public Order(Long idCashier, Long idUser) {
        this.idCashier = idCashier;
        this.idUser = idUser;
    }

    public void cancel() {
        status = OrderStatusEnum.CANCELED;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public void addPayment(OrderPayment payment) {
        payments.add(payment);
    }

    public void removePayment(OrderPayment payment) {
        payments.remove(payment);
    }

}
