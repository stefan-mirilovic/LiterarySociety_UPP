package upp.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDto implements Serializable {

    private String content;
    private String recipient;

}
