package upp.demo.elastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import upp.demo.dto.Coordinate;
import upp.demo.elastic.handler.PdfIndexHandler;
import upp.demo.elastic.model.BetaIndex;
import upp.demo.elastic.model.BookIndex;
import upp.demo.elastic.repository.BetaIndexRepository;
import upp.demo.elastic.repository.BookIndexRepository;
import upp.demo.model.Book;
import upp.demo.model.User;

import java.io.File;
import java.io.IOException;

@Service
public class IndexingService {

    @Autowired
    private BookIndexRepository bookIndexRepository;

    @Autowired
    private BetaIndexRepository betaIndexRepository;

    @Autowired
    private PdfIndexHandler pdfIndexHandler;

    @Autowired
    private GeolocationService geolocationService;

    public void indexBook(Book book) throws IOException {
        BookIndex bookIndex = BookIndex.builder()
                .id(book.getId())
                .title(book.getTitle())
                .basicInfo(book.getSynopsis())
                .openAccess(book.getPublished())
                .genre(book.getGenre().getName())
                .text(pdfIndexHandler.getPdfText(new File(book.getDocumentPath())))
                .writer(book.getOwner().getName() + " " + book.getOwner().getSurname()).build();
        bookIndexRepository.save(bookIndex);
    }

    public void indexBeta(User user) {
        Coordinate coordinate = geolocationService.getLocation(user);

        BetaIndex betaIndex = BetaIndex.builder()
                .id(user.getId())
                .name(user.getName() + " " + user.getSurname())
                .email(user.getEmail())
                .location(new GeoPoint(coordinate.getLatitude(), coordinate.getLongitude())).build();
        betaIndexRepository.save(betaIndex);
    }

    public void deleteAllIndexes() {
        bookIndexRepository.deleteAll();
    }

}
