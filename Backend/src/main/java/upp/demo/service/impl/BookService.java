package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.mapper.BookMapper;
import upp.demo.model.Book;
import upp.demo.model.Genre;
import upp.demo.repository.BookRepository;
import upp.demo.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    public List<BookStoreDisplayDTO> findAllForStoreDisplay(int resultsPerPage, int pageNo, String genreid) throws Exception {
        List<Book> list = null;
        if (genreid.equals("undefined"))
            list = bookRepository.findAllByOrderByPublishingYearDesc();
        else {
            Genre genre = genreRepository.findById(Long.parseLong(genreid)).orElseThrow(() -> new Exception("Genre not found!"));
            list = bookRepository.findAllByGenreOrderByPublishingYearDesc(genre);
        }
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
