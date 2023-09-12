package kgbStage.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import kgbStage.api.enums.Sexe;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEtudiant;
    private String nom;
    private  String prenom;
    private String numero;
    private String mail;
    private String sexe;
    private Date dateNaiss;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<InscriptionConcours> inscription;

    public Etudiant() {

    }

    public Etudiant(String nom, String prenom, String numero, String mail, String sexe, Date dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.mail = mail;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
    }

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

    public List<InscriptionConcours> getInscription() {
        return inscription;
    }

    public void setInscription(List<InscriptionConcours> inscription) {
        this.inscription = inscription;
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
}
