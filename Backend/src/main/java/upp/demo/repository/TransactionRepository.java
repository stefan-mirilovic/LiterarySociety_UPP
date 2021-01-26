package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
