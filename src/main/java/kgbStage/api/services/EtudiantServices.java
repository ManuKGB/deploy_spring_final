package kgbStage.api.services;
import kgbStage.api.dto.EtudiantDTO;
import kgbStage.api.entity.Ecole;
import kgbStage.api.entity.Etudiant;
import kgbStage.api.entity.InscriptionConcours;
import kgbStage.api.repository.EtudiantRepository;
import kgbStage.api.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class EtudiantServices {
    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    InscriptionRepository inscriptionRepository;


    public HashMap<Object,Object> reponseModel(int statusCode, Object result){
        HashMap<Object,Object> map = new HashMap<>();
        map.put("statusCode",statusCode);
        if (statusCode==201){
            map.put("message","Étudiant creer avec succès");
            map.put("ok",true);
        } else if (statusCode==200) {
            map.put("message","Tout les étudiants sont récupérer");
            map.put("ok",true);
        } else if (statusCode==404) {
            map.put("message","Ce étudiant n'existe pas!");
            map.put("ok",false);
        } else if (statusCode==204) {
            map.put("message","Étudiant  avec success!");
            map.put("ok",true);
        } else if (statusCode==205) {
            map.put("message","Étudiant restaurer avec success!");
            map.put("ok ",true);
        }
        map.put("response",result);
        return map;
    }
    public ResponseEntity<HashMap<Object,Object>> allEtudiant(){
        var allEtudiants = etudiantRepository.findAll();
        var response = this.reponseModel(HttpStatus.OK.value(), allEtudiants);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<HashMap<Object,Object>> saveEtudiant(Etudiant etudiant){
        var response = this.reponseModel(HttpStatus.CREATED.value(),etudiant);
        etudiantRepository.save(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> getEtudiantById(UUID idEtudiant) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(idEtudiant);
        if (etudiant.isPresent()) {
            Etudiant etudiantData = etudiant.get();
            ///List<InscriptionConcours> inscriptions = inscriptionRepository.find(idEtudiant);
            var response = this.reponseModel(HttpStatus.OK.value(), etudiantData);
            //response.put("inscription", etudiantData.getInscription());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public EtudiantDTO convertToEtudiantDTO(Etudiant etudiant){
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        if (etudiant != null){
            etudiantDTO.setIdEtudiant(etudiant.getIdEtudiant());
            etudiantDTO.setNom(etudiant.getNom());
            etudiantDTO.setNumero(etudiant.getNumero());
            etudiantDTO.setMail(etudiant.getMail());
            etudiantDTO.setDateNaiss(etudiant.getDateNaiss());
            etudiantDTO.setSexe(etudiant.getSexe());
            etudiantDTO.setPrenom(etudiant.getPrenom());
            etudiantDTO.setInscription(etudiant.getInscription());
        }

        return etudiantDTO;
    }
    public Etudiant convertToEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        if (etudiantDTO!= null){
            etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
            etudiant.setNom(etudiantDTO.getNom());
            etudiant.setNumero(etudiantDTO.getNumero());
            etudiant.setMail(etudiantDTO.getMail());
            etudiant.setDateNaiss(etudiantDTO.getDateNaiss());
            etudiant.setSexe(etudiantDTO.getSexe());
            etudiant.setPrenom(etudiantDTO.getPrenom());
            etudiant.setInscription(etudiantDTO.getInscription());
        }

        return etudiant;
    }








}
