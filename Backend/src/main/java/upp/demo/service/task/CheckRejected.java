package upp.demo.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import upp.demo.dto.ReviewDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;

import java.util.List;

@Service
public class CheckRejected implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        float rejectCounter = 0;
        float moreDocuments = 0;
        boolean rejected = false;
        boolean more = false;
        List<User> reviewers = (List<User>) delegateExecution.getVariable(PropertyName.VariableName.EDITORS_FOR_REVIEW_TEXT);
        List<ReviewDto> reviewDtoList = (List<ReviewDto>) delegateExecution.getVariable(PropertyName.Review.REVIEW_LIST);
        for (ReviewDto reviewDto : reviewDtoList) {
            if (reviewDto.getOpinion().equals("Reject")) {
                rejectCounter++;
            }
            if (reviewDto.getOpinion().equals("MoreDocument")) {
                moreDocuments++;
            }
        }

        float percent = (rejectCounter / reviewDtoList.size()) * 100;

        if (percent >= 50) {
            rejected = true;
        }
        if (moreDocuments > 0) {
            more = true;
        }

        delegateExecution.setVariable(PropertyName.Review.REJECT, rejected);
        delegateExecution.setVariable(PropertyName.Review.MORE_DOCUMENTS,more);
    }
}
