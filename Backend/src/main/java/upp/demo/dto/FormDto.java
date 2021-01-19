package upp.demo.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class FormDto {
	private String processInstanceId;
	private String taskId;
	private Collection<FormFieldDto> formFields;
}
