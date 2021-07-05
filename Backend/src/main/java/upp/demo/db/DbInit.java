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
        Genre genreEdu = genreRepository.findByName("Educational");

        //Writers
        User writer1 = userRepository.findByEmail("writer1@maildrop.cc");
        User reader = userRepository.findByEmail("reader@maildrop.cc");

        //Forming book in DB
        Book bookHorror  = new Book();
        bookHorror.setOwnerEmail("writer1@maildrop.cc");
        bookHorror.setGenre(genreHorror);
        bookHorror.setOwner(writer1);
        bookHorror.setPublished(true);
        bookHorror.setSynopsis("Scary");
        bookHorror.setTitle("Primenjena kriptografija");
        bookHorror.setDocumentPath("pdf/mqqLxJAQeC.pdf");
        Book book = bookRepository.save(bookHorror);

        Book book2  = new Book();
        book2.setOwnerEmail("writer1@maildrop.cc");
        book2.setGenre(genreEdu);
        book2.setOwner(writer1);
        book2.setPublished(true);
        book2.setSynopsis("Živimo u digitalnom dobu. Dobu u kojem se u većini slučajeva" +
                "ne postavlja pitanje da li u digitalnom obliku postoji korisni" +
                "materijal, nego kako takav materijal pronaći u mnoštvu digitalnih" +
                "dokumenata. Razvojem informaciono-komunikacionih tehnologija" +
                "i razvojem veba stvorili su se uslovi da značajan deo svetske" +
                "populacije ima pristup potrebnom hardveru i softveru za kreiranje" +
                "i distribuiranje digitalnih dokumenata. Pojavljuju se i novi" +
                "termini u srpskom jeziku koji su nastali kao posledica digitalnog" +
                "doba u kojem živimo. Da li ste čuli za termine guglovanje, lajkovanje," +
                "tvitovanje, šerovanje, selfi? Ako ste čuli za ove termine" +
                "onda koristite tehnologije digitalnog doba.");
        book2.setTitle("Upravljanje digitalnim dokumentima");
        book2.setDocumentPath("pdf/rtvgJnspSC.pdf");
        book2 = bookRepository.save(book2);
        List<Book> books = new ArrayList<>();
        books.add(book);
        reader.setOwnedBooks(books);
        userRepository.save(reader);

        indexingService.deleteAllIndexes();
        indexingService.indexBook(book);
        indexingService.indexBook(book2);

        User beta1 = userRepository.findByEmail("novisad@maildrop.cc");
        User beta2 = userRepository.findByEmail("washington@maildrop.cc");
        User beta3 = userRepository.findByEmail("beograd@maildrop.cc");
        indexingService.indexBeta(beta1);
        indexingService.indexBeta(beta2);
        indexingService.indexBeta(beta3);



    }
}
