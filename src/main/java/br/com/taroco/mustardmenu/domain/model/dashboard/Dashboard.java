package br.com.taroco.mustardmenu.domain.model.dashboard;

import br.com.taroco.mustardmenu.domain.enumerator.DashboardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    DashboardType type;
    String description;
    BigDecimal value;
}
