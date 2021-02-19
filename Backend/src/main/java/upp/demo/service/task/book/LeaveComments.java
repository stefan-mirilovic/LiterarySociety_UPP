package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveComments implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        List<String> comments = (List<String>) delegateExecution.getVariable(PropertyName.BookPublishing.BETA_COMMENTS);
        for(FormSubmissionDto form: submissionList){
            if(form.getFieldId().equals("comments")){
                comments.add(form.getFieldValue());
            }
        }
        delegateExecution.setVariable(PropertyName.BookPublishing.BETA_COMMENTS,comments);
    }
}
