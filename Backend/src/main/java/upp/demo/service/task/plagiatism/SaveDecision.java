package upp.demo.service.task.plagiatism;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.PlagiarismDecisionDto;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.PlagiarismDecisionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveDecision implements JavaDelegate {

    private final PlagiarismDecisionMapper plagiarismDecisionMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        PlagiarismDecisionDto plagiarismDecisionDto = plagiarismDecisionMapper.getDecision(submissionList);
        List<PlagiarismDecisionDto> plagiarismList = (List<PlagiarismDecisionDto>) delegateExecution.getVariable(PropertyName.Plagiarism.PLAGIARISM_DECISION);
        plagiarismList.add(plagiarismDecisionDto);
        delegateExecution.setVariable(PropertyName.Plagiarism.PLAGIARISM_DECISION,plagiarismList);
    }
}
