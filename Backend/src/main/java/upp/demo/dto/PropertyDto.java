package upp.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
	List<EnumDto> values;
	String inputs;
	String custom;
	String constraints;
}
