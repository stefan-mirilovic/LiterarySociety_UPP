package upp.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.helper.FileHelper;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PdfHandler {

	private final FileHelper fileHelper;

	public List<FormSubmissionDto> change(List<FormSubmissionDto> submission) throws IOException {
		List<FormSubmissionDto> forms=submission;
		for(FormSubmissionDto form: submission){
			if(form.getIsFile()!=null){
				form.setFieldValue("accepted");
			}
		}
		return forms;
	}
}
