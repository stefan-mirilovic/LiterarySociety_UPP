package upp.demo.service.task.book;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

@Service
@RequiredArgsConstructor
public class NotifyWriterAccept implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String owner  = (String) delegateExecution.getVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        emailService.sendSimpleMessage(owner, "Synopsis Accpeted", "SYNOPSIS ACCPETED you have 7 DAYS to send text");
    }
}
