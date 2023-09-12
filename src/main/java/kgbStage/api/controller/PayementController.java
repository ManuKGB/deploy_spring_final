package kgbStage.api.controller;

import kgbStage.api.entity.Payement;
import kgbStage.api.services.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payement")
public class PayementController {
    @Autowired
    PayementService payementService;

    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> getAllPayements() {
        return payementService.getAllPayements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getPayementById(@PathVariable("id") UUID idPayement) {
        return payementService.getPayementById(idPayement);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object, Object>> createPayement(@RequestBody Payement payement) {
        return payementService.savePayement(payement);
    }

    @GetMapping("/inscription/{idInscription}")
    public ResponseEntity<HashMap<Object,Object>> getPayementByInscription(@PathVariable UUID idInscription){
        return  payementService.getPayementByInscription(idInscription);
    }
}
