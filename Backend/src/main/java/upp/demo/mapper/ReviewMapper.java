package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.ReviewDto;
import upp.demo.model.Book;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewDto convert(List<FormSubmissionDto> formSubmissionDtos) {

        ReviewDto reviewDto = new ReviewDto();
        for (FormSubmissionDto form : formSubmissionDtos) {

            if (form.getFieldId().equals("comment")) {
                reviewDto.setComment(form.getFieldValue());
            } else if (form.getFieldId().equals("opinion")) {
                reviewDto.setOpinion(form.getFieldValue());
            }
        }
        return reviewDto;
    }
}
