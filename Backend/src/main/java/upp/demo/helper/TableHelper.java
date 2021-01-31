package upp.demo.helper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import upp.demo.dto.RowDto;
import upp.demo.dto.TableDto;
import upp.demo.dto.TableFieldDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.Book;
import upp.demo.repository.BookRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TableHelper {

    private final FileHelper fileHelper;
    private final RuntimeService runtimeService;
    private final PropertyHelper propertyHelper;

    public List<RowDto> convertToBook(String process, List<FormField> formFields) throws IOException {
        List<Book> textForReviews = (List<Book>) runtimeService.getVariable(process, PropertyName.VariableName.BOOKS_FOR_REVIEW);
        return (textForReviews != null) ? convertToTableField(textForReviews, formFields) : new ArrayList<>();
    }

    public List<RowDto> convertToTableField(List<Book> books, List<FormField> formFields) throws IOException {
        List<TableFieldDto> tableFieldDtoList;
        List<RowDto> rows = new ArrayList<>();
        for (Book book : books) {
            RowDto rowDto = new RowDto();
            tableFieldDtoList = new ArrayList<>();
            for (FormField formField : formFields) {
                TableFieldDto tableFieldDto = new TableFieldDto();
                if (formField.getId().equals("pdf")) {
                    tableFieldDto.setFieldId(formField.getId());
                    tableFieldDto.setFieldValue(fileHelper.load(book.getDocumentPath()));
                    tableFieldDto.setInput(propertyHelper.findAvailableProperties(formField.getProperties()).getInputs());
                }
                if (formField.getId().equals("owner")) {
                    tableFieldDto.setFieldId(formField.getId());
                    tableFieldDto.setFieldValue(book.getOwnerEmail());
                    tableFieldDto.setInput(propertyHelper.findAvailableProperties(formField.getProperties()).getInputs());
                }
                if (formField.getId().equals("comment")) {
                    tableFieldDto.setFieldId(formField.getId());
                    tableFieldDto.setFieldValue("");
                    tableFieldDto.setInput(propertyHelper.findAvailableProperties(formField.getProperties()).getInputs());
                    tableFieldDto.setCustom(propertyHelper.findAvailableProperties(formField.getProperties()).getCustom());
                }

                tableFieldDtoList.add(tableFieldDto);
            }
            rowDto.setRow(tableFieldDtoList);
            rows.add(rowDto);

        }
        return rows;
    }
}
