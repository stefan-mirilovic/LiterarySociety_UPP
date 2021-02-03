package upp.demo.service.task;

import camundajar.impl.scala.sys.Prop;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.helper.FileHelper;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavePdfService implements JavaDelegate {

	private final FileHelper fileHelper;
	private final UserRepository userRepository;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		String ownerEmail= (String) delegateExecution.getVariable(PropertyName.VariableName.LOGGED_USER);
		List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
		submissionList.forEach(x-> {
			try {
				 fileHelper.saveFile(x.getFieldValue(),ownerEmail);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		List<User> editors= userRepository.findAllByRole(RoleEnum.COMITTEE_MEMBER);
		delegateExecution.setVariable(PropertyName.VariableName.EDITORS_FOR_REVIEW_TEXT,editors);
	}
}
