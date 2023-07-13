package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.cashier.Cashier;
import br.com.taroco.mustardmenu.domain.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cashier")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @GetMapping()
    public ResponseEntity<List<Cashier>> getCashier() {
        return ResponseEntity.status(HttpStatus.OK).body(cashierService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cashier> getCashier(@PathVariable Long id) {
        Cashier cashier = cashierService.findById(id);
        if (cashier != null)
            return ResponseEntity.status(HttpStatus.OK).body(cashier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping
    public ResponseEntity<Cashier> post(@RequestBody Cashier cashier) {
        if (cashier.getId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(cashierService.save(cashier));
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<Cashier> finish(@PathVariable Long id) {
        Cashier cashier = cashierService.findById(id);
        if (cashier != null) {
            cashier.finish();
            return ResponseEntity.status(HttpStatus.OK).body(cashierService.save(cashier));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
