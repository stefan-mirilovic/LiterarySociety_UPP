package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.helper.MultiSelectHelper;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBetaReaders implements JavaDelegate {

    private final MultiSelectHelper multiSelectHelper;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        List<String> emails = new ArrayList<>();
        for (FormSubmissionDto formSubmissionDto : submissionList) {
            if (formSubmissionDto.getFieldId().equals("betaReaders")) {
                emails = multiSelectHelper.convert(formSubmissionDto.getFieldValue());
            }
        }

        for (String email : emails) {
            emailService.sendSimpleMessage(email, "Check Plagiarism", "Please Check Plagiarism");
        }
        Book book = (Book) delegateExecution.getVariable(PropertyName.VariableName.BOOK_SYNOPSIS);
        List<Book> books = new ArrayList<>();
        books.add(book);
        List<String> comments = new ArrayList<>();
        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW, books);
        delegateExecution.setVariable(PropertyName.BookPublishing.CHOSEN_BETA_READERS, emails);
        delegateExecution.setVariable(PropertyName.BookPublishing.BETA_COMMENTS, comments);

    }
}
