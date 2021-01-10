package upp.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
	List<String> values;
	String inputs;
	String custom;
	String constraints;
}
