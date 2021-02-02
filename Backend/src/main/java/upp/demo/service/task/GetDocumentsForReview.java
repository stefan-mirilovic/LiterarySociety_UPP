package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.ReviewDto;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDocumentsForReview implements JavaDelegate {
	private final BookRepository bookRepository;

	@Override
	public void execute(DelegateExecution delegateExecution){
		String ownerEmail= (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
		List<Book> books = bookRepository.findAllByDocumentStatusAndOwnerEmail(DocumentStatus.TEXT_PENDING,ownerEmail);
		delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW, books);
		List<ReviewDto> reviewDtos = new ArrayList<>();
		delegateExecution.setVariable(PropertyName.Review.REVIEW_LIST,reviewDtos);
	}
}
