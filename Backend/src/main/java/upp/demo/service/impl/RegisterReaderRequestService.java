package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upp.demo.enumeration.RegisterRequestStatus;
import upp.demo.enumeration.RoleEnum;
import upp.demo.model.RegisterReaderRequest;
import upp.demo.model.User;
import upp.demo.repository.RegisterReaderRequestRepository;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterReaderRequestService {

    private final EmailService emailService;
    private final RegisterReaderRequestRepository registerReaderRequestRepository;
    private final UserRepository userRepository;

    public String create() {
        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest(null, "user1@maildrop.cc", "123",
                "Pera", "Peric", "Novi Sad", "Serbia", RegisterRequestStatus.PENDING, false,
                UUID.randomUUID(), new ArrayList<>());
        registerReaderRequestRepository.save(registerReaderRequest);
        emailService.sendSimpleMessage(registerReaderRequest.getEmail(), "Confirm your email",
                "To confirm your email, please click this link: http://localhost:9090/api/approve/" + registerReaderRequest.getId()
                        + "/" + registerReaderRequest.getApproveCode());
        return "created";
    }

    public String approve(Long id, UUID approveCode) {
        String result = "Successfully confirmed your email!";
        RegisterReaderRequest request;
        try {
            request = registerReaderRequestRepository.findById(id).orElseThrow(Exception::new);
            if (!request.getApproveCode().equals(approveCode) || !request.getStatus().equals(RegisterRequestStatus.PENDING)) {
                request.setStatus(RegisterRequestStatus.CANCELLED);
                registerReaderRequestRepository.save(request);
                throw new Exception();
            }
            request.setStatus(RegisterRequestStatus.APPROVED);
            RoleEnum roleEnum = request.isBeta() ? RoleEnum.BETA_READER : RoleEnum.READER;
            User user = new User(0L, request.getEmail(), request.getPassword(), request.getName(), request.getSurname(),
                    request.getCity(), request.getCity(), roleEnum, request.getGenres());
            request.setGenres(new ArrayList<>());
            registerReaderRequestRepository.save(request);
            userRepository.save(user);
        }catch (Exception e){
            result = "Failed to confirm your email!";
        }

        return result;
    }
}
