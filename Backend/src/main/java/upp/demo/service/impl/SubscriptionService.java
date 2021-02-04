package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import upp.demo.enumeration.SubscriptionStatus;
import upp.demo.model.Subscription;
import upp.demo.model.User;
import upp.demo.repository.SubscriptionRepository;
import upp.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final RuntimeService runtimeService;

    public String changeStatus(Long id, String code, SubscriptionStatus status) throws Exception {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new Exception("Subscription not found!"));
        String confirmationCode = null;
        switch (status) {
            case ACTIVE:
                confirmationCode = subscription.getSuccessCode();
                break;
            case FAILED:
                confirmationCode = subscription.getFailedCode();
                break;
            case ERROR:
                confirmationCode = subscription.getErrorCode();
                break;
        }
        assert confirmationCode != null;
        if (!confirmationCode.equals(code)) {
            throw new Exception("Invalid code");
        }
        subscription.setStatus(status);
        subscription = subscriptionRepository.save(subscription);
        String retVal = "http://localhost:4200/login?msg=Subscription+failed,+please+try+again";
        if (status == SubscriptionStatus.ACTIVE) {
            runtimeService.createSignalEvent("Signal_SubscriptionPaid").send();
            retVal = "http://localhost:4200/login?msg=Subscription+activated";
            User user = subscription.getInitiator();
            user.setEnabled(true);
            userRepository.save(user);
        }
        return retVal;
    }
}
