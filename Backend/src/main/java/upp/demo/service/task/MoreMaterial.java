package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.ReviewDto;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoreMaterial implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int moreMaterial = (int) delegateExecution.getVariable(PropertyName.Review.MORE_DOCUMENT_COUNTER);
        moreMaterial++;
        delegateExecution.setVariable(PropertyName.Review.MORE_DOCUMENT_COUNTER,moreMaterial);

        String comments = "Reviewers comments/n";
        List<ReviewDto> reviewDtoList = (List<ReviewDto>) delegateExecution.getVariable(PropertyName.Review.REVIEW_LIST);
        for(ReviewDto reviewDto: reviewDtoList){
            comments+="Comment: "+comments+reviewDto.getComment()+"  ";
        }
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
        emailService.sendSimpleMessage(ownerEmail,"Registration need more material EXPIRES IN 7 Days", comments );
    }
}
