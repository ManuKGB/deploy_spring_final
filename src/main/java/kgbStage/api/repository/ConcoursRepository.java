package kgbStage.api.repository;

import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Ecole;
import kgbStage.api.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConcoursRepository extends JpaRepository<Concours, UUID> {
    List<Concours> findByEcole(Ecole ecole);

}
