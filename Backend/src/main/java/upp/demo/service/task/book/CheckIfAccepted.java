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
public class CheckIfAccepted implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        String decision = null;
        boolean accept = false;
        for (FormSubmissionDto form : synopsisForm) {
            if (form.getFieldId().equals("accept")) {
                decision = form.getFieldValue();
            }
        }

        if (decision.equals("YES")) {
            accept = true;
        }

        delegateExecution.setVariable(PropertyName.BookPublishing.IS_ACCEPTED_TEXT, accept);

    }
}
