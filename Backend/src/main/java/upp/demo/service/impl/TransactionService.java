package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.dto.OrderDTO;
import upp.demo.dto.TransactionDTO;
import upp.demo.enumeration.TransactionStatus;
import upp.demo.mapper.TransactionMapper;
import upp.demo.model.Transaction;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.TransactionRepository;
import upp.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 200000)
    public void autoFeed() {
        List<Transaction> transactions = transactionRepository.findByStatus(TransactionStatus.IN_PROGRESS);
        for (Transaction t: transactions) {
            if (t.getTimestamp().isBefore(LocalDateTime.now().minusMinutes(20))) {
                t.setStatus(TransactionStatus.CANCELLED);
                transactionRepository.save(t);
            }
        }
    }

    public OrderDTO create(TransactionDTO dto) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new Exception("Not logged in!");
        }
        Transaction transaction = new Transaction();
        transaction.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(() -> new Exception("Book not found!")));
        transaction.setInitiator(user);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.IN_PROGRESS);
        transaction.setSuccessCode(UUID.randomUUID().toString());
        transaction.setFailedCode(UUID.randomUUID().toString());
        transaction.setErrorCode(UUID.randomUUID().toString());
        transaction = transactionRepository.save(transaction);
        //return transactionMapper.toDTO(transaction);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAmount(59.99);
        orderDTO.setMerchantId(transaction.getBook().getOwner().getMerchantId().toString());
        orderDTO.setSuccessUrl("http://localhost:9090/api/transactions/" + transaction.getId() +
                "/success/" + transaction.getSuccessCode());
        orderDTO.setFailedUrl("http://localhost:9090/api/transactions/" + transaction.getId() +
                "/failed/" + transaction.getFailedCode());
        orderDTO.setErrorUrl("http://localhost:9090/api/transactions/" + transaction.getId() +
                "/error/" + transaction.getErrorCode());
        return orderDTO;
    }

    public TransactionDTO changeStatus(Long id, String code, TransactionStatus status) throws Exception {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found!"));
        String confirmationCode = null;
        switch (status) {
            case COMPLETED:
                confirmationCode = transaction.getSuccessCode();
                break;
            case FAILED:
                confirmationCode = transaction.getFailedCode();
                break;
            case ERROR:
                confirmationCode = transaction.getErrorCode();
                break;
        }
        assert confirmationCode != null;
        if (!confirmationCode.equals(code)) {
            throw new Exception("Invalid code");
        }
        transaction.setStatus(status);
        transaction = transactionRepository.save(transaction);
        if (status == TransactionStatus.COMPLETED) {
            User user = transaction.getInitiator();
            user.getOwnedBooks().add(transaction.getBook());
            userRepository.save(user);
        }
        return transactionMapper.toDTO(transaction);
    }
}
