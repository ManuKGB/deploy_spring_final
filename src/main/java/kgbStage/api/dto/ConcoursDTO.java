package kgbStage.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcoursDTO {
    private UUID idConcours;
    private String libelle;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String montant;
    private EcoleDTO ecole;

    public UUID getIdConcours() {
        return idConcours;
    }

    public void setIdConcours(UUID idConcours) {
        this.idConcours = idConcours;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public EcoleDTO getEcole() {
        return ecole;
    }

    public void setEcole(EcoleDTO ecole) {
        this.ecole = ecole;
    }
}
