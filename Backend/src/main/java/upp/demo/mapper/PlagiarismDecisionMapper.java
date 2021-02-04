package upp.demo.mapper;

import lombok.Data;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.PlagiarismDecisionDto;

import java.util.List;

@Data
public class PlagiarismDecisionMapper {

    public PlagiarismDecisionDto getDecision(List<FormSubmissionDto> forms){
        PlagiarismDecisionDto plagiarismDecisionDto = new PlagiarismDecisionDto();
        for(FormSubmissionDto form: forms){
            if(form.getFieldId().equals("decision")){
                plagiarismDecisionDto.setDecision(form.getFieldValue());
            }
        }
        return plagiarismDecisionDto;
    }
}
