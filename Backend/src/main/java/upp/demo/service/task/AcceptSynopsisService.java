package upp.demo.service.task;


import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcceptSynopsisService implements JavaDelegate {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean wannaRead = false;
        String decision= null;
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        for(FormSubmissionDto form: synopsisForm){
            if(form.getFieldId().equals("read")){
                decision = form.getFieldValue();
            }
        }

        if(decision.equals("YES")){
            wannaRead = true;
        }

        delegateExecution.setVariable(PropertyName.BookPublishing.WANNA_READ_HANDWRITING,wannaRead);
    }
}
