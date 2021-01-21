package upp.demo.service.task;


import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.EmailDto;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

@Service
@RequiredArgsConstructor
public class EmailNotificationService implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        EmailDto emailDto= (EmailDto) delegateExecution.getVariable(PropertyName.VariableName.GENERIC_EMAIL);
        emailService.sendSimpleMessage(emailDto.getRecipient(),"Notification",emailDto.getContent());

    }
}
