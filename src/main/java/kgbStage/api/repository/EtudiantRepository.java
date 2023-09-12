package kgbStage.api.repository;

import kgbStage.api.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EtudiantRepository  extends JpaRepository<Etudiant, UUID> {
}
