package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.dto.BookSearchDto;
import upp.demo.dto.BookStoreDTO;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.elastic.handler.BookIndexMapper;
import upp.demo.elastic.model.BookIndex;
import upp.demo.elastic.repository.BookIndexRepository;
import upp.demo.elastic.utils.ResultMapper;
import upp.demo.mapper.BookMapper;
import upp.demo.model.Book;
import upp.demo.model.Genre;
import upp.demo.model.User;
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
    private final BookIndexRepository bookIndexRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final BookIndexMapper bookIndexMapper;

    public List<BookStoreDisplayDTO> findAllForStoreDisplay(int resultsPerPage, int pageNo, String genreid) throws Exception {
        List<Book> list = null;
        if (genreid.equals("undefined"))
            list = bookRepository.findAllByPublishedOrderByPublishingYearDesc(true);
        else {
            Genre genre = genreRepository.findById(Long.parseLong(genreid)).orElseThrow(() -> new Exception("Genre not found!"));
            list = bookRepository.findAllByGenreAndPublishedOrderByPublishingYearDesc(genre, true);
        }
        //paginacija
        int no_of_pages = list.size() / resultsPerPage;
        if (list.size() % resultsPerPage != 0) {
            no_of_pages++;
        }
        List<BookStoreDisplayDTO> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < resultsPerPage * pageNo) {
                continue;
            }
            if (i >= resultsPerPage * (pageNo + 1)) {
                break;
            }
            results.add(bookMapper.toBookStoreDisplayDTO(list.get(i)));
        }
        if (results.size() != 0)
            results.get(0).setNoOfPages(no_of_pages);
        return results;
    }

    public BookStoreDTO findForStore(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(() -> new Exception("Book not found!"));
        return bookMapper.toBookStoreDTO(book);
    }

    public List<BookStoreDisplayDTO> findAllOwned(int resultsPerPage, int pageNo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> list = user.getOwnedBooks();
        //paginacija
        int no_of_pages = list.size() / resultsPerPage;
        if (list.size() % resultsPerPage != 0) {
            no_of_pages++;
        }
        List<BookStoreDisplayDTO> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < resultsPerPage * pageNo) {
                continue;
            }
            if (i >= resultsPerPage * (pageNo + 1)) {
                break;
            }
            results.add(bookMapper.toBookStoreDisplayDTO(list.get(i)));
        }
        if (results.size() != 0)
            results.get(0).setNoOfPages(no_of_pages);
        return results;
    }

    public List<BookStoreDisplayDTO> searchBooks(List<BookSearchDto> searchQueryList) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolean toHighlight = false;
        for (BookSearchDto element : searchQueryList) {
            //ako ima keywords u upitu, generisi highlight, ako ne, koristi sinopsis
            if (element.getElementId().equals("text")) {
                toHighlight = true;
            }
            if (element.isMust()) {
                if(element.isPhrase()){
                    boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(element.getElementId(), element.getElementValue()));
                }
                else{
                    boolQueryBuilder.must(QueryBuilders.commonTermsQuery(element.getElementId(), element.getElementValue()));
                }
            }
            else {
                if(element.isPhrase()){
                    boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(element.getElementId(), element.getElementValue()));
                }
                else{
                    boolQueryBuilder.should(QueryBuilders.commonTermsQuery(element.getElementId(), element.getElementValue()));
                }
            }
        }
        SearchQuery query;
        if (toHighlight) {
            query = queryBuilder.withQuery(boolQueryBuilder).withHighlightFields(
                    new HighlightBuilder.Field("text")
                            .preTags("<b>")
                            .postTags("</b>")
                            .numOfFragments(1)
                            .fragmentSize(200)).withPageable(PageRequest.of(0, 5)).build();
        } else {
            query = queryBuilder.withQuery(boolQueryBuilder).withPageable(PageRequest.of(0, 5)).build();
        }


        Page<BookIndex> books =  elasticsearchTemplate.queryForPage(query, BookIndex.class, new ResultMapper());
        if (books == null) {
            return new ArrayList<>();
        }
        List<BookStoreDisplayDTO> bookForDisplay = bookIndexMapper.convertIndexToDto(books.getContent(), toHighlight);
        return bookForDisplay;
    }

}
