package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.mapper.BookMapper;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookStoreDisplayDTO> findAllForStoreDisplay(int resultsPerPage, int pageNo) {
        List<Book> list = bookRepository.findAllByOrderByPublishingYearDesc();
        //paginacija
        int no_of_pages = list.size() / resultsPerPage;
        if (list.size() % resultsPerPage != 0) {
            no_of_pages++;
        }
        List<BookStoreDisplayDTO> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < resultsPerPage*pageNo) {
                continue;
            }
            if (i >= resultsPerPage*(pageNo + 1)) {
                break;
            }
            results.add(bookMapper.toBookStoreDisplayDTO(list.get(i)));
        }
        if (results.size() != 0)
            results.get(0).setNoOfPages(no_of_pages);
        return results;
    }
}
