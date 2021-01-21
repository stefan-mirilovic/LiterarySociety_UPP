package upp.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.model.Book;
import upp.demo.repository.GenreRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SynopsisMapper {
    private final GenreRepository genreRepository;

    public Book convert(List<FormSubmissionDto> formSubmissionDtos) {
        Book book = new Book();

        for (FormSubmissionDto form : formSubmissionDtos) {

            if (form.getFieldId().equals("title")) {
                book.setTitle(form.getFieldValue());

            } else if (form.getFieldId().equals("genre")) {
                book.setGenre(genreRepository.findByName(form.getFieldValue()));

            } else if (form.getFieldId().equals("synopsis")) {
                book.setSynopsis(form.getFieldValue());
            }

        }
        return book;
    }
}
