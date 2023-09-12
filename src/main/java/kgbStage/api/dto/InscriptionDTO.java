package kgbStage.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Etudiant;

import java.time.LocalDateTime;
import java.util.UUID;

public class InscriptionDTO {
    private UUID idInscription;
    private EtudiantDTO etudiant;
    private ConcoursDTO concours;
    private LocalDateTime dateInscription;
    private boolean statut;

    public UUID getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(UUID idInscription) {
        this.idInscription = idInscription;
    }

    public EtudiantDTO getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(EtudiantDTO etudiant) {
        this.etudiant = etudiant;
    }

    public ConcoursDTO getConcours() {
        return concours;
    }

    public void setConcours(ConcoursDTO concours) {
        this.concours = concours;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public InscriptionDTO() {
        this.dateInscription =LocalDateTime.now();
    }
}
