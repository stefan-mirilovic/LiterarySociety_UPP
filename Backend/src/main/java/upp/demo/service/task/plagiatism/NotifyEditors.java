package upp.demo.service.task.plagiatism;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;
import upp.demo.helper.MultiSelectHelper;
import upp.demo.model.User;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NotifyEditors implements JavaDelegate {

    private EmailService emailService;
    private MultiSelectHelper multiSelectHelper;
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        List<String> emails = new ArrayList<>();
        for(FormSubmissionDto formSubmissionDto : submissionList){
            if(formSubmissionDto.getFieldId().equals("editors")){
                emails = multiSelectHelper.convert(formSubmissionDto.getFieldValue());
            }
        }

        for(String email: emails){
            emailService.sendSimpleMessage(email,"Check Plagiarism","Please Check Plagiarism");
        }
        delegateExecution.setVariable(PropertyName.Plagiarism.CHOSEN_EDITORS, emails);
    }
}
