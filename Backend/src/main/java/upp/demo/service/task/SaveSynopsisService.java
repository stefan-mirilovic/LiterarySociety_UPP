package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.dto.EmailDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.SynopsisMapper;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SaveSynopsisService implements JavaDelegate {

    private final SynopsisMapper synopsisMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //delegateExecution.setVariable(); pravim promenjivu sa bilo kojom vrednoscu
        //vidljiva je i na kamundi
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        Book book = synopsisMapper.convert(synopsisForm);
        book.setPublished(false);
        //ubaciti iz baze
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        book.setOwnerEmail(user.getEmail());
        bookRepository.save(book);
        delegateExecution.setVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL, user.getEmail());
//        Random rand = new Random();
//        List<User> editorsList = userRepository.findAllByRole(RoleEnum.MAIN_EDITOR);
//        User randomEditor = editorsList.get(rand.nextInt(editorsList.size()));
//        book.getEditors().add(randomEditor);
//        bookRepository.save(book);
        User mainEditor = userRepository.findByRole(RoleEnum.MAIN_EDITOR);
        List<Book> books = new ArrayList<>();
        books.add(book);
        delegateExecution.setVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW,books);
        delegateExecution.setVariable(PropertyName.VariableName.BOOK_SYNOPSIS, book);
        emailService.sendSimpleMessage(mainEditor.getEmail(),"New synopsis", "New synopsis has been detected.Please check.");
//        EmailDto email= new EmailDto();
//        email.setContent("New synopsis has been detected.Please check.");
//        email.setRecipient(randomEditor.getEmail());
//        delegateExecution.setVariable(PropertyName.VariableName.GENERIC_EMAIL,email);

    }
}
