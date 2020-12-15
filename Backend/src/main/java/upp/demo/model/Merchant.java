package upp.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private UUID merchantId;
}
