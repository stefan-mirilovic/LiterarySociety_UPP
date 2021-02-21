package upp.demo.elastic.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.demo.elastic.handler.PdfIndexHandler;
import upp.demo.elastic.model.BookIndex;
import upp.demo.elastic.repository.BookIndexRepository;
import upp.demo.model.Book;

import java.io.File;
import java.io.IOException;

@Service
public class IndexingService {

    @Autowired
    private BookIndexRepository bookIndexRepository;

    @Autowired
    private PdfIndexHandler pdfIndexHandler;

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

    public void deleteAllIndexes() {
        bookIndexRepository.deleteAll();
    }

}
