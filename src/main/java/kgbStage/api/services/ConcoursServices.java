package kgbStage.api.services;

import kgbStage.api.dto.ConcoursDTO;
import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Ecole;
import kgbStage.api.repository.ConcoursRepository;
import kgbStage.api.repository.EcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConcoursServices {
    private final ConcoursRepository concoursRepository;

    @Autowired
    public ConcoursServices(ConcoursRepository concoursRepository) {
        this.concoursRepository = concoursRepository;
    }
    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private EcoleServices ecoleServices;

    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "Concours créé avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Tous les concours sont récupérés");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Ce concours n'existe pas !");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "Concours supprimé avec succès !");
            map.put("ok", true);
        } else if (statusCode == 205) {
            map.put("message", "Concours restauré avec succès !");
            map.put("ok", true);
        }else if (statusCode == 202){
            map.put("message", "Date du concours mis à jour avec succès!");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }

    public ResponseEntity<HashMap<Object, Object>> getAllConcours() {
        List<Concours> concoursList = concoursRepository.findAll();
        List<ConcoursDTO> concoursDTOList = concoursList.stream()
                .map(this::convertToConcoursDTO)
                .collect(Collectors.toList());
        var response = this.reponseModel(HttpStatus.OK.value(), concoursDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> getConcoursById(UUID idConcours) {
        Optional<Concours> concours = concoursRepository.findById(idConcours);
        if (concours.isPresent()) {
            ConcoursDTO concoursDTO = convertToConcoursDTO(concours.get());
            var response = this.reponseModel(HttpStatus.OK.value(), concoursDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> createConcours(ConcoursDTO concoursDTO) {
        //Concours createdConcours = concoursRepository.save(concours);
        Concours concours = convertToConcours(concoursDTO);
        Ecole ecole = ecoleRepository.findById(concours.getEcole().getIdEcole()).get();
        concours.setEcole(ecole);
        concoursRepository.save(concours);
        //var response = this.reponseModel(HttpStatus.CREATED.value(), concours);
        return getConcoursById(concours.getIdConcours());
    }


    public ResponseEntity<HashMap<Object, Object>> updateConcours(UUID idConcours, ConcoursDTO concoursDTO) {
        Optional<Concours> existingConcours = concoursRepository.findById(idConcours);
        if (existingConcours.isPresent()) {
            Concours updatedConcours = convertToConcours(concoursDTO);
            updatedConcours.setIdConcours(idConcours);
            concoursRepository.save(updatedConcours);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public  ResponseEntity<HashMap<Object,Object>> addConcoursDate(UUID idConcours, ConcoursDTO concoursDTO, LocalDateTime date){
        Optional<Concours> existingConcours = concoursRepository.findById(idConcours);
        if (existingConcours.isPresent()) {
            Concours updatedConcours = convertToConcours(concoursDTO);
            updatedConcours.setIdConcours(idConcours);
            updatedConcours.setDateFin(date);
            concoursRepository.save(updatedConcours);
            var response = this.reponseModel(HttpStatus.ACCEPTED.value(), concoursDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> deleteConcours(UUID idConcours) {
        Optional<Concours> existingConcours = concoursRepository.findById(idConcours);
        if (existingConcours.isPresent()) {
            concoursRepository.deleteById(idConcours);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    public ConcoursDTO convertToConcoursDTO(Concours concours) {
        ConcoursDTO concoursDTO = new ConcoursDTO();
        if (concours != null) {
            concoursDTO.setIdConcours(concours.getIdConcours());
            concoursDTO.setLibelle(concours.getLibelle());
            concoursDTO.setDescription(concours.getDescription());
            concoursDTO.setDateDebut(concours.getDateDebut());
            concoursDTO.setDateFin(concours.getDateFin());
            concoursDTO.setMontant(concours.getMontant());
            concoursDTO.setEcole(ecoleServices.convertToEcoleDTO(concours.getEcole()));
        }
        return concoursDTO;
    }


    public Concours convertToConcours(ConcoursDTO concoursDTO) {
        Concours concours = new Concours();
        if (concoursDTO != null){
            concours.setIdConcours(concoursDTO.getIdConcours());
            concours.setLibelle(concoursDTO.getLibelle());
            concours.setDescription(concoursDTO.getDescription());
            concours.setDateDebut(concoursDTO.getDateDebut());
            concours.setDateFin(concoursDTO.getDateFin());
            concours.setMontant(concoursDTO.getMontant());
            concours.setEcole(ecoleServices.convertToEcole(concoursDTO.getEcole()));
        }

        return concours;
    }
}
