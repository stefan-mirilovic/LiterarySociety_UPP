package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import upp.demo.dto.NoteDto;
import upp.demo.dto.RowDto;
import upp.demo.dto.TableFieldDto;
import upp.demo.globals.PropertyName;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotesHelper {
    private final RuntimeService runtimeService;

    public List<RowDto> convertToNotes(String process, List<FormField> formFields){
        List<NoteDto> notes = (List<NoteDto>) runtimeService.getVariable(process, PropertyName.Plagiarism.NOTES);
        return (notes != null) ? convertToTableField(notes, formFields) : new ArrayList<>();
    }
    public List<RowDto> convertToTableField(List<NoteDto> notes, List<FormField> formFields){
        List<TableFieldDto> tableFieldDtoList;
        List<RowDto> rows = new ArrayList<>();
        for(NoteDto noteDto: notes){
            RowDto rowDto = new RowDto();
            tableFieldDtoList= new ArrayList<>();
            for(FormField formField:formFields){
                TableFieldDto tableFieldDto = new TableFieldDto();
                if(formField.getId().equals("note")){
                    tableFieldDto.setFieldId(formField.getId());
                    tableFieldDto.setFieldValue(noteDto.getNote());
                    tableFieldDtoList.add(tableFieldDto);
                }
                if(formField.getId().equals("bookTitle")){
                    tableFieldDto.setFieldId(formField.getId());
                    tableFieldDto.setFieldValue(noteDto.getBookTitle());
                    tableFieldDtoList.add(tableFieldDto);
                }
            }
            rowDto.setRow(tableFieldDtoList);
            rows.add(rowDto);
        }

        return rows;
    }
}
