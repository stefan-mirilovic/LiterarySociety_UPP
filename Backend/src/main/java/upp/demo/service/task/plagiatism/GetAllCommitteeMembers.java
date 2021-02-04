package upp.demo.service.task.plagiatism;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.NoteDto;
import upp.demo.dto.PlagiarismDecisionDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCommitteeMembers implements JavaDelegate {

    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<NoteDto> notes = (List<NoteDto>) delegateExecution.getVariable(PropertyName.Plagiarism.NOTES);
        List<User> committee = userRepository.findAllByRole(RoleEnum.COMITTEE_MEMBER);
        delegateExecution.setVariable(PropertyName.Plagiarism.COMMITTEE_MEMBERS, committee);
        List<PlagiarismDecisionDto> decisionList = new ArrayList<>();
        delegateExecution.setVariable(PropertyName.Plagiarism.PLAGIARISM_DECISION, decisionList);
    }
}
