package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.PaperDTO;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.globals.PropertyName;
import upp.demo.helper.FileHelper;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;
import upp.demo.service.impl.PlagiarismService;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlagiarismDetection implements JavaDelegate {

    private final BookRepository bookRepository;
    private final PlagiarismService plagiarismService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Book> books = (List<Book>) delegateExecution.getVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW);
//        List<Book> allBooks = bookRepository.findAllByPublished(true);
//        books.addAll(allBooks);
        Book synopsis = (Book) delegateExecution.getVariable(PropertyName.VariableName.BOOK_SYNOPSIS);
        List<PaperDTO> similarPapers = plagiarismService.uploadPaper(synopsis.getTitle(), books.get(0).getDocumentPath(), true).getSimilarPapers();

        for (PaperDTO paper: similarPapers) {
            Book book = new Book();
            book.setTitle(paper.getTitle());
            book.setIsbn(Double.toString(paper.getSimilarProcent()));
            books.add(book);
        }
        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW, books);
    }
}
