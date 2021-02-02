package upp.demo.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.ReviewDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;

import java.util.List;

@Service
public class CountAccepted implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean accepted = false;
        String reviewersComments="Reviewers comments";
        int acceptCounter = 0;

        List<User> reviewers = (List<User>) delegateExecution.getVariable(PropertyName.VariableName.EDITORS_FOR_REVIEW_TEXT);
        List<ReviewDto> reviewDtoList = (List<ReviewDto>) delegateExecution.getVariable(PropertyName.Review.REVIEW_LIST);

        for(ReviewDto reviewDto: reviewDtoList){
            if(reviewDto.getOpinion().equals("Accept")){
                acceptCounter++;
            }
        }
        if(acceptCounter==reviewers.size()){
            accepted = true;
        }
        delegateExecution.setVariable(PropertyName.Review.ACCEPT,accepted);
        delegateExecution.setVariable(PropertyName.Review.REVIEWERS_COMMENTS,reviewersComments);
    }
}
