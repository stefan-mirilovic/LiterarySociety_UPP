package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.NoteDto;
import upp.demo.model.Book;

import java.util.List;

@Component
public class NoteMapper {

    public NoteDto convertToNote(List<FormSubmissionDto> submissionDtoList, Book book){
        NoteDto noteDto = new NoteDto();
        noteDto.setBookId(book.getId());
        noteDto.setBookTitle(book.getTitle());

        for(FormSubmissionDto form : submissionDtoList){
            if(form.getFieldId().equals("notes")){
                noteDto.setNote(form.getFieldValue());
            }
        }
        return noteDto;
    }
}
