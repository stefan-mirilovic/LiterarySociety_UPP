package upp.demo.helper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import upp.demo.dto.TableDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TableHelper {

	private final RuntimeService runtimeService;
	public List<TableDto> convertToBook(String process, List<FormField> formFields){
		List<Book> textForReviews = (List<Book>) runtimeService.getVariable(process, PropertyName.VariableName.BOOKS_FOR_REVIEW);
		for(Book book: textForReviews){
			
		}
	}
}
