package upp.demo.service.task.review;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

@RequiredArgsConstructor
@Service
public class SubscriptionCreate implements JavaDelegate {
    private final EmailService emailService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
        emailService.sendSimpleMessage(ownerEmail,"Subscription accepted", "You only have 2 weeks for subscription" +
                "on this link " +"Subscription link:");
    }
}
