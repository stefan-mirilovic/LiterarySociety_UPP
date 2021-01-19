package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubmissionListHelper {

	public List<String> getEnums(List<FormSubmissionDto> submission, String enumId) {
		List<String> enumList = new ArrayList<>();
		for (FormSubmissionDto form : submission) {
			if (form.getFieldId().equals(enumId)) {
				enumList.addAll(split(form.getFieldValue()));
			}
		}
		return enumList;
	}

	private List<String> split(String genres) {
		List<String> genresSplit = new ArrayList<>();
		String[] arrOfStr = genres.split(",");
		for (String s : arrOfStr) {
			genresSplit.add(s);
		}
		return genresSplit;
	}
}
