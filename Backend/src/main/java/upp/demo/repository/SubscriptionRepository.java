package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
