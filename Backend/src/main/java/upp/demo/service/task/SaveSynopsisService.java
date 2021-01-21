package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import upp.demo.dto.EmailDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SaveSynopsisService implements JavaDelegate {

    private final ConversionService conversionService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //delegateExecution.setVariable(); pravim promenjivu sa bilo kojom vrednoscu
        //vidljiva je i na kamundi
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        Book book = conversionService.convert(synopsisForm,Book.class);
        book.setPublished(false);
        //ubaciti iz baze
        book.setOwner(userRepository.findByEmail("merchant1@maildrop.cc"));
        Random rand = new Random();
        List<User> editorsList = userRepository.findAllByRole(RoleEnum.EDITOR);
        User randomEditor = editorsList.get(rand.nextInt(editorsList.size()));
        book.getEditors().add(randomEditor);
        bookRepository.save(book);
        delegateExecution.setVariable(PropertyName.VariableName.BOOK_SYNOPSIS,book);

        EmailDto email= new EmailDto();
        email.setContent("New synopsis has been detected.Please check.");
        email.setRecipient(randomEditor.getEmail());
        delegateExecution.setVariable(PropertyName.VariableName.GENERIC_EMAIL,email);
    }
}
