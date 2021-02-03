package upp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upp.demo.enumeration.SubscriptionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @ManyToOne
    private User initiator;

    @Column
    private SubscriptionStatus status;

    @Column(unique=true)
    private String successCode;

    @Column(unique=true)
    private String failedCode;

    @Column(unique=true)
    private String errorCode;

    public Subscription(User initiator) {
        this.initiator = initiator;
        this.timestamp = LocalDateTime.now();
        this.status = SubscriptionStatus.CREATED;
        this.successCode = UUID.randomUUID().toString();
        this.failedCode = UUID.randomUUID().toString();
        this.errorCode = UUID.randomUUID().toString();
    }
}
