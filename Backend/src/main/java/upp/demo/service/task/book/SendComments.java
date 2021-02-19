package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendComments implements JavaDelegate {

    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> comments = (List<String>) delegateExecution.getVariable(PropertyName.BookPublishing.BETA_COMMENTS);
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        String commentsFromBeta= " ";
        for(String str: comments){
            commentsFromBeta += str;
        }
        emailService.sendSimpleMessage(ownerEmail,"Comments from Beta Readers",commentsFromBeta);
    }
}
