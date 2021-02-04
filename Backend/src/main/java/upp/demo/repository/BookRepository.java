package upp.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.model.Book;
import upp.demo.model.Genre;
import upp.demo.model.User;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByTitle(String title);

    List<Book> findAllByTitleIn(List<String> titles);

    List<Book> findAllByEditorsIn(List<User> users);

    List<Book> findAllByPublished(boolean published);

    List<Book> findAllByDocumentStatusAndOwnerEmail(DocumentStatus documentStatus, String email);

    List<Book> findAllByPublishedOrderByPublishingYearDesc(boolean published);

    List<Book> findAllByPublishedAndOwnerEmail(boolean published, String email);

    List<Book> findAllByGenreAndPublishedOrderByPublishingYearDesc(Genre genre, boolean published);

}
