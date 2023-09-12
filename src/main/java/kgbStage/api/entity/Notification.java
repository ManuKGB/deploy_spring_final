package kgbStage.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idNotification;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String description;

    private LocalDateTime date;

    public Notification(String libelle, String description, Concours concours) {
        this.libelle = libelle;
        this.description = description;
        this.concours = concours;
        this.date = LocalDateTime.now();
    }

    public Notification() {
        this.date=LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idConcours")
    private Concours concours;

    public UUID getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(UUID idNotification) {
        this.idNotification = idNotification;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }
}
