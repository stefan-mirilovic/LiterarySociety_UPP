package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.springframework.stereotype.Component;
import upp.demo.dto.ValidationDto;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidationHelper {

	public HashMap<String, String> getValidationConstraints(List<FormFieldValidationConstraint> constraintList){
		HashMap<String,String> constraints = new HashMap<>();
		if(!constraintList.isEmpty() && constraintList != null){
			for(FormFieldValidationConstraint fc: constraintList){
				constraints.put(fc.getName(),fc.getConfiguration().toString());
			}
		}
		return constraints;
	}
}
