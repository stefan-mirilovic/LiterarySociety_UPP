package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
