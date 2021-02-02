package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.ReviewDto;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.ReviewMapper;
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
    private final ReviewMapper reviewMapper;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
    	String ownerEmail = (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
		List<FormSubmissionDto> formData = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        ReviewDto reviewDto = reviewMapper.convert(formData);
        reviewDto.setOwnerEmail(ownerEmail);

        List<ReviewDto> reviewDtoList = (List<ReviewDto>) delegateExecution.getVariable(PropertyName.Review.REVIEW_LIST);
        reviewDtoList.add(reviewDto);
        delegateExecution.setVariable(PropertyName.Review.REVIEW_LIST,reviewDtoList);
    }
}
