package kgbStage.api.controller;

import kgbStage.api.entity.Etudiant;
import kgbStage.api.services.EtudiantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/etudiant")

public class EtudiantController {
    @Autowired
    EtudiantServices etudiantServices;
    /*
     * RECUPERER TOUT LES CLIENTS
     * */
    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> allEtudiant(){
        return etudiantServices.allEtudiant();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getEtudiantById(@PathVariable("id") UUID idEtudiant) {
        return etudiantServices.getEtudiantById(idEtudiant);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object,Object>> createClient(@RequestBody Etudiant etudiant) {
        return etudiantServices.saveEtudiant(etudiant);
    }







}
