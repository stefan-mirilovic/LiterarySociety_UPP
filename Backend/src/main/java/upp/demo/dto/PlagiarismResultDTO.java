package upp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlagiarismResultDTO {
    private List<PaperDTO> similarPapers;
    private PaperDTO uploadedPaper;
}