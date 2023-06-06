package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_dto.atm.ATMSaveDTO;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO cashier (address, available, balance, cp, locality, photo, position)" +
                    "VALUES (:address, :available, :balance, :cp, :locality, encode(pg_read_binary_file(:photo),'base64'), " +
                                "ST_SetSRID(ST_Makepoint(:latitude, :longitude),4326));",
            nativeQuery = true)
    void saveWithPhoto(
                        @Param(value = "address")String address,
                        @Param(value = "available")boolean available,
                        @Param(value = "balance")double balance,
                        @Param(value = "cp")String cp,
                        @Param(value = "locality")String locality,
                        @Param(value = "photo")String photo,
                        @Param(value = "latitude")double latitude,
                        @Param(value = "longitude")double longitude
    );

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO cashier (address, available, balance, cp, locality, position)" +
                    "VALUES (:address, :available, :balance, :cp, :locality, ST_SetSRID(ST_Makepoint(:latitude, :longitude),4326));",
            nativeQuery = true)
    void saveWithoutPhoto(
            @Param(value = "address")String address,
            @Param(value = "available")boolean available,
            @Param(value = "balance")double balance,
            @Param(value = "cp")String cp,
            @Param(value = "locality")String locality,
            @Param(value = "latitude")double latitude,
            @Param(value = "longitude")double longitude
    );

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE :id IN " +
                        "(SELECT id " +
                        "FROM cashier " +
                        "WHERE position = ST_SetSRID(ST_Makepoint(:lat, :lon),4326)) " +
                    "AND id = :id ;",
            nativeQuery = true)
    Optional<Cashier> findATMByIDAndPosition(
            @Param(value="id")long id,
            @Param(value="lat")double lattiude,
            @Param(value="lon")double longitude
    );

    @Query(
            value = "SELECT * " +
                    "FROM cashier " +
                    "WHERE position = ST_SetSRID(ST_Makepoint(:lat, :lon),4326) ;",
            nativeQuery = true)
    Optional<Cashier> findATMByPosition(
            @Param(value="lat")double lattiude,
            @Param(value="lon")double longitude
    );

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE cashier " +
                    "SET address = :address, available = :available, balance = :balance, cp = :cp, locality = :locality, " +
                        "photo = encode(pg_read_binary_file(:photo),'base64') " +
                    "WHERE id = :id ;",
            nativeQuery = true)
    int updateWithPhoto(
            @Param(value = "id")Long id,
            @Param(value = "address")String address,
            @Param(value = "available")boolean available,
            @Param(value = "balance")double balance,
            @Param(value = "cp")String cp,
            @Param(value = "locality")String locality,
            @Param(value = "photo")String photo
    );

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE cashier " +
                    "SET address = :address, available = :available, balance = :balance, cp = :cp, locality = :locality " +
                    "WHERE id = :id ;",
            nativeQuery = true)
    int updateWithoutPhoto(
            @Param(value = "id")Long id,
            @Param(value = "address")String address,
            @Param(value = "available")boolean available,
            @Param(value = "balance")double balance,
            @Param(value = "cp")String cp,
            @Param(value = "locality")String locality
    );

    //Sentencia Insert alternativa
    //INSERT INTO cashier (position)
    // VALUES (ST_GeographyFromText('POINT(37.8851372511271 -4.779386997689956)'));
}
