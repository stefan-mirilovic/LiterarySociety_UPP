package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.PlagiarismDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;

import java.util.List;

@Component
public class BookMapperPlagiarism {
    public EnumDto convert(Book book){
        EnumDto enumDto = new EnumDto();
        enumDto.setId(book.getId());
        enumDto.setValue(book.getTitle());
        return enumDto;
    }

    public PlagiarismDto convertToPlagiarismDto(List<FormSubmissionDto> submissionList){
        PlagiarismDto plagiarismDto = new PlagiarismDto();
        for(FormSubmissionDto form: submissionList){
            if(form.getFieldId().equals("otherBooks")){
                plagiarismDto.setPlagiarismTitle(form.getFieldValue());
            }
            if(form.getFieldId().equals("writer")){
                plagiarismDto.setPlagiarismWriterName(form.getFieldValue());
            }
            if(form.getFieldId().equals("books")){
                plagiarismDto.setOwnerTitle(form.getFieldValue());
            }
        }
        return plagiarismDto;
    }
}
