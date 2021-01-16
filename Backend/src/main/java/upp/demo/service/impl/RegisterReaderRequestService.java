package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.enumeration.RegisterRequestStatus;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.Processes;
import upp.demo.globals.PropertyName;
import upp.demo.model.RegisterReaderRequest;
import upp.demo.model.User;
import upp.demo.repository.RegisterReaderRequestRepository;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterReaderRequestService implements JavaDelegate {

    private final EmailService emailService;
    private final RegisterReaderRequestRepository registerReaderRequestRepository;
    private final UserRepository userRepository;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final ConversionService conversionService;

    public String approve(Long id, UUID approveCode) {
        String result = "Successfully confirmed your email!";
        RegisterReaderRequest request;
        try {
            request = registerReaderRequestRepository.findById(id).orElseThrow(Exception::new);
            if (!request.getApproveCode().equals(approveCode) || !request.getStatus().equals(RegisterRequestStatus.PENDING)) {
                request.setStatus(RegisterRequestStatus.CANCELLED);
                registerReaderRequestRepository.save(request);
                throw new Exception();
            }
            request.setStatus(RegisterRequestStatus.APPROVED);
            RoleEnum roleEnum = request.isBeta() ? RoleEnum.BETA_READER : RoleEnum.READER;
            User user = new User(0L, request.getEmail(), request.getPassword(), request.getName(), request.getSurname(),
                    request.getCity(), request.getCity(), roleEnum, request.getGenres());
            request.setGenres(new ArrayList<>());
            registerReaderRequestRepository.save(request);
            userRepository.save(user);
        }catch (Exception e){
            result = "Failed to confirm your email!";
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_14uovfx");
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
//        Task task = taskService.createTaskQuery().taskId("Activity_1qfd0fd").singleResult();
//        taskService.complete(task.getId());
        return result;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        RegisterReaderRequest registerReaderRequest = conversionService.convert(registration, RegisterReaderRequest.class);
        registerReaderRequest.setGenres(new ArrayList<>());
        registerReaderRequest.setStatus(RegisterRequestStatus.PENDING);
        registerReaderRequest.setApproveCode(UUID.randomUUID());
        registerReaderRequestRepository.save(registerReaderRequest);
        emailService.sendSimpleMessage(registerReaderRequest.getEmail(), "Confirm your email",
                "To confirm your email, please click this link: http://localhost:9090/api/approve/" + registerReaderRequest.getId()
                        + "/" + registerReaderRequest.getApproveCode());
    }

}
