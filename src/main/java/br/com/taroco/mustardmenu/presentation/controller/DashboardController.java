package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.dashboard.Dashboard;
import br.com.taroco.mustardmenu.domain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/today")
    public ResponseEntity<List<Dashboard>> getDashboardToday() {
        List<Dashboard> dashboardList = dashboardService.getToday();
        if (dashboardList != null && !dashboardList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(dashboardList);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/week")
    public ResponseEntity<List<Dashboard>> getDashboardWeek() {
        List<Dashboard> dashboardList = dashboardService.getWeek();
        if (dashboardList != null && !dashboardList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(dashboardList);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
