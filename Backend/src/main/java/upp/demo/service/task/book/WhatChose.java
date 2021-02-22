package upp.demo.service.task.book;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.elasticsearch.index.IndexService;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.elastic.service.IndexingService;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.model.Genre;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.GenreRepository;
import upp.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WhatChose implements JavaDelegate {

    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final IndexingService indexingService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> synopsisForm = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        String decision = null;
        boolean accept = false;
        for (FormSubmissionDto form : synopsisForm) {
            if (form.getFieldId().equals("beta")) {
                decision = form.getFieldValue();
            }
        }

        if (decision.equals("YES")) {
            accept = true;
        }
        List<Book> booksForPublish = (List<Book>) delegateExecution.getVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW);
        String ownerEmail = (String) delegateExecution.getVariable(PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        Book synopsis = (Book) delegateExecution.getVariable(PropertyName.VariableName.BOOK_SYNOPSIS);

        User owner = userRepository.findByEmail(ownerEmail);
        Genre genre = genreRepository.findByName(synopsis.getGenre().getName());


        Book book = new Book();
        book.setDocumentPath(booksForPublish.get(0).getDocumentPath());
        book.setOwner(owner);
        book.setTitle(synopsis.getTitle());
        book.setGenre(genre);
        book.setPublished(true);
        Book indexedBook = bookRepository.save(book);
        indexingService.indexBook(book);
        delegateExecution.setVariable(PropertyName.BookPublishing.IS_ACCEPTED_BETA, accept);
    }
}
