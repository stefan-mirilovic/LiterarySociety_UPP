package upp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreDTO {
    private Long id;
    private String title;
    private String writer;
    private String isbn;
    private int publishingYear;
    private String genre;
    private String synopsis;
    private boolean owned;
    private double price;
}
