package upp.demo.helper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import upp.demo.dto.TableDto;
import upp.demo.dto.TableFieldDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TableHelper {

	private final RuntimeService runtimeService;
	private final PropertyHelper propertyHelper;
	public List<TableFieldDto> convertToBook(String process, List<FormField> formFields){
		List<Book> textForReviews = (List<Book>) runtimeService.getVariable(process, PropertyName.VariableName.BOOKS_FOR_REVIEW);
		return convertToTableField(textForReviews,formFields);
	}

	public List<TableFieldDto> convertToTableField(List<Book> books, List<FormField> formFields){
		List<TableFieldDto> tableFieldDtoList = new ArrayList<>();
		for(Book book:books){
			TableFieldDto tableFieldDto = new TableFieldDto();
			for(FormField formField:formFields){
				if(formField.getId().equals("pdf")){
					tableFieldDto.setFieldId(formField.getId());
//					tableFieldDto.setFieldValue(book.ge);
				}
				if(formField.getId().equals("owner")){
					tableFieldDto.setFieldValue(book.getOwnerEmail());
					tableFieldDto.setInput(propertyHelper.findAvailableProperties(formField.getProperties()).getInputs());
				}
				if(formField.getId().equals("comment")){
					tableFieldDto.setFieldValue("");
					tableFieldDto.setInput(propertyHelper.findAvailableProperties(formField.getProperties()).getInputs());
					tableFieldDto.setCustom(propertyHelper.findAvailableProperties(formField.getProperties()).getCustom());
				}
			}
			tableFieldDtoList.add(tableFieldDto);
		}
		return tableFieldDtoList;
	}
}
