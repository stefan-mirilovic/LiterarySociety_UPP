package upp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreDisplayDTO {
    private Long id;
    private String title;
    private String writer;
    private String synopsis;
    private boolean owned;
    private double price;
    private int noOfPages;
}
