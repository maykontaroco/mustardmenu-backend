package br.com.taroco.mustardmenu.domain.model.order;

import br.com.taroco.mustardmenu.domain.enumerator.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Column(name = "addition", nullable = false)
    private BigDecimal addition = BigDecimal.ZERO;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount = BigDecimal.ZERO;

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

    public void refreshTotal() {
        this.amount = BigDecimal.ZERO;
        for (OrderItem item : this.items) {
            if (!item.isCanceled())
                this.amount = amount.add(item.getTotalValue());
        }
        this.amount = this.amount.add(addition).subtract(this.discount);
    }

    public void cancel() {
        status = OrderStatusEnum.CANCELED;
    }

    public void conclude() {
        status = OrderStatusEnum.FINISHED;
    }

    public BigDecimal totalPayments() {
        BigDecimal totalPaid = BigDecimal.ZERO;
        for (OrderPayment payment : this.getPayments()) {
            totalPaid = totalPaid.add(payment.getValue());
        }
        return totalPaid;
    }

    public boolean orderIsPaid() {
        return this.getAmount().compareTo(this.totalPayments()) == 0;
    }

    public boolean paymentIsValid(BigDecimal value) {
        BigDecimal totalPayments = this.totalPayments().add(value);
        return this.getAmount().compareTo(totalPayments) >= 0;
    }

}
