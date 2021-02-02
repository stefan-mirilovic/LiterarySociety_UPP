package upp.demo.service.task.review;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.ReviewDto;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationWriterReject implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String comments = "Reviewers comments/n";
        List<ReviewDto> reviewDtoList = (List<ReviewDto>) delegateExecution.getVariable(PropertyName.Review.REVIEW_LIST);
        for(ReviewDto reviewDto : reviewDtoList){
            comments+= "Comment: "+ reviewDto.getComment()+"  ";
        }
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
        emailService.sendSimpleMessage(ownerEmail,"Registration rejected", comments );
    }
}
