package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import upp.demo.service.ProcessService;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

	private final RuntimeService runtimeService;

	@Override
	public ProcessInstance start(String name) {
		return runtimeService.startProcessInstanceByKey(name);
	}
}
