package kgbStage.api.controller;

import kgbStage.api.dto.ConcoursDTO;
import kgbStage.api.dto.EcoleDTO;
import kgbStage.api.dto.EtudiantDTO;
import kgbStage.api.dto.InscriptionDTO;
import kgbStage.api.entity.InscriptionConcours;
import org.springframework.beans.BeanUtils;

import kgbStage.api.services.InscriptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {
    @Autowired

    private InscriptionServices inscriptionServices;
    private HashMap<String, Object> createResponse(int statusCode, String message, Object data) {
        HashMap<String, Object> response = new HashMap<>();
        if (statusCode == 200){
            response.put("statusCode", statusCode);
            response.put("ok", true);
            response.put("message", message);
            response.put("response", data);
        } else if (statusCode == 404 ) {
            response.put("statusCode", statusCode);
            response.put("ok", false);
            response.put("message", message);
            response.put("response", null);
        } else if (statusCode == 204) {
            response.put("statusCode", statusCode);
            response.put("ok", false);
            response.put("message", message);
            response.put("response", null);
        }

        return response;
    }



    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> getAllInscriptionConcours() {
        return inscriptionServices.getAllInscriptionConcours();
    }



//        @Autowired
//        private InscriptioServices inscriptionService;

        @GetMapping("/etudiant/{etudiantId}")
        public ResponseEntity<HashMap<String, Object>> getInscriptionsByEtudiantId(@PathVariable UUID etudiantId) {
            List<InscriptionConcours> inscriptions = inscriptionServices.getInscriptionsByEtudiantId(etudiantId);

            if (inscriptions.isEmpty()) {
                var response = createResponse(HttpStatus.NO_CONTENT.value(), "Aucune inscription trouvée pour cet étudiant.", null);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            } else {
                List<InscriptionDTO> inscriptionDTOs = inscriptions.stream()
                        .map(this::convertToInscriptionDTO)
                        .collect(Collectors.toList());
                var response = createResponse(HttpStatus.OK.value(), "Inscriptions récupérées avec succès.", inscriptionDTOs);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }


    @PutMapping("update/{idInscription}")
    public ResponseEntity<HashMap<Object,Object>> updateInscription(@PathVariable UUID idInscription){
        return inscriptionServices.updateInscription(idInscription);
    }



    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getInscriptionConcoursById(@PathVariable UUID id) {
        return inscriptionServices.getInscriptionConcoursById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object, Object>> saveInscriptionConcours(@RequestBody InscriptionDTO inscriptionDTO) {
        return inscriptionServices.saveInscriptionConcours(inscriptionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> deleteInscriptionConcours(@PathVariable UUID id) {
        return inscriptionServices.deleteInscriptionConcours(id);
    }


    public InscriptionDTO convertToInscriptionDTO(InscriptionConcours inscription) {
        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        BeanUtils.copyProperties(inscription, inscriptionDTO);

        // Convertir l'étudiant
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        BeanUtils.copyProperties(inscription.getEtudiant(), etudiantDTO);
        inscriptionDTO.setEtudiant(etudiantDTO);

        // Convertir le concours
        ConcoursDTO concoursDTO = new ConcoursDTO();
        BeanUtils.copyProperties(inscription.getConcours(), concoursDTO);

        // Convertir l'école liée au concours
        EcoleDTO ecoleDTO = new EcoleDTO();
        BeanUtils.copyProperties(inscription.getConcours().getEcole(), ecoleDTO);
        concoursDTO.setEcole(ecoleDTO);

        inscriptionDTO.setConcours(concoursDTO);

        return inscriptionDTO;
    }


}

