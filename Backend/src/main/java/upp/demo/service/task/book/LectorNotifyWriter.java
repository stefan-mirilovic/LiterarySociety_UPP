package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectorNotifyWriter implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String owner = (String) delegateExecution.getVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        String errors = "";
        for (FormSubmissionDto form : synopsisForm) {
            if (form.getFieldId().equals("errors")) {
                errors = form.getFieldValue();
            }
        }
        emailService.sendSimpleMessage(owner,"Need to coret errors", "Errors: "+ errors);
    }
}
