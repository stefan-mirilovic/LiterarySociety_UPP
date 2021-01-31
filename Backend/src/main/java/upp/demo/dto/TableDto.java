package upp.demo.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class TableDto {
	private String processInstanceId;
	private String taskId;
	private Collection<RowDto> tableRows;
}
