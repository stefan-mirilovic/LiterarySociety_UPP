package upp.demo.service.task.review;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.model.Subscription;
import upp.demo.repository.SubscriptionRepository;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

@RequiredArgsConstructor
@Service
public class SubscriptionCreate implements JavaDelegate {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
        Subscription subscription = new Subscription(userRepository.findByEmail(ownerEmail));
        subscription = subscriptionRepository.save(subscription);
        emailService.sendSimpleMessage(ownerEmail,"Subscription accepted", "You only have 2 weeks to pay the subscription" +
                "on this link " +"Subscription link: "+PropertyName.Payment.PC_URL+"?merchantId="+PropertyName.Payment.MERCHANT_ID+
                "&amount="+ PropertyName.Payment.SUBSCRIPTION_COST +
                "&frequency="+PropertyName.Payment.FREQUENCY+"&interval="+PropertyName.Payment.INTERVAL+"&cycles="+PropertyName.Payment.CYCLES +
                "&successUrl=http:%2F%2Flocalhost:9090%2Fapi%2Fsubscriptions%2F"+subscription.getId()+"%2Fsuccess%2F"+subscription.getSuccessCode() +
                "&failedUrl=http:%2F%2Flocalhost:9090%2Fapi%2Fsubscriptions%2F"+subscription.getId()+"%2Ffailed%2F"+subscription.getFailedCode() +
                "&errorUrl=http:%2F%2Flocalhost:9090%2Fapi%2Fsubscriptions%2F"+subscription.getId()+"%2Ferror%2F"+subscription.getErrorCode());
    }
}
