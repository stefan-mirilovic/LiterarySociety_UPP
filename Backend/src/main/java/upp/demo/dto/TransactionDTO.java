package upp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upp.demo.enumeration.TransactionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String timestamp;
    private Long initiatorId;
    private Long bookId;
    private TransactionStatus status;
}
