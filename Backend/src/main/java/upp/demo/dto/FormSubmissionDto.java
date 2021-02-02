package upp.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class FormSubmissionDto implements Serializable  {
	private String fieldId;
	private String fieldValue;
	private String isFile;
}
