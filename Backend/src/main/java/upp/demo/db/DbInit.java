package upp.demo.db;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import upp.demo.elastic.service.IndexingService;
import upp.demo.model.Book;
import upp.demo.model.Genre;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.GenreRepository;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInit implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    @Autowired
    private IndexingService indexingService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Getting genres
        Genre genreHorror = genreRepository.findByName("Horror");
        Genre genreDrama = genreRepository.findByName("Drama");
        Genre genreHistory = genreRepository.findByName("History");

        //Writers
        User writer1 = userRepository.findByEmail("writer1@yahoo.com");

        //Forming book in DB
        Book bookHorror  = new Book();
        bookHorror.setOwnerEmail("writer1@yahoo.com");
        bookHorror.setGenre(genreHorror);
        bookHorror.setOwner(writer1);
        bookHorror.setPublished(true);
        bookHorror.setSynopsis("Scary");
        bookHorror.setTitle("Primenjena kriptografija");
        bookHorror.setDocumentPath("pdf/mqqLxJAQeC.pdf");
        Book book = bookRepository.save(bookHorror);
        List<Book> books = new ArrayList<>();
        books.add(book);
        writer1.setOwnedBooks(books);
        userRepository.save(writer1);

        indexingService.deleteAllIndexes();
        indexingService.indexBook(book);

        User beta1 = userRepository.findByEmail("novisad@yahoo.com");
        User beta2 = userRepository.findByEmail("washington@yahoo.com");
        User beta3 = userRepository.findByEmail("beograd@yahoo.com");
        indexingService.indexBeta(beta1);
        indexingService.indexBeta(beta2);
        indexingService.indexBeta(beta3);



    }
}
