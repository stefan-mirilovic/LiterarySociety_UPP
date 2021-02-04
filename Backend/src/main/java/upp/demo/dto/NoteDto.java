package upp.demo.dto;

import lombok.Data;

@Data
public class NoteDto {
    private Long bookId;
    private String note;
    private String bookTitle;
}
