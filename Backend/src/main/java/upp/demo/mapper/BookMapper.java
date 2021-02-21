package upp.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import upp.demo.dto.BookStoreDTO;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.model.Book;
import upp.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class BookMapper {

    public BookStoreDTO toBookStoreDTO(Book entity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean owned = user.getOwnedBooks().contains(entity);
        BookStoreDTO dto = new BookStoreDTO(entity.getId(), entity.getTitle(),
                entity.getOwner().getName() + " " + entity.getOwner().getSurname(), entity.getIsbn(), entity.getPublishingYear(),
                entity.getGenre().getName(), entity.getSynopsis(), entity.getNoOfPages(), owned, 59.99);
        return dto;
    }

    public BookStoreDisplayDTO toBookStoreDisplayDTO(Book entity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean owned = user.getOwnedBooks().contains(entity);
        BookStoreDisplayDTO dto = new BookStoreDisplayDTO(entity.getId(), entity.getTitle(),
                entity.getOwner().getName() + " " + entity.getOwner().getSurname(),
                null, owned, 59.99, -1);
        return dto;
    }

    public List<BookStoreDisplayDTO> toBookStoreDisplayDTOList(List<Book> entities) {
        List<BookStoreDisplayDTO> list = new ArrayList<>();
        for (Book entity: entities) {
            list.add(toBookStoreDisplayDTO(entity));
        }
        return list;
    }
}
