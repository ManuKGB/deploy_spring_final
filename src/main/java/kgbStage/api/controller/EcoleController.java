package kgbStage.api.controller;

import kgbStage.api.dto.EcoleDTO;
import kgbStage.api.entity.Ecole;
import kgbStage.api.services.EcoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/ecole")
public class EcoleController {
    private final EcoleServices ecoleService;

    @Autowired
    public EcoleController(EcoleServices ecoleService) {
        this.ecoleService = ecoleService;
    }

    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> getAllEcoles() {
        return ecoleService.getAllEcoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getEcoleById(@PathVariable("id") UUID idEcole) {
        return ecoleService.getEcoleById(idEcole);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object, Object>> createEcole(@RequestBody EcoleDTO ecole) {
        return ecoleService.createEcole(ecole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> updateEcole(
            @PathVariable("id") UUID idEcole,
            @RequestBody EcoleDTO ecole
    ) {
        return ecoleService.updateEcole(idEcole, ecole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> deleteEcole(@PathVariable("id") UUID idEcole) {
        return ecoleService.deleteEcole(idEcole);
    }
}
