package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDocumentsForReview implements JavaDelegate {
	private final BookRepository bookRepository;

	@Override
	public void execute(DelegateExecution delegateExecution){
		List<Book> books = bookRepository.findAllByDocumentStatus(DocumentStatus.TEXT_PENDING);
		delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW, books);
	}
}
