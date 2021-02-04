package upp.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteDto implements Serializable {
    private Long bookId;
    private String note;
    private String bookTitle;
}
