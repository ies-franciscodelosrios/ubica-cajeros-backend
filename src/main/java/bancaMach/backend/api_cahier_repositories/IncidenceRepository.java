package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.dataobject.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {
}
