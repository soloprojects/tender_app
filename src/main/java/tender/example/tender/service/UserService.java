package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tender.example.tender.config.JwtService;
import tender.example.tender.dto.AuthenticationResponse;
import tender.example.tender.entity.User;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IUserService;
import tender.example.tender.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private JwtService jwtService;

    private AuthenticationService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("Email not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public AuthenticationResponse validateVerificationToken(String token) {

        String refreshToken = token;
        String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            throw new BusinessException("Invalid JWT token, email not found", HttpStatus.NOT_FOUND);
        }
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        if (jwtService.isTokenValid(refreshToken, user)) {
            String accessToken = jwtService.generateToken(user);
            authService.revokeAllUserTokens(user);
            authService.saveUserToken(user, accessToken);
            AuthenticationResponse authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return authResponse;
        }
        throw new BusinessException("Invalid Token, token not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public String generateNewVerificationToken(String oldToken) {

        String userEmail = jwtService.extractUsername(oldToken);
        if (userEmail == null) {
            throw new BusinessException("Invalid token, email not found", HttpStatus.NOT_FOUND);
        }
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        if (jwtService.isTokenValid(oldToken, user)) {
            String accessToken = jwtService.generateToken(user);
            authService.revokeAllUserTokens(user);
            authService.saveUserToken(user, accessToken);
            return accessToken;
        }
        throw new BusinessException("Invalid Token, token not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
