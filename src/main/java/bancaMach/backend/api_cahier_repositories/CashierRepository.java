package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.DTOCashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepository extends JpaRepository<DTOCashier, Long> {
    @Query(
            value = "SELECT ST_X(position) AS longitude, ST_Y(position) AS latitude " +
                    "FROM cashier " +
                    "WHERE =  ST_DWithin(location, ST_SetSRID(ST_MakePoint(?1, ?2), 4326), 50;",

            nativeQuery = true)
    List<DTOCashier> getAllCashiersByLoc(Double lat, Double lng);

    @Query(
            value = "SELECT ST_X(position) AS longitude, ST_Y(position) AS latitude " +
                    "FROM cashier " +
                    "WHERE cp=?1;",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByCP(Integer cp);

    @Query(
            value = "SELECT ST_X(position) AS longitude, ST_Y(position) AS latitude " +
                    "FROM cashier " +
                    "WHERE =  ST_DWithin(location, ST_SetSRID(ST_MakePoint(?1, ?2), 4326), ?3;",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByDistance(Double lat, Double lng, Integer distance);
}
