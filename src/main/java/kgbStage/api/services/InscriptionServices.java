package kgbStage.api.services;

import kgbStage.api.dto.ConcoursDTO;
import kgbStage.api.dto.InscriptionDTO;
import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Etudiant;
import kgbStage.api.entity.InscriptionConcours;
import kgbStage.api.entity.Payement;
import kgbStage.api.repository.ConcoursRepository;
import kgbStage.api.repository.EtudiantRepository;
import kgbStage.api.repository.InscriptionRepository;
import kgbStage.api.repository.PayementRepository;
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
public class InscriptionServices {

    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired ConcoursServices concoursServices;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ConcoursRepository concoursRepository;

    @Autowired
    PayementRepository payementRepository;

@Autowired
private EtudiantServices etudiantServices;

    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "Inscription au concours créée avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Toutes les inscriptions au concours récupérées");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Cette inscription au concours n'existe pas !");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "Inscription au concours supprimée avec succès");
            map.put("ok", true);
        } else if (statusCode == 205) {
            map.put("message", "Inscription au concours restaurée avec succès");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }


    public ResponseEntity<HashMap<Object, Object>> getAllInscriptionConcours() {
        List<InscriptionConcours> inscriptionList =inscriptionRepository.findAll();
        List<InscriptionDTO> inscriptionDTOList = inscriptionList.stream()
                .map(this::convertToInscriptionDTO)
                .collect(Collectors.toList());
        var response = this.reponseModel(HttpStatus.OK.value(), inscriptionDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public ResponseEntity<HashMap<Object, Object>> getInscriptionConcoursById(UUID idInscriptionConcours) {
        Optional<InscriptionConcours> inscriptionConcoursOptional = inscriptionRepository.findById(idInscriptionConcours);

        if (inscriptionConcoursOptional.isPresent()) {
            InscriptionConcours inscriptionConcours = inscriptionConcoursOptional.get();

            // Convertir l'objet InscriptionConcours en InscriptionDTO
            InscriptionDTO inscriptionDTO = convertToInscriptionDTO(inscriptionConcours);

            var response = this.reponseModel(HttpStatus.OK.value(), inscriptionDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    public ResponseEntity<HashMap<Object, Object>> saveInscriptionConcours(
            InscriptionDTO inscriptionDTO) {
        InscriptionConcours inscriptionConcours = convertToInscription(inscriptionDTO);
        Etudiant etudiant = etudiantRepository.findById(inscriptionConcours.getEtudiant().getIdEtudiant()).get();
        Concours concours = concoursRepository.findById(inscriptionConcours.getConcours().getIdConcours()).get();
        inscriptionConcours.setEtudiant(etudiant);
        inscriptionConcours.setConcours(concours);
        inscriptionRepository.save(inscriptionConcours);
       return getInscriptionConcoursById(inscriptionConcours.getIdInscription());
    }

    public List<InscriptionConcours> getInscriptionsByEtudiantId(UUID etudiantId) {
        var etudiant = etudiantRepository.findById(etudiantId);
        return etudiant.get().getInscription();
        //return inscriptionRepository.findByEtudiantId(etudiantId);
    }

    public ResponseEntity<HashMap<Object,Object>> updateInscription(UUID idInscription){
        Optional<InscriptionConcours> inscriptionConcoursOptional = inscriptionRepository.findById(idInscription);

        if (inscriptionConcoursOptional.isPresent()) {
            InscriptionConcours inscriptionConcours = inscriptionConcoursOptional.get();
            inscriptionConcours.setStatut(true);
            inscriptionRepository.save(inscriptionConcours);
            InscriptionDTO inscriptionDTO = convertToInscriptionDTO(inscriptionConcours);

            var response = this.reponseModel(HttpStatus.OK.value(), inscriptionDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> deleteInscriptionConcours(UUID idInscriptionConcours) {
        Optional<InscriptionConcours> inscriptionConcours = inscriptionRepository.findById(idInscriptionConcours);
        if (inscriptionConcours.isPresent()) {
            inscriptionRepository.deleteById(idInscriptionConcours);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public InscriptionDTO convertToInscriptionDTO(InscriptionConcours inscription) {
        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        if (inscription != null){
            inscriptionDTO.setIdInscription(inscription.getIdInscription());
            inscriptionDTO.setDateInscription(inscription.getDateInscription());
            inscriptionDTO.setStatut(inscription.isStatut());
            inscriptionDTO.setEtudiant( etudiantServices.convertToEtudiantDTO(inscription.getEtudiant()) );
            inscriptionDTO.setConcours( concoursServices.convertToConcoursDTO(inscription.getConcours()) );
        }

        return inscriptionDTO;
    }
    public InscriptionConcours convertToInscription(InscriptionDTO inscriptionDTO) {
        InscriptionConcours inscriptionConcours = new InscriptionConcours();
        if (inscriptionDTO!=null){
            inscriptionConcours.setIdInscription(inscriptionDTO.getIdInscription());
            inscriptionConcours.setDateInscription(inscriptionDTO.getDateInscription());
            inscriptionConcours.setStatut(inscriptionDTO.isStatut());
            inscriptionConcours.setEtudiant(etudiantServices.convertToEtudiant(inscriptionDTO.getEtudiant()));
            inscriptionConcours.setConcours(concoursServices.convertToConcours(inscriptionDTO.getConcours()));
        }

        return inscriptionConcours;
    }



}
