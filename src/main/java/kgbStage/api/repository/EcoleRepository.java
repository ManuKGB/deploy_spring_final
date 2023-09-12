package kgbStage.api.repository;

import kgbStage.api.entity.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EcoleRepository extends JpaRepository<Ecole,UUID> {
}
