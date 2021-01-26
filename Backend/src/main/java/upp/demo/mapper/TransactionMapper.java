package upp.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.TransactionDTO;
import upp.demo.model.Transaction;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    public TransactionDTO toDTO(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setBookId(entity.getBook().getId());
        dto.setInitiatorId(entity.getInitiator().getId());
        dto.setTimestamp(entity.getTimestamp().toString());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
