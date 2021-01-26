package upp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upp.demo.enumeration.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @ManyToOne
    private User initiator;

    @ManyToOne
    private Book book;

    @Column
    private TransactionStatus status;

    @Column(unique=true)
    private String successCode;

    @Column(unique=true)
    private String failedCode;

    @Column(unique=true)
    private String errorCode;
}
