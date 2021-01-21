package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.enumeration.RoleEnum;
import upp.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAllByRole(RoleEnum role);
}
