package br.com.taroco.mustardmenu.presentation.controller;

import br.com.taroco.mustardmenu.domain.model.cashier.CashierConference;
import br.com.taroco.mustardmenu.domain.service.CashierConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cashier/conference")
public class CashierConferenceController {

    @Autowired
    private CashierConferenceService cashierConferenceService;

    @GetMapping()
    public ResponseEntity<List<CashierConference>> getCashierConferences() {
        return ResponseEntity.status(HttpStatus.OK).body(cashierConferenceService.getAll());
    }

    @PostMapping
    public ResponseEntity<CashierConference> post(@RequestBody CashierConference cashierConference) {
        if (cashierConference.getId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(cashierConferenceService.save(cashierConference));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashierConference> update(@PathVariable Long id, @RequestBody CashierConference cashierConference) {
        if (cashierConference.getId() != null && !cashierConference.getId().equals(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(cashierConferenceService.save(cashierConference));
    }
}
