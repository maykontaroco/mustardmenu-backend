package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.cashier.CashierOperation;
import br.com.taroco.mustardmenu.domain.service.CashierOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cashier/operation")
public class CashierOperationController {

    @Autowired
    private CashierOperationService cashierOperationService;

    @GetMapping()
    public ResponseEntity<List<CashierOperation>> getCashierOperations() {
        return ResponseEntity.status(HttpStatus.OK).body(cashierOperationService.getAll());
    }

    @PostMapping
    public ResponseEntity<CashierOperation> post(@RequestBody CashierOperation cashierOperation) {
        if (cashierOperation.getId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(cashierOperationService.save(cashierOperation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashierOperation> update(@PathVariable Long id, @RequestBody CashierOperation cashierOperation) {
        if (cashierOperation.getId() != null && !cashierOperation.getId().equals(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(cashierOperationService.save(cashierOperation));
    }
}
