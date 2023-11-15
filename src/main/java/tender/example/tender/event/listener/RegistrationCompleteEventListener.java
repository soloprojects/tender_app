package tender.example.tender.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import tender.example.tender.config.JwtService;
import tender.example.tender.entity.User;
import tender.example.tender.event.RegistrationCompleteEvent;
import tender.example.tender.service.AuthenticationService;
import tender.example.tender.service.EmailService;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    private final AuthenticationService authService;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the Verification Token for the User with Link
        User user = event.getUser();
        String token = jwtService.generateToken(user);
        authService.saveUserToken(user, token);
        //Send Mail to user
        String url =
                event.getApplicationUrl()
                        + "/verifyRegistration?token="
                        + token;

        // Send the confirmation email
//        try {
//            emailService.send(
//                    user.getEmail(),
//                    user.getFirstname(),
//                    null,
//                    String.format(url, token)
//            );
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        log.info("Click the link to verify your account: {}",
                url);
    }
}
