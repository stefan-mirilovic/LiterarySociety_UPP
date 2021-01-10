package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.container.impl.metadata.PropertyHelper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormFieldDto;
import upp.demo.dto.PropertyDto;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FormFieldsHelper {
	private final RuntimeService runtimeService;
	private final upp.demo.helper.PropertyHelper propertyHelper;

	public List<FormFieldDto> convertToDto(String processInstanceId, List<FormField> formFields){
		List<FormFieldDto> formFieldDtos = new ArrayList<>();

		for (FormField formField : formFields) {
			PropertyDto propertyDto = propertyHelper.findAvailableProperties(formField.getProperties());

			FormFieldDto formFieldDto = new FormFieldDto();
			formFieldDto.setId(formField.getId());
			formFieldDto.setType(formField.getType());
			formFieldDto.setTypeName(formField.getTypeName());
			formFieldDto.setLabel(formField.getLabel());

			formFieldDto.setAvailableValues(propertyDto.getValues());
			formFieldDto.setConstraint(propertyDto.getConstraints());
			formFieldDto.setCustom(propertyDto.getCustom());
			formFieldDto.setInput(propertyDto.getInputs());
			formFieldDto.setReadOnly(false);
			formFieldDtos.add(formFieldDto);
		}
		return formFieldDtos;
	}
}
