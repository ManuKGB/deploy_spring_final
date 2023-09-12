package kgbStage.api.repository;

import kgbStage.api.entity.InscriptionConcours;
import kgbStage.api.entity.Payement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PayementRepository extends JpaRepository<Payement, UUID> {
    List<Payement> findByInscription(InscriptionConcours inscriptionConcours);

}
