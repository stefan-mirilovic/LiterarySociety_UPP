package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.enumeration.TransactionStatus;
import upp.demo.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByStatus(TransactionStatus status);
}
