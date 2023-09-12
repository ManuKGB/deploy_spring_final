package kgbStage.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inscription_concours")
public class InscriptionConcours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idInscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idEtudiant")
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idConcours")
    private Concours concours;

    private LocalDateTime dateInscription;
    private boolean statut;

//    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL)
//    List<Payement> payementList;

//    public List<Payement> getPayementList() {
//        return payementList;
//    }
//
//    public void setPayementList(List<Payement> payementList) {
//        this.payementList = payementList;
//    }

    public UUID getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(UUID idInscription) {
        this.idInscription = idInscription;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
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

    public InscriptionConcours(Etudiant etudiant, Concours concours) {
        this.etudiant = etudiant;
        this.concours = concours;
        this.dateInscription = LocalDateTime.now();
        this.statut =false;

    }

    public InscriptionConcours() {
        this.statut = false;
        this.dateInscription=LocalDateTime.now();
    }
}