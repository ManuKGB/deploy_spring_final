package kgbStage.api.repository;

import kgbStage.api.entity.ConfirmationPayement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationPayementRepository extends JpaRepository<ConfirmationPayement,String> {
}
