package tender.example.tender.iservice;


import tender.example.tender.dto.AuthenticationResponse;
import tender.example.tender.entity.User;

public interface IUserService {
     User findByEmail(String email);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    AuthenticationResponse validateVerificationToken(String token);

    String generateNewVerificationToken(String token);
}
