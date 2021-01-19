package upp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
	private String id;

	private String name;

	private String assignee;
}
