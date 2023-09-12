package kgbStage.api.services;

import kgbStage.api.dto.EcoleDTO;
import kgbStage.api.dto.EcoleDTO2;
import kgbStage.api.entity.Ecole;
import kgbStage.api.repository.EcoleRepository;
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
public class EcoleServices {
    private final EcoleRepository ecoleRepository;

    @Autowired
    public EcoleServices(EcoleRepository ecoleRepository) {
        this.ecoleRepository = ecoleRepository;
    }

    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "École créée avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Toutes les écoles récupérées");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Cette école n'existe pas !");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "École supprimée avec succès !");
            map.put("ok", true);
        } else if (statusCode == 205) {
            map.put("message", "École restaurée avec succès !");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }

    public ResponseEntity<HashMap<Object, Object>> getAllEcoles() {
        List<Ecole> ecoleList = ecoleRepository.findAll();
        List<EcoleDTO> ecoleDTOList = ecoleList.stream()
                .map(this::convertToEcoleDTO)
                .collect(Collectors.toList());
        var response = this.reponseModel(HttpStatus.OK.value(), ecoleDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> getEcoleById(UUID idEcole) {
        Optional<Ecole> ecole = ecoleRepository.findById(idEcole);
        if (ecole.isPresent()) {
            EcoleDTO2 ecoleDTO2 = convertToEcoleDTO2(ecole.get());
            var response = this.reponseModel(HttpStatus.OK.value(), ecoleDTO2);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> createEcole(EcoleDTO ecoleDTO) {
        Ecole ecole = convertToEcole(ecoleDTO);
        Ecole createdEcole = ecoleRepository.save(ecole);
        var response = this.reponseModel(HttpStatus.CREATED.value(), convertToEcoleDTO(createdEcole));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> updateEcole(UUID idEcole, EcoleDTO ecoleDTO) {
        Optional<Ecole> existingEcole = ecoleRepository.findById(idEcole);
        if (existingEcole.isPresent()) {
            Ecole updatedEcole = convertToEcole(ecoleDTO);
            updatedEcole.setIdEcole(idEcole);
            ecoleRepository.save(updatedEcole);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> deleteEcole(UUID idEcole) {
        Optional<Ecole> existingEcole = ecoleRepository.findById(idEcole);
        if (existingEcole.isPresent()) {
            ecoleRepository.deleteById(idEcole);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    public EcoleDTO convertToEcoleDTO(Ecole ecole) {
        EcoleDTO ecoleDTO = new EcoleDTO();
        if (ecole != null){
            ecoleDTO.setIdEcole(ecole.getIdEcole());
            ecoleDTO.setNom(ecole.getNom());
            ecoleDTO.setDescription(ecole.getDescription());
            ecoleDTO.setAdresse(ecole.getAdresse());
            ecoleDTO.setEmail(ecole.getEmail());
            ecoleDTO.setNumero(ecole.getNumero());
            ecoleDTO.setNumCompte(ecole.getNumCompte());
        }

        return ecoleDTO;
    }
    public EcoleDTO2 convertToEcoleDTO2(Ecole ecole) {
        EcoleDTO2 ecoleDTO = new EcoleDTO2();
        if (ecole != null){
            ecoleDTO.setIdEcole(ecole.getIdEcole());
            ecoleDTO.setNom(ecole.getNom());
            ecoleDTO.setDescription(ecole.getDescription());
            ecoleDTO.setAdresse(ecole.getAdresse());
            ecoleDTO.setEmail(ecole.getEmail());
            ecoleDTO.setNumero(ecole.getNumero());
            ecoleDTO.setNumCompte(ecole.getNumCompte());
            ecoleDTO.setConcours(ecole.getConcours());
        }

        return ecoleDTO;
    }

    public Ecole convertToEcole(EcoleDTO ecoleDTO) {
        Ecole ecole = new Ecole();
        if (ecoleDTO != null){
            ecole.setIdEcole(ecoleDTO.getIdEcole());
            ecole.setNom(ecoleDTO.getNom());
            ecole.setDescription(ecoleDTO.getDescription());
            ecole.setAdresse(ecoleDTO.getAdresse());
            ecole.setEmail(ecoleDTO.getEmail());
            ecole.setNumero(ecoleDTO.getNumero());
            ecole.setNumCompte(ecoleDTO.getNumCompte());
        }

        return ecole;
    }
}
