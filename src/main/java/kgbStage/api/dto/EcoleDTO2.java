package kgbStage.api.dto;

import kgbStage.api.entity.Concours;

import java.util.List;
import java.util.UUID;

public class EcoleDTO2 {
    private UUID idEcole;
    private String nom;
    private String description;
    private String adresse;
    private String email;
    private String numero;
    private String numCompte;

    private List<Concours> concours;

    public UUID getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(UUID idEcole) {
        this.idEcole = idEcole;
    }

    public String getNom() {
        return nom;
    }

    public List<Concours> getConcours() {
        return concours;
    }

    public void setConcours(List<Concours> concours) {
        this.concours = concours;
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
}
