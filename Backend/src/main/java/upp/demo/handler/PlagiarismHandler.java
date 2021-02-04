package upp.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.mapper.BookMapper;
import upp.demo.mapper.BookMapperPlagiarism;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlagiarismHandler {

    private final BookRepository bookRepository;
    private final BookMapperPlagiarism bookMapper;

    public List<EnumDto> getWriterBook() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> writerBooks = bookRepository.findAllByPublishedAndOwnerEmail(true, user.getEmail());
        return writerBooks.stream().map(bookMapper::convert).collect(Collectors.toList());
    }

    public List<EnumDto> getOtherBooks() {
        List<Book> allBooks = bookRepository.findAllByPublished(true);
        List<EnumDto> enumList= allBooks.stream().map(bookMapper::convert).collect(Collectors.toList());
        enumList.remove(getWriterBook());
        return enumList;
    }
}
