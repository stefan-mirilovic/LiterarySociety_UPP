package upp.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RowDto {
    private List<TableFieldDto> row;
}
