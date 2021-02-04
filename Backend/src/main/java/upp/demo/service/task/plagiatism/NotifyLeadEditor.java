package upp.demo.service.task.plagiatism;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.PlagiarismDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.BookMapperPlagiarism;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotifyLeadEditor implements JavaDelegate {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final BookRepository bookRepository;

    private final BookMapperPlagiarism bookMapperPlagiarism;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String leadEditor = userRepository.findAllByRole(RoleEnum.MAIN_EDITOR).get(0).getEmail();
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        PlagiarismDto plagiarismDto = bookMapperPlagiarism.convertToPlagiarismDto(submissionList);

        List<String> titles=new ArrayList<>();
        titles.add(plagiarismDto.getOwnerTitle());
        titles.add(plagiarismDto.getPlagiarismTitle());

        List<Book> books = bookRepository.findAllByTitleIn(titles);

        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW,books);
        emailService.sendSimpleMessage(leadEditor, "New Plagiarism Checking", "Please find editors for review" +
                "plagiarism check");
        List<User> allEditors = userRepository.findAllByRole(RoleEnum.EDITOR);
        delegateExecution.setVariable(PropertyName.Plagiarism.ALL_EDITORS, allEditors);
    }
}
