package upp.demo.service.task.book;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;

import java.util.List;

@Service
public class Publish implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Book> booksForPublish = (List<Book>) delegateExecution.getVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW);
    }
}
