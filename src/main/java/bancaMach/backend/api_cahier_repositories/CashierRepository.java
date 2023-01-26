package bancaMach.backend.api_cahier_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends JpaRepository<DTOCashier, Long> {

}
