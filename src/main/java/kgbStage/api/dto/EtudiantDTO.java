package kgbStage.api.dto;

import jakarta.persistence.*;
import kgbStage.api.entity.InscriptionConcours;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EtudiantDTO {

    private UUID idEtudiant;
    private String nom;
    private  String prenom;
    private String numero;
    private String mail;
    private String sexe;
    private Date dateNaiss;
    private List<InscriptionConcours> inscription;

    public UUID getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(UUID idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public List<InscriptionConcours> getInscription() {
        return inscription;
    }

    public void setInscription(List<InscriptionConcours> inscription) {
        this.inscription = inscription;
    }
}
