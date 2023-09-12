package kgbStage.api.services;

import kgbStage.api.entity.Ecole;
import kgbStage.api.entity.InscriptionConcours;
import kgbStage.api.entity.Payement;
import kgbStage.api.repository.EtudiantRepository;
import kgbStage.api.repository.InscriptionRepository;
import kgbStage.api.repository.PayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayementService {
    @Autowired
    PayementRepository payementRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    InscriptionRepository inscriptionConcoursRepository;

    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "Paiement créé avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Tous les paiements sont récupérés");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Ce paiement n'existe pas!");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "Paiement supprimé avec succès!");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }

    public ResponseEntity<HashMap<Object, Object>> getAllPayements() {
        List<Payement> allPayements = payementRepository.findAll();
        var response = this.reponseModel(HttpStatus.OK.value(), allPayements);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> savePayement(Payement payement) {
        var response = this.reponseModel(HttpStatus.CREATED.value(), payement);
        Optional<InscriptionConcours> optionalInscriptionConcours = inscriptionConcoursRepository
                .findById(payement.getInscription().getIdInscription());
        if (optionalInscriptionConcours.isPresent()) {
            InscriptionConcours inscriptionConcours = optionalInscriptionConcours.get();
            payement.setInscription(inscriptionConcours);
            payement.setMontant(inscriptionConcours.getConcours().getMontant());
            payementRepository.save(payement);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            var errorResponse = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    public ResponseEntity<HashMap<Object, Object>> getPayementById(UUID idPayement) {
        Optional<Payement> payement = payementRepository.findById(idPayement);
        if (payement.isPresent()) {
            Payement payementData = payement.get();
            var response = this.reponseModel(HttpStatus.OK.value(), payementData);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object,Object>> getPayementByInscription(UUID inscriptionId) {
        // Recherchez l'inscription par son ID
        InscriptionConcours inscriptionConcours = inscriptionConcoursRepository.findById(inscriptionId).orElse(null);
        if (inscriptionConcours == null) {
            var reponse = this.reponseModel(HttpStatus.NOT_FOUND.value(),Collections.EMPTY_LIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
        }
        // Récupérez tous les paiements associés à l'inscription
        var reponse = this.reponseModel(HttpStatus.OK.value(),
                payementRepository.findByInscription(inscriptionConcours) );
        return ResponseEntity.status(HttpStatus.OK).body(reponse);
    }


//    public ResponseEntity<HashMap<Object,Object>> statutPayement(){
//
//    }
}
