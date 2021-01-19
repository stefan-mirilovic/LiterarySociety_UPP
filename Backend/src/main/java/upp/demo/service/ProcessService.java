package upp.demo.service;

import org.camunda.bpm.engine.runtime.ProcessInstance;

public interface ProcessService {

	ProcessInstance start(String name);

}
