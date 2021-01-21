package upp.demo.service.task;

import camundajar.impl.scala.sys.Prop;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RegisterRequestStatus;
import upp.demo.globals.Processes;
import upp.demo.globals.PropertyName;
import upp.demo.helper.SubmissionListHelper;
import upp.demo.mapper.RegisterReaderMapper;
import upp.demo.model.Genre;
import upp.demo.model.RegisterReaderRequest;
import upp.demo.repository.GenreRepository;
import upp.demo.repository.RegisterReaderRequestRepository;
import upp.demo.service.impl.EmailService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationApprovedWriterService implements JavaDelegate {

	private final EmailService emailService;
	private final GenreRepository genreRepository;
	private final RegisterReaderMapper registerReaderMapper;
	private final SubmissionListHelper submissionListHelper;
	private final RegisterReaderRequestRepository registerReaderRequestRepository;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
		RegisterReaderRequest registerReaderRequest = registerReaderMapper.convert(registration);
		List<Genre> genres = genreRepository.findAllByNameIn(submissionListHelper.getEnums(registration, "genre"));
		if(genres!=null){
			delegateExecution.setVariable("genre",genres);
		}
		else{
			registerReaderRequest.setGenres(new ArrayList<>());
		}

		registerReaderRequest.setStatus(RegisterRequestStatus.PENDING);
		registerReaderRequest.setApproveCode(UUID.randomUUID());
		registerReaderRequestRepository.save(registerReaderRequest);
		delegateExecution.setVariable(PropertyName.VariableName.APPROVE_CODE, registerReaderRequest.getApproveCode());
		delegateExecution.setVariable(PropertyName.VariableName.REGISTRATION_REQUEST, registerReaderRequest.getId());
		emailService.sendSimpleMessage(registerReaderRequest.getEmail(), "Confirm your email",
				"To confirm your email, please click this link: http://localhost:4200/confirm/" + registerReaderRequest.getId()
						+ "/" + registerReaderRequest.getApproveCode());
	}
}
