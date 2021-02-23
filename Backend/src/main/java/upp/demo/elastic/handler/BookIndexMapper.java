package upp.demo.elastic.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.elastic.model.BookIndex;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookIndexMapper {

    private final BookRepository bookRepository;

    public List<BookStoreDisplayDTO> convertIndexToDto(List<BookIndex> bookIndexList) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> list = user.getOwnedBooks();
        List<BookStoreDisplayDTO> bookStore = new ArrayList<>();
        for (BookIndex index : bookIndexList){
            Book bookFromDb = bookRepository.getOne(index.getId());
            boolean owned = user.getOwnedBooks().contains(bookFromDb);
            BookStoreDisplayDTO book = new BookStoreDisplayDTO();
            book.setId(index.getId());
            book.setTitle(index.getTitle());
            book.setOwned(owned);
            book.setSynopsis(index.getText());
            book.setWriter(index.getWriter());
            bookStore.add(book);
        }
        return bookStore;
    }
}
