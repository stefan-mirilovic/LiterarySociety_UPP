package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.globals.PropertyName;
import upp.demo.helper.FileHelper;
import upp.demo.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendText implements JavaDelegate {

    private final FileHelper fileHelper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        List<Book> books= new ArrayList<>();
        submissionList.forEach(x-> {
            try {
                Book book = fileHelper.saveFile(x.getFieldValue(),ownerEmail, DocumentStatus.HAND_WRITE_PENDING);
                books.add(book);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW,books);
    }
}
