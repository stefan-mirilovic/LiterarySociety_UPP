package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksForReviewService implements JavaDelegate {

	private final UserRepository userRepository;
	private final BookRepository bookRepository;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

	}
}
