package tender.example.tender.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tender.example.tender.config.JwtService;
import tender.example.tender.dto.AuthenticationResponse;
import tender.example.tender.dto.PasswordRequest;
import tender.example.tender.dto.RegisterRequest;
import tender.example.tender.entity.User;
import tender.example.tender.event.RegistrationCompleteEvent;
import tender.example.tender.service.AuthenticationService;
import tender.example.tender.service.UserService;
import tender.example.tender.utility.ResponseHandler;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/enroll")
public class RegistrationController {

    private UserService userService;
    private JwtService jwtService;

    private final AuthenticationService authenticationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterRequest userModel, final HttpServletRequest request) {
        AuthenticationResponse authResponse = authenticationService.register(userModel);
        log.info(userModel.getEmail());
        User user = userService.findByEmail(userModel.getEmail());
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return ResponseHandler.generateResponse("User created successfully", HttpStatus.CREATED, authResponse);
    }

    @GetMapping("/verifyRegistration")
    public ResponseEntity<Object> verifyRegistration(@RequestParam("token") String token) {
        AuthenticationResponse authResponse = userService.validateVerificationToken(token);
        return ResponseHandler.generateResponse("Verification successful", HttpStatus.OK, authResponse);
    }


    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        String verificationToken
                = userService.generateNewVerificationToken(oldToken);
        String email = jwtService.extractUsername(verificationToken);
        User user = userService.findByEmail(email);
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody PasswordRequest passwordModel, HttpServletRequest request) {

        try{
            User user = userService.findByEmail(passwordModel.getEmail());
            String url = "";
            String token = jwtService.generateToken(user);
            authenticationService.saveUserToken(user,token);
            url = passwordResetTokenMail(user,applicationUrl(request), token);
            return ResponseHandler.generateResponse("Password token sent to your email", HttpStatus.OK, url);
        }catch (Exception ex){
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/savePassword")
    public ResponseEntity<Object> savePassword(@RequestParam("token") String token,
                                               @RequestBody PasswordRequest passwordModel) {
        AuthenticationResponse authResponse = userService.validateVerificationToken(token);
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email);
        userService.changePassword(user, passwordModel.getNewPassword());
        return ResponseHandler.generateResponse("Password reset successfully", HttpStatus.CREATED, null);
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordRequest passwordModel){
        User user = userService.findByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
            return "Invalid Old Password";
        }
        //Save New Password
        userService.changePassword(user,passwordModel.getNewPassword());
        return "Password Changed Successfully";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url =
                applicationUrl
                        + "/savePassword?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to Reset your Password: {}",
                url);
        return url;
    }


    private void resendVerificationTokenMail(User user, String applicationUrl, String verificationToken) {
        String url =
                applicationUrl
                        + "/verifyRegistration?token="
                        + verificationToken;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
