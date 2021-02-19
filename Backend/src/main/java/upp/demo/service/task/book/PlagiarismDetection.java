package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlagiarismDetection implements JavaDelegate {

    private final BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Book> books = (List<Book>) delegateExecution.getVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW);
        List<Book> allBooks = bookRepository.findAllByPublished(true);
        books.addAll(allBooks);
        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW, books);
    }
}
