package upp.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlagiarismDecisionDto implements Serializable {
    private String decision;
}
