package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Long> {
    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE ST_DWithin(position, ST_MakePoint(:lat, :lng), 100) " +
                    "ORDER BY position;",

            nativeQuery = true)
    List<Cashier> getAllCashiersByLoc(@Param(value="lat")Double lat, @Param(value="lng")Double lng);

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE cp like :cp " +
                    "ORDER BY position;",
            nativeQuery = true)
    List<Cashier> getAllCashiersByCP(@Param(value="cp")String cp);

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE ST_DWithin(position, ST_MakePoint(:lat,:lng), :distanceM) " +
                    "ORDER BY position;",
            nativeQuery = true)
    List<Cashier> getAllCashiersByDistance(
            @Param(value="lat")Double lat,
            @Param(value="lng")Double lng,
            @Param(value="distanceM") Integer distanceM
    );

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE address ilike %:address% " +
                    "ORDER BY position;",
            nativeQuery = true)
    List<Cashier> getAllCashiersByAddress(@Param(value="address")String address);
}
