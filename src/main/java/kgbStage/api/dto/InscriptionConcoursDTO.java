package kgbStage.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Etudiant;
import kgbStage.api.entity.Payement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InscriptionConcoursDTO {

    private UUID idInscription;


    private Etudiant etudiant;


    private Concours concours;

    private LocalDateTime dateInscription;
    private boolean statut;


    List<Payement> payementList;

}
