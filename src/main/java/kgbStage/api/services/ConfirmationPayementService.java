package kgbStage.api.services;

import kgbStage.api.dto.EcoleDTO;
import kgbStage.api.entity.ConfirmationPayement;
import kgbStage.api.entity.Ecole;
import kgbStage.api.entity.Etudiant;
import kgbStage.api.repository.ConfirmationPayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConfirmationPayementService {
    @Autowired
    private ConfirmationPayementRepository confirmationPayementRepository;


    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "Confirmation créée avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Toutes les confirmations récupérées");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Cette confirmation n'existe pas !");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "Confirmation supprimée avec succès !");
            map.put("ok", true);
        } else if (statusCode == 205) {
            map.put("message", "Confirmation restaurée avec succès !");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }
    public ResponseEntity<HashMap<Object, Object>> getAllConfirmation() {
        List<ConfirmationPayement> confirmationPayementList = confirmationPayementRepository.findAll();
        var response = this.reponseModel(HttpStatus.OK.value(), confirmationPayementList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public  ResponseEntity<HashMap<Object,Object>> getOneConfirmation(String idConfirmation){
        Optional<ConfirmationPayement> confirmationPayement = confirmationPayementRepository.findById(idConfirmation);
        if (confirmationPayement.isPresent()){
            ConfirmationPayement confirmation = confirmationPayement.get();
            var response = this.reponseModel(HttpStatus.OK.value(), confirmation);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> saveConfirmation(ConfirmationPayement confirmationPayement) {

       ConfirmationPayement confirmationPayement1 = confirmationPayementRepository.save(confirmationPayement);
        var response = this.reponseModel(HttpStatus.CREATED.value(), confirmationPayement1);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
