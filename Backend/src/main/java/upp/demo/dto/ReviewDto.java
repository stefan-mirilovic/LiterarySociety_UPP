package upp.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewDto implements Serializable {
    private String comment;
    private String opinion;
    private String ownerEmail;
}
