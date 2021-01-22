package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import upp.demo.enumeration.RegisterRequestStatus;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.model.Genre;
import upp.demo.model.RegisterReaderRequest;
import upp.demo.model.User;
import upp.demo.repository.RegisterReaderRequestRepository;
import upp.demo.repository.UserRepository;
import upp.demo.service.impl.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailApproveReaderService implements JavaDelegate {
	private final EmailService emailService;
	private final UserRepository userRepository;
	private final RegisterReaderRequestRepository registerReaderRequestRepository;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = (Long) delegateExecution.getVariable(PropertyName.VariableName.REGISTRATION_REQUEST);
		UUID approveCode = (UUID) delegateExecution.getVariable(PropertyName.VariableName.APPROVE_CODE);
		RegisterReaderRequest request = registerReaderRequestRepository.findById(id).orElseThrow(Exception::new);
		if (!request.getApproveCode().equals(approveCode) || !request.getStatus().equals(RegisterRequestStatus.PENDING)) {
			request.setStatus(RegisterRequestStatus.CANCELLED);
			registerReaderRequestRepository.save(request);
			throw new Exception();
		}
		request.setStatus(RegisterRequestStatus.APPROVED);
		RoleEnum roleEnum = request.isBeta() ? RoleEnum.BETA_READER : RoleEnum.READER;
		User user = new User(0L, request.getEmail(), request.getPassword(), request.getName(), request.getSurname(),
				request.getCity(), request.getCountry(), roleEnum, request.getGenres(), true);
		request.setGenres((List<Genre>)delegateExecution.getVariable("genres"));
		registerReaderRequestRepository.save(request);
		userRepository.save(user);
		delegateExecution.setVariable(PropertyName.VariableName.LOGGED_USER,user.getEmail());
	}
}
