package upp.demo.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenState {
    private String accessToken;
    private Long expiresIn;
    private Long userId;
    private String userType;
    private boolean passwordChanged;
    private String email;
}
