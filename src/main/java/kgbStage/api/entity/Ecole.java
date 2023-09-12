package kgbStage.api.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ecoles")
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEcole;
    private String nom;
    private String description;
    private String adresse;
    private String email;
    private String numero;
    private String numCompte;
    @OneToMany(mappedBy = "ecole", cascade = CascadeType.ALL)
    private List<Concours> concours;

    public Ecole( String nom, String description, String adresse, String email, String numero, String numCompte) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.email = email;
        this.numero = numero;
        this.numCompte = numCompte;
    }

    public Ecole() {

    }

    public UUID getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(UUID idEcole) {
        this.idEcole = idEcole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public List<Concours> getConcours() {
        return concours;
    }

    public void setConcours(List<Concours> concours) {
        this.concours = concours;
    }
}
