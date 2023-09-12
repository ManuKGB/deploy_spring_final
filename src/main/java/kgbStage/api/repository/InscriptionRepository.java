package kgbStage.api.repository;

import kgbStage.api.entity.InscriptionConcours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InscriptionRepository extends JpaRepository<InscriptionConcours, UUID> {
}
