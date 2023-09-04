package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.enumerator.DashboardType;
import br.com.taroco.mustardmenu.domain.model.dashboard.Dashboard;
import br.com.taroco.mustardmenu.domain.model.order.Order;
import br.com.taroco.mustardmenu.infrastructure.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardService {

    private final OrderRepository repository;

    public List<Dashboard> getToday() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Order> ordersToday = repository.findByDateBetween(startOfDay, endOfDay);
        Dashboard valueOrders = getValueOrders(ordersToday);
        Dashboard totalOrders = getTotalOrders(ordersToday);
        Dashboard totalProducts = getTotalProducts(ordersToday);
        Dashboard averageTicket = getAverageTicket(ordersToday);

        return Arrays.asList(valueOrders, totalOrders, totalProducts, averageTicket);
    }

    public List<Dashboard> getWeek() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Order> ordersToday = repository.findByDateBetween(startOfDay, endOfDay);
        Dashboard valueOrders = getValueOrders(ordersToday);
        Dashboard totalOrders = getTotalOrders(ordersToday);
        Dashboard totalProducts = getTotalProducts(ordersToday);
        Dashboard averageTicket = getAverageTicket(ordersToday);

        return Arrays.asList(valueOrders, totalOrders, totalProducts, averageTicket);
    }

    private Dashboard getValueOrders(List<Order> orders) {
        BigDecimal value = orders.stream()
                .map(Order::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Dashboard(DashboardType.MONEY, "Valor em vendas", value);
    }

    private Dashboard getTotalOrders(List<Order> orders) {
        BigDecimal value = new BigDecimal(orders.size());
        return new Dashboard(DashboardType.UNIT, "Vendas", value);
    }

    private Dashboard getTotalProducts(List<Order> orders) {
        BigDecimal value = new BigDecimal(orders.stream()
                .mapToInt(order -> order.getItems().stream()
                        .mapToInt(item -> item.getQuantity().intValue()) // Extrai a quantidade de cada objeto OrderItem
                        .sum()) // Soma as quantidades de itens de cada Order
                .sum());
        return new Dashboard(DashboardType.UNIT, "Produtos vendidos", value);
    }

    private Dashboard getAverageTicket(List<Order> orders) {
        BigDecimal valueOrders = orders.stream()
                .map(Order::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalOrders = new BigDecimal(orders.size());
        BigDecimal value = orders.size() > 0 ? valueOrders.divide(totalOrders, RoundingMode.HALF_EVEN) : BigDecimal.ZERO;

        return new Dashboard(DashboardType.MONEY, "Ticket médio", value);
    }
}
