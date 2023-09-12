package kgbStage.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payements")
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPayement;

    private LocalDateTime date;

    private boolean statut;

    private String montant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInscription")
    private InscriptionConcours inscription;

    public Payement(  InscriptionConcours inscription) {
        this.date = LocalDateTime.now();
        this.statut = false;
        //this.montant = inscription.getConcours().getMontant();
        this.inscription = inscription;
    }

    public Payement() {
        this.date=LocalDateTime.now();
    }

    public UUID getIdPayement() {
        return idPayement;
    }

    public void setIdPayement(UUID idPayement) {
        this.idPayement = idPayement;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isStatut() {
        return statut;
    }

    public InscriptionConcours getInscription() {
        return inscription;
    }

    public void setInscription(InscriptionConcours inscription) {
        this.inscription = inscription;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}
