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
        BookIndex bookIndex = new BookIndex();
        bookIndex.setId(book.getId());
        bookIndex.setTitle(book.getTitle());
        bookIndex.setBasicInfo(book.getSynopsis());
        bookIndex.setOpenAccess(book.getPublished());
        bookIndex.setGenre(book.getGenre().getName());
        bookIndex.setText(pdfIndexHandler.getPdfText(new File(book.getDocumentPath())));
        bookIndex.setWriter(book.getOwner().getName() + " " + book.getOwner().getSurname());
        bookIndexRepository.save(bookIndex);
    }


}
