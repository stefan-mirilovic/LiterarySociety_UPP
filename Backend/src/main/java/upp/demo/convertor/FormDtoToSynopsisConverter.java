package upp.demo.convertor;


import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.model.Book;
import upp.demo.repository.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FormDtoToSynopsisConverter implements Converter<List<FormSubmissionDto>, Book> {

    private final GenreRepository genreRepository;


    @Override
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
