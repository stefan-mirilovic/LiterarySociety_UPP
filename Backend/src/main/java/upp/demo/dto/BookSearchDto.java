package upp.demo.dto;

import lombok.Data;

@Data
public class BookSearchDto {
    private String elementId;
    private String elementValue;
    private boolean must;
    private boolean phrase;
}
