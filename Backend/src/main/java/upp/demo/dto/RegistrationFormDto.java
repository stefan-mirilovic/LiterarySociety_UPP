package upp.demo.dto;

import lombok.Data;
import org.camunda.bpm.engine.form.FormField;

import java.io.Serializable;
import java.util.List;

@Data
public class RegistrationFormDto implements Serializable {

	private List<FormField> formFields;
	private String processInstanceId;
	private String taskId;

}
