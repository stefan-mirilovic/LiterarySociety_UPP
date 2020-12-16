package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
