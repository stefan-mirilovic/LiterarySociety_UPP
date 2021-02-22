package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.BookSearchDto;
import upp.demo.dto.BookStoreDTO;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.service.impl.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/store")
    public ResponseEntity<?> findAllForStoreDisplay(@RequestParam int resperpage, @RequestParam int pageno, @RequestParam String genreid) {
        try {
            List<BookStoreDisplayDTO> result = bookService.findAllForStoreDisplay(resperpage, pageno, genreid);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/store/{id}")
    public ResponseEntity<?> findForStore(@PathVariable Long id) {
        try {
            BookStoreDTO result = bookService.findForStore(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/owned")
    public ResponseEntity<?> findAllOwned(@RequestParam int resperpage, @RequestParam int pageno) {
        try {
            List<BookStoreDisplayDTO> result = bookService.findAllOwned(resperpage, pageno);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchStore(@RequestBody List<BookSearchDto> bookSearchDtoList){
        try {
            List<BookStoreDisplayDTO> result = bookService.searchBooks(bookSearchDtoList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
