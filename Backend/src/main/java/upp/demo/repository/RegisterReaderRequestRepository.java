package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.RegisterReaderRequest;

public interface RegisterReaderRequestRepository extends JpaRepository<RegisterReaderRequest, Long> {

}
