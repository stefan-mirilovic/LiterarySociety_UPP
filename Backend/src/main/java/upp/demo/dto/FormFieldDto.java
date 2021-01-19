package upp.demo.dto;

import lombok.Data;
import org.camunda.bpm.engine.form.FormType;

import java.util.HashMap;
import java.util.List;

@Data
public class FormFieldDto {
	private String id;

	private String label;

	private FormType type;

	private String typeName;

	private List<EnumDto> availableValues;

	private HashMap<String, String> constraints;

	private Boolean readOnly;

	private String value;

	private String input;

	private String constraint;

	private String custom;
}
