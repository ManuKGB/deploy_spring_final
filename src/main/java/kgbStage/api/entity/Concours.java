package kgbStage.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name="concours")
public class Concours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConcours;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String description;
    private  LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String montant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idEcole")
    private Ecole ecole;

    @OneToMany(mappedBy = "concours", cascade = CascadeType.ALL)
    private List<InscriptionConcours> inscription;

    public List<InscriptionConcours> getInscription() {
        return inscription;
    }

    public void setInscription(List<InscriptionConcours> inscription) {
        this.inscription = inscription;
    }

    public Concours(String libelle, String description, LocalDateTime dateDebut, LocalDateTime dateFin, String montant, Ecole ecole) {
        this.libelle = libelle;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.ecole = ecole;
        this.dateCreation = LocalDateTime.now();

    }

    public Concours(String libelle, String description, LocalDateTime dateCreation, LocalDateTime dateDebut, LocalDateTime dateFin, String montant) {
        this.libelle = libelle;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
    }

    public Concours() {
        this.dateCreation = LocalDateTime.now();
    }

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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
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

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }
}
