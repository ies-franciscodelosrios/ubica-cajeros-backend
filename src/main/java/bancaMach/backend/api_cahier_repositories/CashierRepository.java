package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_models.DTORequestGeoCashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepository extends JpaRepository<DTOCashier, Long> {
    @Query(
            value = "SELECT",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByLoc(DTORequestGeoCashier georeq);

    @Query(
            value = "SELECT",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByCP(Integer cp);
}
