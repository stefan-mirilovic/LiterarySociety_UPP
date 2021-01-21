package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SynopsisTableService implements JavaDelegate {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Book book = (Book) delegateExecution.getVariable(PropertyName.VariableName.BOOK_SYNOPSIS);

        //logika je za jednog editora
        List<Book> books = bookRepository.findAllByEditorsIn(book.getEditors());

        delegateExecution.setVariable("reject",false);

    }
}
