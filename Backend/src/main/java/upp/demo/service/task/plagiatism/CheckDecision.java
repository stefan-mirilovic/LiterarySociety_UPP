package upp.demo.service.task.plagiatism;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.PlagiarismDecisionDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;

import java.util.List;

@Service
public class CheckDecision implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean finishDecision = false;
        int plagiarismCounter = 0;
        int notPlagiarismCounter = 0;
        List<User> members = (List<User>) delegateExecution.getVariable(PropertyName.Plagiarism.COMMITTEE_MEMBERS);
        List<PlagiarismDecisionDto> plagiarismList = (List<PlagiarismDecisionDto>) delegateExecution.getVariable(PropertyName.Plagiarism.PLAGIARISM_DECISION);
        for(PlagiarismDecisionDto dto: plagiarismList){
            if(dto.getDecision().equals("Plagiarism")){
                plagiarismCounter++;
            }
            else{
                notPlagiarismCounter++;
            }
        }

        if(plagiarismCounter==members.size()){
            finishDecision = true;
        }
        if(notPlagiarismCounter==members.size()){
            finishDecision = true;
        }
        delegateExecution.setVariable(PropertyName.Plagiarism.PLAGIARISM_DECISION,finishDecision);
    }
}
