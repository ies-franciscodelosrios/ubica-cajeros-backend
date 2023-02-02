package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.DTOCashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepository extends JpaRepository<DTOCashier, Long> {
    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE ST_DWithin(position, ST_MakePoint(:lat, :lng), 500);",

            nativeQuery = true)
    List<DTOCashier> getAllCashiersByLoc(@Param(value="lat")Double lat,@Param(value="lng")Double lng);

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE cp = :cp;",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByCP(Integer cp);

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE ST_DWithin(position, ST_MakePoint(:lat,:lng), :distanceM);",
            nativeQuery = true)
    List<DTOCashier> getAllCashiersByDistance(
            @Param(value="lat")Double lat,
            @Param(value="lng")Double lng,
            @Param(value="distanceM") Integer distanceM
    );
}
