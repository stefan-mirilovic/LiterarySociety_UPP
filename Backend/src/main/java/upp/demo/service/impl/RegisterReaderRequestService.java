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
public class RegisterReaderRequestService  {

    private final RuntimeService runtimeService;

    public String approve(Long id, UUID approveCode) {
        String result = "Successfully confirmed your email!";
        RegisterReaderRequest request;
        runtimeService.createSignalEvent("signalReader").send();
        return result;
    }
}
