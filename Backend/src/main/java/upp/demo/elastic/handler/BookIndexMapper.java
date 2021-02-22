package upp.demo.elastic.handler;

import org.springframework.stereotype.Component;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.elastic.model.BookIndex;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookIndexMapper {

    public List<BookStoreDisplayDTO> convertIndexToDto(List<BookIndex> bookIndexList) {
        List<BookStoreDisplayDTO> bookStore = new ArrayList<>();
        for (BookIndex index : bookIndexList){
            BookStoreDisplayDTO book = new BookStoreDisplayDTO();
            book.setId(index.getId());
            book.setTitle(index.getTitle());
            book.setOwned(index.getOpenAccess());
            book.setSynopsis(index.getText());
            book.setWriter(index.getWriter());
            bookStore.add(book);
        }
        return bookStore;
    }
}
