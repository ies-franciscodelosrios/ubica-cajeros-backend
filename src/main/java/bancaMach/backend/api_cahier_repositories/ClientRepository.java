package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.dataobject.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(
            value = "SELECT * " +
                    "FROM client " +
                    "WHERE dni ilike %:dni% ;",
            nativeQuery = true)
    Optional<Client> getClientByDNI(@Param(value="dni")String dni);
}
