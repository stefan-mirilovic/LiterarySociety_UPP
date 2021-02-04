package upp.demo.service.task.plagiatism;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.NoteDto;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.NoteMapper;
import upp.demo.model.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveNotes implements JavaDelegate {

    private NoteMapper noteMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormSubmissionDto> submissionList = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
        List<Book> books = (List<Book>) delegateExecution.getVariable(PropertyName.VariableName.BOOKS_FOR_REVIEW);
        List<NoteDto> notes = (List<NoteDto>) delegateExecution.getVariable(PropertyName.Plagiarism.NOTES);
        NoteDto noteDto = noteMapper.convertToNote(submissionList, books.get(0));
        notes.add(noteDto);
        delegateExecution.setVariable(PropertyName.Plagiarism.NOTES,notes);
    }
}
