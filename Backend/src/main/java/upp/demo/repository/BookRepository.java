package upp.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.Book;
import upp.demo.model.User;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByEditorsIn(List<User> users);

    List<Book> findAllByOrderByPublishingYearDesc();
}
