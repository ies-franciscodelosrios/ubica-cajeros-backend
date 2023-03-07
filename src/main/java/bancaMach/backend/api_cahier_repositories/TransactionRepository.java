package bancaMach.backend.api_cahier_repositories;

import bancaMach.backend.api_cashier_models.dataobject.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
            value = "SELECT * " +
                    "FROM transaction " +
                    "WHERE security_code ilike %:code% ;",
            nativeQuery = true)
    Transaction getTransactionByCode(@Param(value="code")String code);
}
