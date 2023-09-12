package kgbStage.api.controller;

import kgbStage.api.dto.ConcoursDTO;
import kgbStage.api.entity.Concours;
import kgbStage.api.services.ConcoursServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/concours")
public class ConcoursController {
    private final ConcoursServices concoursService;

    @Autowired
    public ConcoursController(ConcoursServices concoursService) {
        this.concoursService = concoursService;
    }

    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> getAllConcours() {
        return concoursService.getAllConcours();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getConcoursById(@PathVariable UUID id) {
        return concoursService.getConcoursById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object, Object>> createConcours(@RequestBody ConcoursDTO concoursDTO) {
        return concoursService.createConcours(concoursDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<Object, Object>> updateConcours(@PathVariable UUID id,
                                                                  @RequestBody ConcoursDTO concoursDTO) {
        return concoursService.updateConcours(id, concoursDTO);
    }

    @PutMapping("/postpone/{date}/{id}")
    public ResponseEntity<HashMap<Object,Object>> postponeDate(@PathVariable UUID id, @PathVariable LocalDateTime date,
                                                        @RequestBody ConcoursDTO concoursDTO)
    {
        return concoursService.addConcoursDate(id,concoursDTO,date);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> deleteConcours(@PathVariable("id") UUID idConcours) {
        return concoursService.deleteConcours(idConcours);
    }
}
